package org.atlasapi.equiv;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.util.Identifiables;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.ForwardingSetMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

/**
 * Represents a group of resolved sets of equivalents. 
 *
 * @param <E>
 */
public class ResolvedEquivalents<E extends Equivalent<E>> extends ForwardingSetMultimap<Id, E> {

    public static <E extends Equivalent<E>> Builder<E> builder() {
        return new Builder<E>();
    }

    public static class Builder<E extends Equivalent<E>> {

        private ImmutableSetMultimap.Builder<Id,E> entries = ImmutableSetMultimap.builder();

        public Builder<E> putEquivalents(Id key, Iterable<E> equivalentSet) {
            this.entries.putAll(key, setEquivalentToFields(equivalentSet));
            return this;
        }

        public ResolvedEquivalents<E> build() {
            return new ResolvedEquivalents<E>(entries.build());
        }
        
        private Iterable<E> setEquivalentToFields(Iterable<E> equivalents) {
            Map<Id, EquivalenceRef> refMap = Maps.uniqueIndex(Iterables.transform(equivalents,
                    new Function<Equivalent<?>, EquivalenceRef>() {
                        @Override
                        public EquivalenceRef apply(Equivalent<?> input) {
                            return EquivalenceRef.valueOf(input);
                        }
                    }), Identifiables.toId());
            Set<EquivalenceRef> allRefs = ImmutableSet.copyOf(refMap.values());

            ImmutableSet.Builder<E> equivContents = ImmutableSet.builder();
            for (E equivalent : equivalents) {
                EquivalenceRef ref = refMap.get(equivalent.getId());
                Set<EquivalenceRef> equivs = Sets.filter(allRefs, Predicates.not(Predicates.equalTo(ref)));
                equivContents.add(equivalent.copyWithEquivalentTo(ImmutableSet.copyOf(equivs)));
            }
            return equivContents.build();
        }

    }

    private SetMultimap<Id, E> entries;
    
    private ResolvedEquivalents(SetMultimap<Id, E> entries) {
        this.entries = ImmutableSetMultimap.copyOf(entries);
    }

    @Override
    protected SetMultimap<Id, E> delegate() {
        return entries;
    }
    
    @Override
    public ImmutableSet<E> get(@Nullable Id key) {
        return (ImmutableSet<E>) super.get(key);
    }
    
    public final Iterable<E> getFirstElems() {
        return Iterables.transform(asMap().values(),
                new Function<Collection<E>, E>() {
            @Override
            public E apply(Collection<E> input) {
                return input.iterator().next();
            }
        }
    );
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static final ResolvedEquivalents<?> EMPTY_INSTANCE 
            = new ResolvedEquivalents(ImmutableSetMultimap.<Id,Object>of());
    
    @SuppressWarnings("unchecked")
    public static <E extends Equivalent<E>> ResolvedEquivalents<E> empty() {
        return (ResolvedEquivalents<E>) EMPTY_INSTANCE;
    }
}
