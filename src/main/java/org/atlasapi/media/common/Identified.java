package org.atlasapi.media.common;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Longs;

/**
 * Base type for resources. 
 *
 * @author Fred van den Driessche (fred@metabroadcast.com)
 */
public abstract class Identified implements Sourced {

    public abstract static class Builder<T extends Identified, B extends Builder<T, B>> {

        protected Long id;
        protected ImmutableSet<Identifier> equivalents = ImmutableSet.of();
        protected Publisher source;
        protected String sourceUri;
        protected String sourceCurie;
        protected ImmutableSet<String> aliases = ImmutableSet.of();
        protected DateTime firstSeen;
        protected DateTime lastUpdated;

        public Builder() {}

        protected abstract B builder();
        public abstract T build();
        
        public B copy(T t) {
            return builder().withId(t.id)
            .withEquivalents(t.equivalents)
            .withSource(t.source)
            .withSourceUri(t.sourceUri)
            .withSourceCurie(t.sourceCurie)
            .withAliases(t.aliases)
            .withLastUpdateAt(t.lastUpdated);
        }

        public B withId(Long id) {
            this.id = id;
            return builder();
        }
        
        public B withEquivalents(Iterable<Identifier> equivalents) {
            this.equivalents = ImmutableSet.copyOf(equivalents);
            return builder();
        }
        
        public B withSource(Publisher publisher) {
            this.source = publisher;
            return builder();
        }

        public B withSourceUri(String sourceUri) {
            this.sourceUri = sourceUri;
            return builder();
        }

        public B withSourceCurie(String sourceCurie) {
            this.sourceCurie = sourceCurie;
            return builder();
        }
        
        public B withAliases(Iterable<String> aliases) {
            this.aliases = ImmutableSet.copyOf(aliases);
            return builder();
        }
        
        public B addAlias(String alias) {
            this.aliases = ImmutableSet.<String>builder().add(alias).addAll(aliases).build();
            return builder();
        }
        
        public B withAlias(String alias) {
            this.aliases.add(alias);
            return builder();
        }
        
        public B withLastUpdateAt(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return builder();
        }
        
        public B withFirstSeen(DateTime firstSeen) {
            this.firstSeen = firstSeen;
            return builder();
        }
    }
    
    protected final Long id;
    protected final ImmutableSet<Identifier> equivalents;

    protected final Publisher source;
    protected final String sourceUri;
    protected final String sourceCurie;
    protected final ImmutableSet<String> aliases;

    protected final DateTime lastUpdated;
    protected final DateTime firstSeen;

    public Identified(Builder<? extends Identified, ? extends Builder<? extends Identified, ?>> b) {
        this.id = b.id;
        this.equivalents = b.equivalents;
        this.source = b.source;
        this.sourceUri = b.sourceUri;
        this.sourceCurie = b.sourceCurie;
        this.aliases = b.aliases;
        this.lastUpdated = b.lastUpdated;
        this.firstSeen = b.firstSeen;
    }

    public abstract Builder<? extends Identified, ? extends Builder<? extends Identified, ?>> copy();

    /**
     * Identifier for resource. Unique within the resource type.
     * 
     * @return numeric identifier for the resource.
     */
    public Long id() {
        return id;
    }

    /**
     * @return a set of references to resources that are equivalent to this
     *         resource, in some sense.
     */
    public ImmutableSet<Identifier> equivalents() {
        return equivalents;
    }

    /**
     * External-system resource identifier. 
     * 
     * @return the source-specific identifier for the entity.
     */
    //TODO: does this have to be a URI?
    public String sourceUri() {
        return sourceUri;
    }
    
    /**
     * Compact external-system resource identifier
     * 
     * @return the source-specifice compact identifier for the entity.
     */
    public String sourceCurie() {
        return sourceCurie;
    }

    @Override
    public Publisher source() {
        return source;
    }

    /**
     * @return a set of alternative source URIs for this entity.
     */
    public ImmutableSet<String> aliases() {
        return aliases;
    }

    /**
     * @return the last time this entity was changed.
     */
    public DateTime lastUpdated() {
        return lastUpdated;
    }

    /**
     * @return first time this entity was fetched.
     */
    public DateTime firstSeen() {
        return firstSeen;
    }
    
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if(id != null && that instanceof Identified) {
            return id.equals(((Identified)that).id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : Longs.hashCode(id);
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", getClass().getSimpleName(), id == null ? hashCode() : id);
    }
    
    public abstract Identifier toIdentifier();
}
