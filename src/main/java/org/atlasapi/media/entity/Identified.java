package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.equiv.EquivalenceRef;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.util.Identifiables;
import org.atlasapi.media.vocabulary.OWL;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * Base type for descriptions of resources.
 *
 * @author Robert Chatley
 * @author Lee Denison
 */
public class Identified implements Identifiable {

	private Id id;
	
	private String canonicalUri;

	private String curie;

	@Deprecated
	private Set<String> aliasUrls = Sets.newHashSet();
	private Set<Alias> aliases = Sets.newHashSet();
	
	private Set<EquivalenceRef> equivalentTo = Sets.newHashSet();
	
	/**
	 * Records the time that the 3rd party reported that the
	 * {@link Identified} was last updated
	 */
	private DateTime lastUpdated;
	
	private DateTime equivalenceUpdate;
	
	public Identified(String uri, String curie) {
		this.canonicalUri = uri;
		this.curie = curie;
	}
	
	public Identified() { 
		/* allow anonymous entities */ 
		this.canonicalUri = null;
		this.curie = null;
	}
	
	public Identified(String uri) { 
		this(uri, null);
	}
	
	@Deprecated
	@RdfProperty(relation = true, namespace=OWL.NS, uri="sameAs")
	public Set<String> getAliasUrls() {
		return aliasUrls;
	}
	
	@RdfProperty(relation = true, namespace=OWL.NS, uri="sameAs")
    public Set<Alias> getAliases() {
        return aliases;
    }
	
	public void setCanonicalUri(String canonicalUri) {
		this.canonicalUri = canonicalUri;
	}
	
	public void setCurie(String curie) {
		this.curie = curie;
	}
	
	@Deprecated
	public void setAliasUrls(Iterable<String> urls) {
		this.aliasUrls = ImmutableSortedSet.copyOf(urls);
	}
	
    public void setAliases(Iterable<Alias> aliases) {
        this.aliases = ImmutableSet.copyOf(aliases);
    }
    
    @Deprecated
    public void addAliasUrl(String alias) {
        addAliasUrls(ImmutableList.of(alias));
    }
    
    public void addAlias(Alias alias) {
        addAliases(ImmutableList.of(alias));
    }
    
    @Deprecated
    public void addAliasUrls(Iterable<String> urls) {
        setAliasUrls(Iterables.concat(this.aliasUrls, ImmutableList.copyOf(urls)));
    }
	
	public void addAliases(Iterable<Alias> aliases) {
	    setAliases(Iterables.concat(this.aliases, ImmutableList.copyOf(aliases)));
	}
	
	public String getCanonicalUri() {
		return canonicalUri;
	}
	
	@RdfProperty(relation = false, namespace=PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri="curie")
	public String getCurie() {
		return curie;
	}

	@Deprecated
	public Set<String> getAllUris() {
		Set<String> allUris = Sets.newHashSet(getAliasUrls());
		allUris.add(getCanonicalUri());
		return Collections.unmodifiableSet(allUris);
	}
	
	@Override
	public String toString() {
	    return Objects.toStringHelper(getClass().getSimpleName().toLowerCase())
	            .addValue(id != null ? id : "no-id")
	            .toString();
	}
	
	@Override
	public int hashCode() {
		return id != null ? id.hashCode(): super.hashCode();
	}
	
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that instanceof Identified && id != null) {
            Identified other = (Identified) that;
            return id.equals(other.id);
        }
		return false;
	}
	
	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public DateTime getLastUpdated() {
		return lastUpdated;
	}
	
	//wut?
	public void addEquivalentTo(Content content) {
		checkNotNull(content.getCanonicalUri());
		this.equivalentTo.add(EquivalenceRef.valueOf(content));
	}
	
	public Set<EquivalenceRef> getEquivalentTo() {
		return equivalentTo;
	}
	
	@Override
    public Id getId() {
		return id;
	}
	
	public void setId(long id) {
	    this.id = Id.valueOf(id);
	}
	
	public void setId(Id id) {
		this.id = id;
	}
	
	public DateTime getEquivalenceUpdate() {
	    return equivalenceUpdate;
	}
	
	public void setEquivalenceUpdate(DateTime equivalenceUpdate) {
	    this.equivalenceUpdate = equivalenceUpdate;
	}
	
	public static final Function<Identified, String> TO_URI = new Function<Identified, String>() {

		@Override
		public String apply(Identified description) {
			return description.getCanonicalUri();
		}
	};
	
	public static final Function<Identified, Id> TO_ID = new Function<Identified, Id>() {
        @Override
        public Id apply(Identified input) {
            return input.getId();
        }
    };

	public void setEquivalentTo(Set<EquivalenceRef> uris) {
		this.equivalentTo = uris;
	}
	
	public Identified copyWithEquivalentTo(Iterable<EquivalenceRef> refs) {
	    this.equivalentTo = ImmutableSet.copyOf(refs);
	    return this;
	}
	
	public static final Comparator<Identified> DESCENDING_LAST_UPDATED = new Comparator<Identified>() {
        @Override
        public int compare(final Identified s1, final Identified s2) {
            if (s1.getLastUpdated() == null && s2.getLastUpdated() == null) {
                return 0;
            }
            if (s2.getLastUpdated() == null) {
                return -1;
            }
            if (s1.getLastUpdated() == null) {
                return 1;
            }
            
            return s2.getLastUpdated().compareTo(s1.getLastUpdated());
        }
    };
	
	 /**
     * This method attempts to preserve symmetry of
     * equivalence (since content is persisted independently
     * there is often a window of inconsistency)
     */
	public boolean isEquivalentTo(Content content) {
		return equivalentTo.contains(EquivalenceRef.valueOf(content))
	        || Iterables.contains(Iterables.transform(content.getEquivalentTo(), Identifiables.toId()), id);
	}
	
	public static void copyTo(Identified from, Identified to) {
	    to.id = from.id;
	    to.aliases = Sets.newHashSet(from.aliases);
	    to.aliasUrls = Sets.newHashSet(from.aliasUrls);
	    to.canonicalUri = from.canonicalUri;
	    to.curie = from.curie;
	    to.equivalentTo = Sets.newHashSet(from.equivalentTo);
	    to.lastUpdated = from.lastUpdated;
	}
	
	public static <T extends Identified> List<T> sort(List<T> content, final Iterable<String> orderIterable) {
        
        final ImmutableList<String> order = ImmutableList.copyOf(orderIterable);
        
        Comparator<Identified> byPositionInList = new Comparator<Identified>() {

            @Override
            public int compare(Identified c1, Identified c2) {
                return Ints.compare(indexOf(c1), indexOf(c2));
            }

            private int indexOf(Identified content) {
                for (String uri : content.getAllUris()) {
                    int idx = order.indexOf(uri);
                    if (idx != -1) {
                        return idx;
                    }
                }
                if (content.getCurie() != null) {
                    return order.indexOf(content.getCurie());
                }
                return -1;
            }
        };
        
        List<T> toSort = Lists.newArrayList(content);
        Collections.sort(toSort, byPositionInList);
        return toSort;
    }
}