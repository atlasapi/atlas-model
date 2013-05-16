package org.atlasapi.media.content.schedule;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.ContentStore;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.ChannelSchedule;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Version;
import org.atlasapi.media.util.ItemAndBroadcast;
import org.atlasapi.media.util.Resolved;
import org.atlasapi.media.util.WriteException;
import org.atlasapi.media.util.WriteResult;
import org.joda.time.Interval;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * {@code AbstractScheduleStore} is a base implementation of a
 * {@link ScheduleStore}. It first checks the integrity of the provided
 * {@link ScheduleHierarchy}s, persists all {@link Content} to the
 * {@link ContentStore} and then resolves and updates schedule as necessary.
 * 
 * Schedules are divided into discrete, contiguous blocks of regular duration,
 * represented by {@link ChannelSchedule}s. The block duration is determined by
 * the concrete implementation. Blocks may be empty, partially populated or
 * fully populated. Blocks may also have entries in common if they overlap the
 * start or end of the block interval.
 * 
 * This base will also detect overwritten
 * {@link org.atlasapi.media.entry.Broadcast Broadcast}s and update them in the
 * {@code ContentStore}.
 * 
 */
public abstract class AbstractScheduleStore implements ScheduleStore {
    
    private final ContentStore contentStore;
    private final BroadcastContiguityCheck contiguityCheck;
    private final ScheduleBlockUpdater blockUpdater;

    public AbstractScheduleStore(ContentStore contentStore) {
        this.contentStore = checkNotNull(contentStore);
        this.contiguityCheck = new BroadcastContiguityCheck();
        this.blockUpdater = new ScheduleBlockUpdater();
    }
    
    @Override
    public List<WriteResult<? extends Content>> writeSchedule(List<ScheduleHierarchy> content, Channel channel,
            Interval interval) throws WriteException {
        if (content.isEmpty()) {
            return ImmutableList.of();
        }
        List<ItemAndBroadcast> itemsAndBroadcasts = itemsAndBroadcasts(content);
        checkArgument(broadcastHaveIds(itemsAndBroadcasts),
                "all broadcasts must have IDs");
        checkArgument(broadcastsContiguous(itemsAndBroadcasts), 
                "broadcasts of items on %s not contiguous in %s", channel, interval);
        Publisher source = getSource(content);
        
        List<WriteResult<? extends Content>> writeResults = writeContent(content);
        if (!contentChanged(writeResults)) {
            return writeResults;
        }
        
        List<ChannelSchedule> currentBlocks = resolveCurrentScheduleBlocks(source, channel, interval);
        ScheduleUpdate updated = blockUpdater.updateBlocks(currentBlocks, itemsAndBroadcasts, channel, interval);
        for (ItemAndBroadcast staleEntry : updated.getStaleEntries()) {
            updateItemInContentStore(staleEntry);
        }
        doWrite(source, updated.getUpdatedBlocks());
        return writeResults;
    }
    
    /**
     * Resolve the current block(s) of schedule for a given source, channel and
     * interval. All the blocks overlapped, fully or partially, by the interval
     * must be returned with all entries fully populated. 
     * 
     * If there is no data for block then an empty block must be returned.
     * 
     * @param source
     * @param channel
     * @param interval
     * @return
     * @throws WriteException
     */
    protected abstract List<ChannelSchedule> resolveCurrentScheduleBlocks(Publisher source, Channel channel,
            Interval interval) throws WriteException;

    /**
     * Write the blocks of schedule for a source.
     * 
     * The entries in the blocks may not necessarily fully populate a block.
     * Entries in a block may overlap the beginning or end of a block. The same
     * entry will be included two or more adjacent blocks if it overlaps their
     * respective ends/beginnings.
     * 
     * For example broadcasts A, B, C may be written in blocks as 
     *  <pre>
     *  |A, B| | B | | B, C|
     *  </pre>
     *  if the broadcast B is long enough to cover more than one entire block.  
     * 
     * @param source
     * @param blocks
     * @throws WriteException
     */
    protected abstract void doWrite(Publisher source, List<ChannelSchedule> blocks) throws WriteException;
    
    private void updateItemInContentStore(ItemAndBroadcast entry) throws WriteException {
        Id id = entry.getItem().getId();
        ListenableFuture<Resolved<Content>> resolve = contentStore.resolveIds(ImmutableList.of(id));
        Resolved<Content> resolved2 = Futures.get(resolve,
                10, TimeUnit.SECONDS, WriteException.class);
        Item resolved = (Item) Iterables.getOnlyElement(resolved2.getResources());
        resolved = updateBroadcast(entry.getBroadcast().getSourceId(), resolved);
        contentStore.writeContent(resolved);
    }

    private Item updateBroadcast(String broadcastId, Item resolved) {
        for (Version version : resolved.getVersions()) {
            for (Broadcast broadcast : version.getBroadcasts()) {
                if (broadcastId.equals(broadcast.getSourceId())) {
                    //This will be more fun with an immutable model.
                    broadcast.setIsActivelyPublished(false);
                    return resolved;
                }
            }
        }
        return resolved;
    }

    private <T extends Content> boolean contentChanged(List<WriteResult<? extends Content>> writeResults) {
        return Iterables.any(writeResults, WriteResult.<Content>writtenFilter());
    }

    private List<WriteResult<? extends Content>> writeContent(List<ScheduleHierarchy> contents) {
        ImmutableList.Builder<WriteResult<? extends Content>> results = ImmutableList.builder();
        for (ScheduleHierarchy content : contents) {
            results.addAll(content.writeTo(contentStore));
        }
        return results.build();
    }

    private Publisher getSource(List<ScheduleHierarchy> content) {
        Publisher source = null;
        Iterator<ScheduleHierarchy> contentIter = content.iterator();
        if (contentIter.hasNext()) {
            source = source(contentIter.next());
            while (contentIter.hasNext()) {
                checkSourcesAreEqual(source, source(contentIter.next()));
            }
        }
        return source;
    }

    private Publisher source(ScheduleHierarchy hier) {
        Publisher source = hier.getItemAndBroadcast().getItem().getPublisher();
        if (hier.getPrimaryContainer().isPresent()) {
            checkSourcesAreEqual(source, hier.getPrimaryContainer().get().getPublisher());
        }
        if (hier.getPossibleSeries().isPresent()) {
            checkSourcesAreEqual(source, hier.getPossibleSeries().get().getPublisher());
        }
        return source;
    }

    private void checkSourcesAreEqual(Publisher source, Publisher other) {
        checkArgument(source.equals(other), "Content must be from a single source");
    }

    private boolean broadcastsContiguous(List<ItemAndBroadcast> items) {
        return true;//contiguityCheck.apply(Lists.transform(items, ItemAndBroadcast.toBroadcast()));
    }

    private boolean broadcastHaveIds(List<ItemAndBroadcast> itemsAndBroadcasts) {
        for (ItemAndBroadcast itemAndBroadcast : itemsAndBroadcasts) {
            if (itemAndBroadcast.getBroadcast().getSourceId() == null) {
                return false;
            }
        }
        return true;
    }

    
    private List<ItemAndBroadcast> itemsAndBroadcasts(List<ScheduleHierarchy> hierarchies) {
        List<ItemAndBroadcast> relevantBroadcasts = Lists.newArrayListWithCapacity(hierarchies.size());
        for (ScheduleHierarchy hierarchy : hierarchies) {
            relevantBroadcasts.add(hierarchy.getItemAndBroadcast());
        }
        return relevantBroadcasts;
    }

}
