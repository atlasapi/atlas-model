package org.atlasapi.persistence.content;

import java.util.Map;
import java.util.Set;

import org.atlasapi.media.entity.Content;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;


public class EquivalentContent /* extends ForwardingMap? */ {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ImmutableMap.Builder<String, Set<Content>> entries = ImmutableMap.builder();

        public void put(String uri, Set<Content> equivalentSet) {
            this.entries.put(uri, ImmutableSet.copyOf(equivalentSet));
        }

        public EquivalentContent build() {
            return new EquivalentContent(entries.build());
        }
        
    }

    private Map<String,Set<Content>> entries;
    
    public EquivalentContent(ImmutableMap<String, Set<Content>> entries) {
        this.entries = entries;
    }

    public Set<Content> equivalentContentFor(String uri) {
        return entries.get(uri);
    }
}
