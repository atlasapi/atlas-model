package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.OWL;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * Base type for descriptions of resources.
 *
 * @author Robert Chatley
 * @author Lee Denison
 */
public class Description {

	private String canonicalUri;

	private String curie;

	private Set<String> aliases = Sets.newHashSet();
	
	private Set<String> equivalentTo = Sets.newHashSet();
	
	/**
	 * Records the time that the 3rd party reported that the
	 * {@link Description} was last updated
	 */
	private DateTime lastUpdated;
	
	public Description(String uri, String curie) {
		if (uri == null) {
			throw new IllegalArgumentException("Null uri specified");
		}
		this.canonicalUri = uri;
		this.curie = curie;
	}
	
	public Description() { 
		/* allow anonymous entities */ 
		this.canonicalUri = null;
		this.curie = null;
	}
	
	public Description(String uri) { 
		this(uri, null);
	}
	
	
	@RdfProperty(relation = true, namespace=OWL.NS, uri="sameAs")
	public Set<String> getAliases() {
		return aliases;
	}
	
	public void setCanonicalUri(String canonicalUri) {
		this.canonicalUri = canonicalUri;
	}
	
	public void setCurie(String curie) {
		this.curie = curie;
	}
	
	public void setAliases(Set<String> uris) {
		this.aliases = uris;
	}
	
	public void addAlias(String uri) {
		if (!aliases.contains(uri)) {
			this.aliases.add(uri);
		}
	}
	
	public String getCanonicalUri() {
		return canonicalUri;
	}
	
	@RdfProperty(relation = false, namespace=PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri="curie")
	public String getCurie() {
		return curie;
	}

	public Set<String> getAllUris() {
		Set<String> allUris = Sets.newHashSet(getAliases());
		allUris.add(getCanonicalUri());
		return Collections.unmodifiableSet(allUris);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(uri:"  + canonicalUri + ")";
	}
	
	@Override
	public int hashCode() {
		if (canonicalUri == null) {
			return super.hashCode();
		}
		return canonicalUri.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (canonicalUri != null && obj instanceof Description) {
			return canonicalUri.equals(((Description) obj).canonicalUri);
		}
		return false;
	}
	
	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public DateTime getLastUpdated() {
		return lastUpdated;
	}
	
	public void addEquivalentTo(Content content) {
		checkNotNull(content.getCanonicalUri());
		this.equivalentTo.add(content.getCanonicalUri());
		this.aliases.add(content.getCanonicalUri());
	}
	
	public Set<String> getEquivalentTo() {
		return equivalentTo;
	}
	
	public static final Function<Description, String> TO_URI = new Function<Description, String>() {

		@Override
		public String apply(Description description) {
			return description.getCanonicalUri();
		}
	};

	public void setEquivalentTo(Set<String> uris) {
		this.equivalentTo = uris;
	}
	
	 /**
     * This method attempts to preserve symmetry of
     * equivalence (since content is persisted independently
     * there is often a window of inconsistency)
     */
	public boolean isEquivalentTo(Description content) {
		return equivalentTo.contains(content.getCanonicalUri()) || content.equivalentTo.contains(canonicalUri);
	}
}