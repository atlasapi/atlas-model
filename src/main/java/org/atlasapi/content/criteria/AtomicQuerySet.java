package org.atlasapi.content.criteria;

import java.util.List;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class AtomicQuerySet extends ForwardingSet<AtomicQuery> {

    private ImmutableSet<AtomicQuery> operands;

    public AtomicQuerySet(Iterable<? extends AtomicQuery> operands) {
        this.operands = ImmutableSet.copyOf(operands);
    }

    @Override
    protected ImmutableSet<AtomicQuery> delegate() {
        return operands;
    }
    
    public <V> List<V> accept(QueryVisitor<V> v) {
        ImmutableList.Builder<V> result = ImmutableList.builder();
        for (AtomicQuery operand : operands) {
            result.add(operand.accept(v));
        }
        return result.build();
    }
    
}
