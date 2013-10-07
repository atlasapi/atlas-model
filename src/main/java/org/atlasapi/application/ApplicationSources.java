package org.atlasapi.application;

import java.util.List;

import javax.annotation.Nullable;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

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
    
    public static final ApplicationSources EMPTY_SOURCES = ApplicationSources
            .builder()
            .withPrecedence(false)
            .withReads(ImmutableList.<SourceReadEntry>of())
            .withWrites(ImmutableList.<Publisher>of())
            .build();
    
    private static final Function<SourceReadEntry, Publisher> READ_TO_PUBLISHER =  new Function<SourceReadEntry, Publisher>() {

        @Override
        public Publisher apply(@Nullable SourceReadEntry input) {
            return input.getPublisher();
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

    public ImmutableSet<Publisher> getEnabledReadSources() {
        return ImmutableSet.copyOf(
                Iterables.transform(
                        Iterables.filter(this.getReads(), ENABLED_READS_FILTER), 
                        SOURCEREADS_TO_PUBLISHER));
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
            this.reads = reads;
            return this;
        }

        public Builder withWrites(List<Publisher> writes) {
            this.writes = writes;
            return this;
        }

        public ApplicationSources build() {
            return new ApplicationSources(this);
        }
    }

}
