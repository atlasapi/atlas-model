package org.atlasapi.media.entity;

import com.google.common.base.Objects;
import com.metabroadcast.common.model.SelfModelling;
import com.metabroadcast.common.model.SimpleModel;

public class Channel extends Identified implements SelfModelling {
    // Change this and you have to rebuild the whole schedule index (if you still want to be able to do range queries
    public static final int MAX_KEY_LENGTH = 31;

    private Publisher publisher;
    private String title;
	private MediaType mediaType;
	private String key;

    public Channel() {
    	
    }
    
    public Channel(Publisher publisher, String title, String key, MediaType mediaType, String uri) {
    	super(uri);
    	this.publisher = publisher;
        this.title = title;
        this.key = key;
        this.mediaType = mediaType;
    }

    public String uri() {
        return getCanonicalUri();
    }

    public String title() {
        return title;
    }

    public MediaType mediaType() {
    	return mediaType;
    }
    
    public Publisher publisher() {
    	return publisher;
    }
    
    @Deprecated
    public String key() {
    	return key;
    }

    @Override
    public SimpleModel toSimpleModel() {
        SimpleModel model = new SimpleModel();
        model.put("name", title);
        model.put("uri", getCanonicalUri());
        model.put("id", String.valueOf(getId()));
        model.put("key", key());

        return model;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Channel) {
            Channel target = (Channel) obj;
            return getId() != null ? Objects.equal(getId(), target.getId()) : Objects.equal(uri(), target.uri());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getId() != null ? Objects.hashCode(getId()) : Objects.hashCode(uri());
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(getId()).addValue(getCanonicalUri()).toString();
    }

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
