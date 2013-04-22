package org.atlasapi.equiv;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.media.common.Sourced;
import org.atlasapi.media.util.Sourceds;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.metabroadcast.common.base.MoreOrderings;

public class StrategyBackedEquivalentsMerger<E extends Equivalent<E>>
        implements ApplicationEquivalentsMerger<E> {

    private final EquivalentsMergeStrategy<E> strategy;

    public StrategyBackedEquivalentsMerger(EquivalentsMergeStrategy<E> strategy) {
        this.strategy = checkNotNull(strategy);
    }

    @Override
    public <T extends E> List<T> merge(Iterable<T> equivalents, ApplicationConfiguration config) {
        if (!config.precedenceEnabled()) {
            return ImmutableList.copyOf(equivalents);
        }
        Ordering<Sourced> equivsOrdering = applicationEquivalentsOrdering(config);
        ImmutableList<T> sortedEquivalents = equivsOrdering.immutableSortedCopy(equivalents);
        if (trivialMerge(sortedEquivalents)) {
            return sortedEquivalents;
        }
        T chosen = sortedEquivalents.get(0);
        ImmutableList<T> notChosen = sortedEquivalents.subList(1, sortedEquivalents.size());
        return ImmutableList.of(strategy.merge(chosen, notChosen, config));
    }

    private boolean trivialMerge(ImmutableList<?> sortedEquivalents) {
        return sortedEquivalents.isEmpty() || sortedEquivalents.size() == 1;
    }

    private Ordering<Sourced> applicationEquivalentsOrdering(ApplicationConfiguration config) {
        return MoreOrderings.transformingOrdering(
                Sourceds.toPublisher(), config.publisherPrecedenceOrdering());
    }

}
