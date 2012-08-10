package org.atlasapi.persistence.content;

import java.util.Map;
import java.util.Set;

import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.LookupRef;

import com.google.common.base.Predicates;
import com.google.common.collect.ForwardingSetMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;


public class EquivalentContent extends ForwardingSetMultimap<String, Content> {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ImmutableSetMultimap.Builder<String,Content> entries = ImmutableSetMultimap.builder();

        public void putEquivalents(String key, Iterable<Content> equivalentSet) {
            this.entries.putAll(key, setEquivalentToFields(equivalentSet));
        }

        public EquivalentContent build() {
            return new EquivalentContent(entries.build());
        }
        
        private Iterable<Content> setEquivalentToFields(Iterable<Content> contents) {
            Map<String,LookupRef> refMap = Maps.uniqueIndex(Iterables.transform(contents, LookupRef.FROM_DESCRIBED), LookupRef.TO_ID);
            Set<LookupRef> allRefs = ImmutableSet.copyOf(refMap.values());

            Set<Content> equivContents = Sets.newHashSetWithExpectedSize(refMap.size());
            for (Content content : contents) {
                LookupRef ref = refMap.get(content.getCanonicalUri());
                Set<LookupRef> equivs = Sets.filter(allRefs, Predicates.not(Predicates.equalTo(ref)));
                content.setEquivalentTo(ImmutableSet.copyOf(equivs));
                equivContents.add(content);
            }
            return equivContents;
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
