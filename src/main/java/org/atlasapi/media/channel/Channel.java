package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.atlasapi.media.common.Publisher;
import org.atlasapi.media.content.MediaType;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

public final class Channel extends Identified implements Described {

    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends Identified.Builder<Channel, Builder> {

        private Description description;
        private MediaType mediaType;
        private Publisher broadcaster;
        private Set<Publisher> availableFrom = ImmutableSet.of();

        @Override
        public Builder copy(Channel channel) {
            super.copy(channel);
            this.description = channel.description;
            this.mediaType = channel.mediaType;
            this.broadcaster = channel.broadcaster;
            return builder();
        }
        
        public Builder withDescription(Description description) {
            this.description = description;
            return builder();
        }

        public Builder withDescription(Description.Builder descriptionBuilder) {
            return withDescription(descriptionBuilder.build());
        }

        public Builder withMediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return builder();
        }

        public Builder withBroadcaster(Publisher broadcaster) {
            this.broadcaster = broadcaster;
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Channel build() {
            return new Channel(this);
        }
        
    }
    
    private final Description description;
	private final MediaType mediaType;
    private final Publisher broadcaster;
    private final Set<Publisher> availableFrom;

	public Channel(Builder b) {
    	super(b);
    	this.description = b.description;
    	this.mediaType = b.mediaType;
    	this.broadcaster = b.broadcaster;
    	this.availableFrom = b.availableFrom;
    }
	
	@Override
	public Builder copy() {
	    return new Builder().copy(this);
	}

    public String uri() {
        return sourceUri();
    }

    public Description description() {
        return description;
    }

    public MediaType mediaType() {
    	return mediaType;
    }

    public Publisher broadcaster() {
        return broadcaster;
    }
    
    public Set<Publisher> availableFrom() {
        return availableFrom;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Channel) {
            //TODO: not sure about this, can't we just rely on id from now on?
            Channel target = (Channel) obj;
            return id != null ? id.equals(target.id) : sourceUri.equals(target.sourceUri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id() != null ? Objects.hashCode(id) : Objects.hashCode(sourceUri);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(id).addValue(sourceUri).toString();
    }

    @Override
    public ChannelIdentifier toIdentifier() {
        return new ChannelIdentifier(this);
    }
    
    public static final class ChannelIdentifier extends Identifier {

        protected ChannelIdentifier(Identified identified) {
            super(identified);
        }
        
        public ChannelIdentifier(Long id, Publisher source) {
            super(id, source);
        }
        
    }
}

