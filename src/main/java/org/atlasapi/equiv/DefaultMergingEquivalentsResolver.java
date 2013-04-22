package org.atlasapi.equiv;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.media.common.Id;
import org.atlasapi.output.Annotation;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class DefaultMergingEquivalentsResolver<E extends Equivalent<E>>
        implements MergingEquivalentsResolver<E> {
    
    private final EquivalentsResolver<E> resolver;
    private final ApplicationEquivalentsMerger<E> merger;

    public DefaultMergingEquivalentsResolver(EquivalentsResolver<E> resolver, ApplicationEquivalentsMerger<E> merger) {
        this.resolver = checkNotNull(resolver);
        this.merger = checkNotNull(merger);
    }
    
    @Override
    public ListenableFuture<ResolvedEquivalents<E>> resolveIds(Iterable<Id> ids,
            ApplicationConfiguration config, Set<Annotation> activeAnnotations) {
        ListenableFuture<ResolvedEquivalents<E>> unmerged
            = resolver.resolveIds(ids, config.getEnabledSources(), activeAnnotations);
        return Futures.transform(unmerged, mergeUsing(config));
    }

    private Function<ResolvedEquivalents<E>, ResolvedEquivalents<E>> mergeUsing(
            final ApplicationConfiguration config) {
        return new Function<ResolvedEquivalents<E>, ResolvedEquivalents<E>>() {
            @Override
            public ResolvedEquivalents<E> apply(ResolvedEquivalents<E> input) {
                ResolvedEquivalents.Builder<E> builder = ResolvedEquivalents.builder();
                for (Map.Entry<Id, Collection<E>> entry : input.asMap().entrySet()) {
                    builder.putEquivalents(entry.getKey(), merge(entry.getValue(), config));
                }
                return builder.build();
            }
        };
    }

    private Iterable<E> merge(Collection<E> equivs, ApplicationConfiguration config) {
        return merger.merge(equivs, config);
    }
}
