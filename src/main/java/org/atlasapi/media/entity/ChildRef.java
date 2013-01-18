package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;
import org.joda.time.DateTime;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

public class ChildRef implements Identifiable, Comparable<ChildRef> {

    public static List<ChildRef> dedupeAndSort(Iterable<ChildRef> childRefs) {
        return NATURAL.immutableSortedCopy(ImmutableSet.copyOf(childRefs));
    }

    private static final Ordering<ChildRef> NATURAL = Ordering.natural().reverse();

    private final Id id;
    private final String sortKey;
    private final DateTime updated;
	private final EntityType type;
	
    public ChildRef(long id, String sortKey, DateTime updated, EntityType type) {
        this(Id.valueOf(id), sortKey, updated, type);
    }
    
    public ChildRef(Id id, String sortKey, DateTime updated, EntityType type) {
        this.id = checkNotNull(id);
        this.sortKey =  checkNotNull(sortKey);
        this.type = checkNotNull(type);
        this.updated = checkNotNull(updated);
    }
    
    public Id getId() {
        return id;
    }
    
    public String getSortKey() {
        return sortKey;
    }
    
    public DateTime getUpdated() {
        return updated;
    }
    
    public EntityType getType() {
		return type;
	}
    
    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }
        if(that instanceof ChildRef) {
            ChildRef other = (ChildRef) that;
            return id.equals(other.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int compareTo(ChildRef comparableTo) {
        return ComparisonChain.start()
            .compare(sortKey, comparableTo.sortKey)
            .compare(id, comparableTo.id)
            .result();
    }

}
