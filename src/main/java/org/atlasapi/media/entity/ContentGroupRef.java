package org.atlasapi.media.entity;

import com.google.common.base.Objects;

public class ContentGroupRef {

	private Long id;
    private String uri;
	
	public ContentGroupRef(Long id, String uri) {
		this.id = id;
		this.uri = uri;
	}
    
    public ContentGroupRef(ContentGroup contentGroup) {
		this.id = contentGroup.getId();
		this.uri = contentGroup.getCanonicalUri();
	}

    private ContentGroupRef() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

	@Override
	public int hashCode() {
		return Objects.hashCode(id, uri);
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
		    return true;
		}
		if (that instanceof ContentGroupRef) {
		    ContentGroupRef other = (ContentGroupRef) that;
		    return Objects.equal(id, other.id) 
		            && Objects.equal(uri, other.uri);
		}
		return false;
	}
	
	@Override
	public String toString() {
	    return String.format("Ref content group %s", id);
	}
}
