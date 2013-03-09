package org.atlasapi.equiv;

import static com.google.common.base.Preconditions.checkNotNull;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;
import org.atlasapi.media.common.Sourced;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.time.DateTimeZones;

public class EquivalenceRecord implements Identifiable, Sourced {

    public static final <T extends Identifiable&Sourced> EquivalenceRecord valueOf(T t) {
        EquivalenceRef ref = EquivalenceRef.valueOf(t);
        ImmutableSet<EquivalenceRef> reflexiveSet = ImmutableSet.of(ref);
        DateTime now = new DateTime(DateTimeZones.UTC);
        return new EquivalenceRecord(ref,
                reflexiveSet, reflexiveSet, reflexiveSet, now, now);
    }

    private final EquivalenceRef self;

    private final ImmutableSet<EquivalenceRef> generatedAdjacents;
    private final ImmutableSet<EquivalenceRef> explicitAdjacents;
    private final ImmutableSet<EquivalenceRef> equivalents;

    private final DateTime created;
    private final DateTime updated;

    public EquivalenceRecord(EquivalenceRef self,
            Iterable<EquivalenceRef> generatedAdjacents,
            Iterable<EquivalenceRef> explicitAdjacents,
            Iterable<EquivalenceRef> equivalents, DateTime created, DateTime updated) {
        this.self = checkNotNull(self);
        this.generatedAdjacents = ImmutableSet.copyOf(generatedAdjacents);
        this.explicitAdjacents = ImmutableSet.copyOf(explicitAdjacents);
        this.equivalents = ImmutableSet.copyOf(equivalents);
        this.created = checkNotNull(created);
        this.updated = checkNotNull(updated);
    }

    public Id getId() {
        return this.self.getId();
    }

    public Publisher getPublisher() {
        return this.self.getPublisher();
    }
    
    public EquivalenceRef getSelf() {
        return self;
    }

    public ImmutableSet<EquivalenceRef> getGeneratedAdjacents() {
        return this.generatedAdjacents;
    }

    public ImmutableSet<EquivalenceRef> getExplicitAdjacents() {
        return this.explicitAdjacents;
    }

    public ImmutableSet<EquivalenceRef> getEquivalents() {
        return this.equivalents;
    }

    public DateTime getCreated() {
        return this.created;
    }

    public DateTime getUpdated() {
        return this.updated;
    }
    
    public EquivalenceRecord copyWithGeneratedAdjacent(Iterable<EquivalenceRef> adjacent) {
        ImmutableSet<EquivalenceRef> refs = ImmutableSet.<EquivalenceRef>builder()
                .add(self).addAll(adjacent).build();
        return new EquivalenceRecord(self, refs, explicitAdjacents, equivalents, created, new DateTime(DateTimeZones.UTC));
    }
    
    public EquivalenceRecord copyWithExplicitAdjacent(Iterable<EquivalenceRef> adjacent) {
        ImmutableSet<EquivalenceRef> refs = ImmutableSet.<EquivalenceRef>builder()
                .add(self).addAll(adjacent).build();
        return new EquivalenceRecord(self, generatedAdjacents, refs, equivalents, created, new DateTime(DateTimeZones.UTC));
    }
    
    public EquivalenceRecord copyWithEquivalents(Iterable<EquivalenceRef> equivalents) {
        ImmutableSet<EquivalenceRef> refs = ImmutableSet.<EquivalenceRef>builder()
                .add(self).addAll(equivalents).build();
        return new EquivalenceRecord(self, generatedAdjacents, explicitAdjacents, refs, created, new DateTime(DateTimeZones.UTC));
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof EquivalenceRecord) {
            EquivalenceRecord other = (EquivalenceRecord) that;
            return this.self.equals(other.self);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return self.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add(self.getPublisher().toString(), self.getId())
                .toString();
    }

    public static Function<EquivalenceRecord, EquivalenceRef> toSelf() {
        return ToSelfFunction.INSTANCE;
    }
    
    private enum ToSelfFunction implements Function<EquivalenceRecord, EquivalenceRef> {
        INSTANCE;
        @Override
        public EquivalenceRef apply(EquivalenceRecord input) {
            return input.getSelf();
        }
    }
    
}
