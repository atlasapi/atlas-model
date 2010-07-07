package org.atlasapi.content.criteria;

public abstract class AtomicQuery {
	
	public abstract <V> V accept(QueryVisitor<V> v);

}
