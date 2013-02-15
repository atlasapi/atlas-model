package org.atlasapi.content.criteria;

import org.atlasapi.content.criteria.QueryNode.IntermediateNode;

import com.google.common.collect.ImmutableList;

public final class NodeSet {
    
    private final IntermediateNode root = new IntermediateNode(ImmutableList.<String>of());
    
    public NodeSet(Iterable<? extends AttributeQuery<?>> queries) {
        for (AttributeQuery<?> attributeQuery : queries) {
            ImmutableList<String> path = attributeQuery.getAttribute().getPath();
            root.add(attributeQuery, path, 0);
        }
    }

    public NodeSet() {}

    public NodeSet addQuery(AttributeQuery<?> query) {
        root.add(query, query.getAttribute().getPath(), 0);
        return this;
    }

    @Override
    public String toString() {
        return root.toString();
    }
    
    public <V> V accept(QueryNodeVisitor<V> visitor) {
        return root.accept(visitor);
    }
}