package org.atlasapi.application;

import java.util.List;
import java.util.Map;

import org.atlasapi.application.SourceStatus.SourceState;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class Application {

    private final Id id;
    private final String slug; // Kept to enable creation of compatible entries
                               // for 3.0
    private final String title;
    private final DateTime created;
    private final ApplicationCredentials credentials;
    private final ApplicationSources sources;

    private Application(Id id, 
            String slug, 
            String title, 
            DateTime created, 
            ApplicationCredentials credentials, 
            ApplicationSources sources) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.created = created;
        this.credentials = credentials;
        this.sources = sources;
    }

    public Id getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getCreated() {
        return created;
    }

    public ApplicationCredentials getCredentials() {
        return credentials;
    }

    public ApplicationSources getSources() {
        return sources;
    }
    
    public Application disablePrecendence() {
        ApplicationSources modifiedSources = this
               .getSources().copy().withPrecedence(false).build();
        return this.copy().withSources(modifiedSources).build();
    }
    
    public Application addWrites(Publisher source) {
        List<Publisher> writes = Lists.newArrayList(this.getSources().getWrites());
        if (!writes.contains(source)) {
            writes.add(source);
        }
        ApplicationSources modifiedSources = this
                    .getSources().copy().withWrites(writes).build();
        return this.copy().withSources(modifiedSources).build();
    }
    
    public Application removeWrites(Publisher source) {
        List<Publisher> writes = Lists.newArrayList(this.getSources().getWrites());
        writes.remove(source);
        ApplicationSources modifiedSources = this
                    .getSources().copy().withWrites(writes).build();
        return this.copy().withSources(modifiedSources).build();
    }
    
    public Application replaceSources(ApplicationSources sources) {
        return this.copy().withSources(sources).build();
    }
    
    public Application changeReadSourceState(Publisher source, SourceState sourceState) {
        SourceStatus status = findSourceStatusFor(source, this.getSources().getReads());
        SourceStatus newStatus = status.copyWithState(sourceState);
        return modifyReadSourceStatus(source, newStatus);
    }

    public Application enableSource(Publisher source) {
        SourceStatus status = findSourceStatusFor(source, this.getSources().getReads());
        status = status.enable();
        return modifyReadSourceStatus(source, status);
    }
    
    public Application disableSource(Publisher source) {
        SourceStatus status = findSourceStatusFor(source, this.getSources().getReads());
        status = status.disable();
        return modifyReadSourceStatus(source, status);
    }
    
    private Application modifyReadSourceStatus(Publisher source,
            SourceStatus status) {
        List<SourceReadEntry> modifiedReads = changeReadsPreservingOrder(
                this.getSources().getReads(), source, status);
        ApplicationSources modifiedSources = this.getSources().copy()
                .withReads(modifiedReads).build();
        return this.copy().withSources(modifiedSources).build();
    }
    
    private SourceStatus findSourceStatusFor(Publisher source, List<SourceReadEntry> reads) {
        for (SourceReadEntry status : reads) {
            if (status.getPublisher().equals(source)) {
                return status.getSourceStatus();
            }
        }
        return null;
    }
    
    private List<SourceReadEntry> changeReadsPreservingOrder(
            List<SourceReadEntry> original,
            Publisher sourceToChange,
            SourceStatus newSourceStatus) {
        ImmutableList.Builder<SourceReadEntry> builder = ImmutableList.builder();
        for (SourceReadEntry source : original) {
            if (source.getPublisher().equals(sourceToChange)) {
                builder.add(new SourceReadEntry(source.getPublisher(), newSourceStatus));
            } else {
                builder.add(source);
            }
        }
        return builder.build();
    }
    
    public Application setPrecendenceOrder(List<Publisher> ordering) {
        Map<Publisher, SourceReadEntry> sourceMap = getSourceReadsAsKeyedMap();
        List<Publisher> seen = Lists.newArrayList();
        List<SourceReadEntry> readsWithNewOrder = Lists.newArrayList();
        for (Publisher source : ordering) {
            readsWithNewOrder.add(sourceMap.get(source));
            seen.add(source);
        }
        // add sources omitted from ordering
        for (Publisher source: sourceMap.keySet()) {
            if (!seen.contains(source)) {
               readsWithNewOrder.add(sourceMap.get(source));
            }
        }
        ApplicationSources modifiedSources = this
                    .getSources().copy()
                    .withPrecedence(true)
                    .withReads(readsWithNewOrder)
                    .build();
            
        return this.copy().withSources(modifiedSources).build();
    }
    
    private Map<Publisher, SourceReadEntry> getSourceReadsAsKeyedMap() {
        ImmutableMap.Builder<Publisher, SourceReadEntry> sourceMap = ImmutableMap.builder();
        for (SourceReadEntry read : this.getSources().getReads()) {
            sourceMap.put(read.getPublisher(), read);
        }
        return sourceMap.build();
    }
    
    public Builder copy() {
        return builder()
                .withId(this.getId())
                .withSlug(this.getSlug())
                .withTitle(this.getTitle())
                .withCreated(this.getCreated())
                .withCredentials(this.getCredentials())
                .withSources(this.getSources());
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Id id;
        private String slug;
        private String title;
        private DateTime created;
        private ApplicationCredentials credentials;
        private ApplicationSources sources;

        public Builder() {

        }

        public Builder withId(Id id) {
            this.id = id;
            return this;
        }

        public Builder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withCreated(DateTime created) {
            this.created = created;
            return this;
        }

        public Builder withCredentials(ApplicationCredentials credentials) {
            this.credentials = credentials;
            return this;
        }

        public Builder withSources(ApplicationSources sources) {
            this.sources = sources;
            return this;
        }

        public Application build() {
            return new Application(id, slug, title, created, credentials, sources);
        }
    }

}
