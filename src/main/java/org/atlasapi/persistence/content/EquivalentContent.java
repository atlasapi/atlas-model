package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.Content;

import com.google.common.collect.ForwardingSetMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;


public class EquivalentContent extends ForwardingSetMultimap<String, Content> {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ImmutableSetMultimap.Builder<String,Content> entries = ImmutableSetMultimap.builder();

        public void putEquivalents(String key, Iterable<Content> equivalentSet) {
            this.entries.putAll(key, equivalentSet);
        }

        public EquivalentContent build() {
            return new EquivalentContent(entries.build());
        }
    }

    private SetMultimap<String, Content> entries;
    
    private EquivalentContent(SetMultimap<String, Content> entries) {
        this.entries = ImmutableSetMultimap.copyOf(entries);
    }

    @Override
    protected SetMultimap<String, Content> delegate() {
        return entries;
    }

    private static final EquivalentContent EMPTY_INSTANCE 
            = new EquivalentContent(ImmutableSetMultimap.<String,Content>of());
    
    public static EquivalentContent empty() {
        return EMPTY_INSTANCE;
    }
}