package org.atlasapi.media.content;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Film;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Item.ContainerSummary;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Series;
import org.atlasapi.media.entity.SeriesRef;
import org.atlasapi.media.entity.Song;
import org.atlasapi.media.util.WriteResult;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.ids.IdGenerator;
import com.metabroadcast.common.time.Clock;

public abstract class AbstractContentStore implements ContentStore {

    private final class ContentWritingVisitor implements ContentVisitor<WriteResult<? extends Content>> {

        private boolean hashChanged(Content writing, Content previous) {
            return !hasher.hash(writing).equals(hasher.hash(previous));
        }
        
        private void updateTimes(Content content) {
            DateTime now = clock.now();
            if (content.getFirstSeen() == null) {
                content.setFirstSeen(now);
            }
            content.setLastUpdated(now);
            content.setThisOrChildLastUpdated(now);
        }

        private void updateWithPevious(Content writing, Content previous) {
            writing.setId(previous.getId());
            writing.setFirstSeen(previous.getFirstSeen());
            updateTimes(writing);
        }
        
        @Override
        public WriteResult<Brand> visit(Brand brand) {
            Brand previous = (Brand) getPreviousContent(brand);

            brand.setChildRefs(ImmutableSet.<ChildRef>of());
            brand.setSeriesRefs(ImmutableSet.<SeriesRef>of());
            
            if (previous != null) {
                return writeBrandWithPrevious(brand, previous);
            }

            updateTimes(brand);
            write(brand);
            
            return WriteResult.written(brand).build();
            
        }

        private WriteResult<Brand> writeBrandWithPrevious(Brand brand, Brand previous) {
            boolean written = false;
            if (hashChanged(brand, previous)) {
                updateWithPevious(brand, previous);
                write(brand);
                written = true;
            } 
            brand.setChildRefs(previous.getChildRefs());
            brand.setSeriesRefs(previous.getSeriesRefs());
            return WriteResult.result(brand, written)
                .withPrevious(previous)
                .build();
        }

        @Override
        public WriteResult<Series> visit(Series series) {
            Series previous = (Series) getPreviousContent(series);
            
            series.setChildRefs(ImmutableSet.<ChildRef>of());
            if (previous != null) {
                return writeSeriesWithPrevious(series, previous);
            }
            updateTimes(series);
            writeRefAndSummarizePrimary(series);
            write(series);
            return WriteResult.written(series).build();
        }

        private WriteResult<Series> writeSeriesWithPrevious(Series series, Series previous) {
            boolean written = false;
            if (hashChanged(series, previous)) {
                updateWithPevious(series, previous);
                writeRefAndSummarizePrimary(series);
                write(series);
                written = true;
            }
            series.setChildRefs(previous.getChildRefs());
            return WriteResult.result(series, written)
                .withPrevious(previous)
                .build();
        }
        
        private void writeRefAndSummarizePrimary(Series series) {
            if (series.getParent() != null) {
                ParentRef primary = series.getParent();
                //TODO set summary on series
                ContainerSummary summarize = getSummary(primary);
                ensureId(series);
                writeSecondaryContainerRef(primary, series.childRef());
            }
        }
        
        @Override
        public WriteResult<Item> visit(Item item) {
            Item previous = (Item) getPreviousContent(item);
            
            if (previous != null) {
                return writeItemWithPrevious(item, previous);
            }
            updateTimes(item);
            writeRefAndSummarizeContainer(item);
            write(item);
            return WriteResult.written(item)
                .build();
        }

        private WriteResult<Item> writeItemWithPrevious(Item item, Item previous) {
            boolean written = false;
            if (hashChanged(item, previous)) {
                updateWithPevious(item, previous);
                writeRefAndSummarizeContainer(item);
                write(item);
                written = true;
            } 
            return WriteResult.result(item, written)
                .withPrevious(previous)
                .build();
        }

        private void writeRefAndSummarizeContainer(Item item) {
            if (item.getContainer() != null) {
                ParentRef containerRef = item.getContainer();
                item.setContainerSummary(getSummary(containerRef));
                ensureId(item);
                writeChildRef(containerRef, item.childRef());
            }
        }

        @Override
        public WriteResult<Episode> visit(Episode episode) {
            checkArgument(episode.getContainer() != null, 
                    "can't write episode with null container");
            
            Episode previous = (Episode) getPreviousContent(episode);
            
            if (previous != null) {
                return writeEpisodeWithExising(episode, previous);
            }
            updateTimes(episode);
            writeRefsAndSummarizeContainers(episode);
            write(episode);
            return WriteResult.written(episode).build();
        }

        private WriteResult<Episode> writeEpisodeWithExising(Episode episode, Episode previous) {
            boolean written = false;
            if (hashChanged(episode, previous)) {
                updateWithPevious(episode, previous);
                writeRefsAndSummarizeContainers(episode);
                write(episode);
                written = true;
            } 
            return WriteResult.result(episode, written)
                .withPrevious(previous)
                .build();
        }
        
        private void writeRefsAndSummarizeContainers(Episode episode) {
            ParentRef primaryContainer = episode.getContainer();
            episode.setContainerSummary(getSummary(primaryContainer));

            ChildRef childRef = null;
            if (episode.getSeriesRef() != null) {
                ParentRef secondaryContainer = episode.getSeriesRef();
                //TODO set series summary on episode
                ContainerSummary summary = getSummary(secondaryContainer);
                ensureId(episode);
                childRef = episode.childRef();
                writeChildRef(secondaryContainer, childRef);
            }
            ensureId(episode);
            childRef = childRef == null ? episode.childRef() : childRef;
            writeChildRef(primaryContainer, childRef);
        }

        @Override
        public WriteResult<Film> visit(Film film) {
            Film previous = (Film) getPreviousContent(film);
            if (previous != null) {
                return writeFilmWithPrevious(film, previous);
            }
            updateTimes(film);
            write(film);
            return WriteResult.written(film).build();
        }

        private WriteResult<Film> writeFilmWithPrevious(Film film, Film previous) {
            boolean written = false;
            if (hashChanged(film, previous)) {
                updateWithPevious(film, previous);
                write(film);
                written = true;
            }
            return WriteResult.result(film, written)
                .withPrevious(previous)
                .build();
        }

        @Override
        public WriteResult<Song> visit(Song song) {
            Song previous = (Song) getPreviousContent(song);
            
            if (previous != null) {
                return writeSongWithPrevious(song, previous);
            }
            
            updateTimes(song);
            write(song);
            return WriteResult.written(song)
                .build();
        }
        
        private WriteResult<Song> writeSongWithPrevious(Song song, Song previous) {
            boolean written = false;
            if (hashChanged(song, previous)) {
                updateWithPevious(song, previous);
                write(song);
                written = true;
            }
            return WriteResult.result(song, written)
                .withPrevious(previous)
                .build();
        }

        @Override
        public WriteResult<Clip> visit(Clip clip) {
            throw new UnsupportedOperationException("Can't yet write Clips top-level");
        }
    }
    
    private final ContentHasher hasher;
    private final IdGenerator idGenerator;
    private final Clock clock;

    private final ContentWritingVisitor writingVisitor;
    
    public AbstractContentStore(ContentHasher hasher, IdGenerator idGenerator, Clock clock) {
        this.hasher = checkNotNull(hasher);
        this.idGenerator = checkNotNull(idGenerator);
        this.clock = checkNotNull(clock);
        this.writingVisitor = new ContentWritingVisitor();
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <C extends Content> WriteResult<C> writeContent(C content) {
        checkNotNull(content, "write null content");
        checkNotNull(content.getPublisher(), "write unsourced content");
        
        return (WriteResult<C>)content.accept(writingVisitor);
    }

    private Content getPreviousContent(Content c) {
        Content previous = null ;
        if (c.getId() != null) {
            previous = resolveId(c.getId());
        }
        
        if (previous == null) {
            previous = resolveAliases(c);
        }
        return previous;
    }

    private Content resolveAliases(Content c) {
        Content previous = null;
        Iterator<String> aliases = c.getAliases().iterator();
        while (previous == null && aliases.hasNext()) {
            previous = resolveAlias(aliases.next(), c.getPublisher());
        }
        return previous;
    }

    protected abstract @Nullable Content resolveAlias(String alias, Publisher source);

    protected abstract @Nullable Content resolveId(Id id);

    private void write(Content content) {
        ensureId(content);
        doWriteContent(content);
    }

    private void ensureId(Content content) {
        if(content.getId() == null) {
            content.setId(Id.valueOf(idGenerator.generateRaw()));
        }
    }
    
    protected abstract void doWriteContent(Content content);

    private final ContainerSummary getSummary(ParentRef primary) {
        ContainerSummary summary = summarize(primary);
        if (summary != null) {
            return summary;
        }
        throw new IllegalStateException("Missing container " + primary);
    }
    
    protected abstract ContainerSummary summarize(ParentRef primary);

    /**
     * Add a ref to the series in the primary container and update its
     * thisOrChildLastUpdated time.
     * 
     * @param primary
     * @param series
     */
    protected abstract void writeSecondaryContainerRef(ParentRef primary, ChildRef seriesRef);

    /**
     * Add a ref to the child in the container and update its
     * thisOrChildLastUpdated time.
     * 
     * @param containerId
     * @param child
     */
    protected abstract void writeChildRef(ParentRef containerId, ChildRef childRef);
}
