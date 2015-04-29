package org.atlasapi.persistence.content;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.atlasapi.media.entity.Identified;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.metabroadcast.common.base.Maybe;

public class ResolvedContent {
    
    public static class ResolvedContentBuilder {
        
        private ImmutableMap.Builder<String, Maybe<Identified>> builder = ImmutableMap.builder();
        
        public ResolvedContentBuilder put(String q, Identified r) {
            builder.put(q, Maybe.fromPossibleNullValue(r));
            return this;
        }
        
        public ResolvedContentBuilder putAll(Map<String, ? extends Identified> results) {
            for (Entry<String, ? extends Identified> result : results.entrySet()) {
                put(result.getKey(), result.getValue());
            }
            return this;
        }
        
        public ResolvedContent build() {
            return new ResolvedContent(builder.build());
        }
        
    }
    
    public static ResolvedContentBuilder builder() {
        return new ResolvedContentBuilder();
    }

    private final Map<String, Maybe<Identified>> map;

    public ResolvedContent(Map<String, Maybe<Identified>> map) {
        this.map = map;
    }

    public boolean resolvedAll() {
        return Iterables.size(Maybe.filterValues(map.values())) == map.size();
    }
    
    public boolean resolved(String query) {
        Maybe<Identified> result = map.get(query);
        return result != null && result.hasValue();
    }

    public Maybe<Identified> get(String query) {
        if (map.containsKey(query)) {
            return map.get(query);
        }
        return Maybe.nothing();
    }

    public Set<String> getQueries() {
        return map.keySet();
    }

    public List<Identified> getResolvedResults(Iterable<String> queries) {
        List<Identified> resolvedResults = Lists.newArrayList();

        for (String query : queries) {
            Maybe<Identified> result = map.get(query);
            if (result != null && result.hasValue()) {
                resolvedResults.add(result.requireValue());
            }
        }

        return resolvedResults;
    }

    public List<Identified> getAllResolvedResults() {
        return ImmutableList.copyOf(Maybe.filterValues(map.values()));
    }

    public List<String> getUnresolved() {
        return ImmutableList.copyOf(Maps.filterValues(map, Predicates.not(Maybe.HAS_VALUE)).keySet());
    }

    public List<String> getResolvedQueries() {
        return ImmutableList.copyOf(Maps.filterValues(map, Maybe.HAS_VALUE).keySet());
    }

    public Maybe<Identified> getFirstValue() {
        if (map.isEmpty()) {
            return Maybe.nothing();
        }
        return Iterables.get(map.values(), 0);
    }

    public Map<String, Maybe<Identified>> asMap() {
        return ImmutableMap.copyOf(map);
    }

    public Map<String, Identified> asResolvedMap() {
        return ImmutableMap.copyOf(Maps.transformValues(Maps.filterValues(map, Maybe.HAS_VALUE), Maybe.<Identified> requireValueFunction()));
    }
    
    public ResolvedContent filterContent(Predicate<Maybe<Identified>> filter) {
        ImmutableMap.Builder<String, Maybe<Identified>> filtered = ImmutableMap.builder();
        
        for (Entry<String, Maybe<Identified>> entry: map.entrySet()) {
            
            Maybe<Identified> value = filter.apply(entry.getValue()) ? entry.getValue() : Maybe.<Identified>nothing();
            filtered.put(entry.getKey(), value);
        }
        
        return new ResolvedContent(filtered.build());
    }

    public boolean isEmpty() {
        return map.isEmpty() || getAllResolvedResults().isEmpty();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResolvedContent) {
            ResolvedContent target = (ResolvedContent) obj;
            return map.equals(target.map);
        }
        return false;
    }

	public ResolvedContent copyWithAllRequestedUris(Iterable<? extends String> uris) {
		ResolvedContentBuilder builder = ResolvedContent.builder();
		for (String uri : uris) {
			Maybe<Identified> result = map.get(uri);
			builder.put(uri, result == null ? null : result.valueOrNull());
		}
		return builder.build();
	}
}
