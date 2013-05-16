package org.atlasapi.media.content.schedule;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nullable;

import org.atlasapi.media.content.Container;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.ContentStore;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Series;
import org.atlasapi.media.util.ItemAndBroadcast;
import org.atlasapi.media.util.WriteResult;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class ScheduleHierarchy {

    public static final ScheduleHierarchy itemOnly(ItemAndBroadcast item) {
        return new ScheduleHierarchy(item, (Container)null, null);
    }
    
    private final ItemAndBroadcast itemAndBroadcast;
    private final Optional<Container> primaryContainer;
    private final Optional<Series> possibleSeries;

    public ScheduleHierarchy(ItemAndBroadcast itemAndBroadcast, @Nullable Container primaryContainer,
            @Nullable Series secondaryContainer) {
        this(checkNotNull(itemAndBroadcast), Optional.fromNullable(primaryContainer), Optional.fromNullable(secondaryContainer));
    }
    
    @SuppressWarnings("unchecked")
    // http://stackoverflow.com/questions/7848789/how-to-use-guava-optional-as-naturally-covariant-object
    public ScheduleHierarchy(ItemAndBroadcast itemAndBroadcast, Optional<? extends Container> primaryContainer, Optional<Series> secondaryContainer) {
        this.itemAndBroadcast = checkNotNull(itemAndBroadcast);
        this.primaryContainer = checkNotNull((Optional<Container>)primaryContainer);
        this.possibleSeries = checkNotNull((Optional<Series>)secondaryContainer);
    }

    public ItemAndBroadcast getItemAndBroadcast() {
        return this.itemAndBroadcast;
    }

    public Optional<Container> getPrimaryContainer() {
        return this.primaryContainer;
    }

    public Optional<Series> getPossibleSeries() {
        return this.possibleSeries;
    }
    
    List<WriteResult<? extends Content>> writeTo(ContentStore store) {
        List<WriteResult<? extends Content>> results = Lists.newArrayListWithCapacity(3);
        WriteResult<Container> primaryContainerResult = null;
        if (primaryContainer.isPresent()) {
            primaryContainerResult = store.writeContent(primaryContainer.get());
            results.add(primaryContainerResult);
        }
        WriteResult<Series> secondaryContainerResult = null;
        if (possibleSeries.isPresent()) {
            if (primaryContainerResult != null && primaryContainerResult.getResource() instanceof Brand) {
                possibleSeries.get().setParent((Brand)primaryContainer.get());
            }
            secondaryContainerResult = store.writeContent(possibleSeries.get());
            results.add(secondaryContainerResult);
        }
        Item item = itemAndBroadcast.getItem();
        if (primaryContainerResult != null) {
            item.setContainer(primaryContainerResult.getResource());
        }
        if (secondaryContainerResult != null && item instanceof Episode) {
            ((Episode)item).setSeries(secondaryContainerResult.getResource());
        }
        results.add(store.writeContent(item));
        return results;
    }
 
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ScheduleHierarchy) {
            ScheduleHierarchy other = (ScheduleHierarchy) that;
            return itemAndBroadcast.equals(other.itemAndBroadcast)
                && primaryContainer.equals(other.primaryContainer)
                && possibleSeries.equals(other.possibleSeries);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return itemAndBroadcast.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(getClass()).omitNullValues()
                .add("item", itemAndBroadcast)
                .add("primary", primaryContainer.orNull())
                .add("secondary", possibleSeries.orNull())
                .toString();
    }

}
