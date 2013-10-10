package org.atlasapi.application;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Sourced;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Sourceds;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

public class ApplicationSources {

    private final boolean precedence;
    private final List<SourceReadEntry> reads;
    private final List<Publisher> writes;
    
    private static final Predicate<SourceReadEntry> ENABLED_READS_FILTER = new Predicate<SourceReadEntry>() {
        @Override
         public boolean apply(@Nullable SourceReadEntry input) {
             return input.getSourceStatus().isEnabled();
         }
        };
    
    private static final Function<SourceReadEntry, Publisher> SOURCEREADS_TO_PUBLISHER = new Function<SourceReadEntry, Publisher>() {
            @Override
            public Publisher apply(@Nullable SourceReadEntry input) {
                return input.getPublisher();
            }
           };
    
    private static final Function<SourceReadEntry, Publisher> READ_TO_PUBLISHER =  new Function<SourceReadEntry, Publisher>() {

        @Override
        public Publisher apply(@Nullable SourceReadEntry input) {
            return input.getPublisher();
        }};
        
    private static final Comparator<SourceReadEntry> SORT_READS_BY_PUBLISHER = new Comparator<SourceReadEntry>() {
        @Override
        public int compare(SourceReadEntry a, SourceReadEntry b) {
            return a.getPublisher().compareTo(b.getPublisher());
        }};

    private ApplicationSources(Builder builder) {
        this.precedence = builder.precedence;
        this.reads = ImmutableList.copyOf(builder.reads);
        this.writes = ImmutableList.copyOf(builder.writes);
    }

    public boolean isPrecedenceEnabled() {
        return precedence;
    }

    public List<SourceReadEntry> getReads() {
        return reads;
    }

    public List<Publisher> getWrites() {
        return writes;
    }
    
    public Ordering<Publisher> publisherPrecedenceOrdering() {
        return Ordering.explicit(Lists.transform(reads, READ_TO_PUBLISHER));
    }
    
    private ImmutableList<Publisher> peoplePrecedence() {
        return ImmutableList.of(Publisher.RADIO_TIMES, Publisher.PA, Publisher.BBC, Publisher.C4, Publisher.ITV);
    }
    
    public boolean peoplePrecedenceEnabled() {
        return peoplePrecedence() != null;
    }
    
    public Ordering<Publisher> peoplePrecedenceOrdering() {
        // Add missing publishers
        List<Publisher> publishers = Lists.newArrayList(peoplePrecedence());
        for (Publisher publisher : Publisher.values()) {
            if (!publishers.contains(publisher)) {
                publishers.add(publisher);
            }
        }
        return Ordering.explicit(publishers);
    }
    
    public Ordering<Sourced> getSourcedPeoplePrecedenceOrdering() {
        return peoplePrecedenceOrdering().onResultOf(Sourceds.toPublisher());
    }
    
    /**
     * Temporary: these should be persisted and not hardcoded
     */
    private ImmutableList<Publisher> imagePrecedence() {
        return ImmutableList.of(Publisher.PA, Publisher.BBC, Publisher.C4);
    }
    
    public boolean imagePrecedenceEnabled() {
        return imagePrecedence() != null;
    }
    
    public Ordering<Publisher> imagePrecedenceOrdering() {
        return publisherPrecedenceOrdering();
    }
    
    public Ordering<Sourced> getSourcedImagePrecedenceOrdering() {
        return imagePrecedenceOrdering().onResultOf(Sourceds.toPublisher());
    }

    public ImmutableSet<Publisher> getEnabledReadSources() {
        return ImmutableSet.copyOf(
                Iterables.transform(
                        Iterables.filter(this.getReads(), ENABLED_READS_FILTER), 
                        SOURCEREADS_TO_PUBLISHER));
    }

    public boolean isReadEnabled(Publisher source) {
        return this.getEnabledReadSources().contains(source);
    }
    
    public boolean isWriteEnabled(Publisher source) {
        return this.getWrites().contains(source);
    }
    
    public Ordering<Sourced> getSourcedReadOrdering() {
        Ordering<Publisher> ordering = ApplicationSources.defaults().publisherPrecedenceOrdering();
        return ordering.onResultOf(Sourceds.toPublisher());
    }
    
    // Build a default configuration, this will get popualated with publishers 
    // with default source status
    public static ApplicationSources defaults() {
        return ApplicationSources.builder()
                  .withPrecedence(false)
                  .withReads(ImmutableList.<SourceReadEntry>of())
                  .withWrites(ImmutableList.<Publisher>of())
                  .build();
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ApplicationSources) {
            ApplicationSources other = (ApplicationSources) obj;
            if (this.isPrecedenceEnabled() == other.isPrecedenceEnabled()) {
                boolean readsEqual = Objects.equal(this.getReads(), other.getReads());
                boolean writesEqual = this.getWrites().containsAll(other.getWrites())
                        && this.getWrites().size() == other.getWrites().size();
                return readsEqual && writesEqual;
            }
        }
        return false;
    }

    public Builder copy() {
        return builder()
                .withPrecedence(this.isPrecedenceEnabled())
                .withReads(this.getReads())
                .withWrites(this.getWrites());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public boolean precedence;
        private List<SourceReadEntry> reads;
        private List<Publisher> writes;

        public Builder withPrecedence(boolean precedence) {
            this.precedence = precedence;
            return this;
        }

        public Builder withReads(List<SourceReadEntry> reads) {
            List<SourceReadEntry> readsAll = Lists.newLinkedList();
            Set<Publisher> publishersSeen = Sets.newHashSet();
            for (SourceReadEntry read : reads) {
                readsAll.add(read);
                publishersSeen.add(read.getPublisher());
            }            
            // populate missing publishers
            for (Publisher source : Publisher.values()) {
                if (!publishersSeen.contains(source)) {
                    readsAll.add(new SourceReadEntry(source, source.getDefaultSourceStatus()));
                }
            }
            this.reads = readsAll;
            return this;
        }

        public Builder withWrites(List<Publisher> writes) {
            this.writes = writes;
            return this;
        }

        public ApplicationSources build() {
            // If precedence not enabled then sort reads by publisher key order
            if (!this.precedence) {
                Collections.sort(this.reads, SORT_READS_BY_PUBLISHER);
            }
            return new ApplicationSources(this);
        }
    }
}
