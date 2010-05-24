package org.uriplay.media.entity;

import java.util.Collections;
import java.util.Set;

import org.uriplay.content.rdf.annotations.RdfProperty;
import org.uriplay.media.vocabulary.OWL;
import org.uriplay.media.vocabulary.PLAY;

import com.google.common.collect.Sets;

/**
 * Base type for descriptions of resources.
 *
 * @author Robert Chatley
 * @author Lee Denison
 */
public class Description {

	private String canonicalUri;

	private Set<String> aliases = Sets.newHashSet();
	
	private String curie;
	
	@RdfProperty(relation = true, namespace=OWL.NS, uri="sameAs")
	public Set<String> getAliases() {
		return aliases;
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
	
	public void setCanonicalUri(String uri) {
		this.canonicalUri = uri;
	}
	
	@RdfProperty(relation = false, namespace=PLAY.NS, uri="curie")
	public String getCurie() {
		return curie;
	}
	
	public void setCurie(String curie) {
		this.curie = curie;
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
}