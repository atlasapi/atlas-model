package org.atlasapi.media.content.item;

import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public final class Location extends Identified {
    
    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends Identified.Builder<Location, Builder> {

        private Boolean transportIsLive;
        private TransportSubType transportSubType;
        private TransportType transportType;
        private String uri;
        private String embedCode;
        private String embedId;
        private Policy policy;
        
        @Override
        public Builder copy(Location t) {
            super.copy(t);
            this.transportIsLive = t.transportIsLive;
            this.transportSubType = t.transportSubType;
            this.transportType = t.transportType;
            this.uri = t.uri;
            this.embedCode = t.embedCode;
            this.embedId = t.embedId;
            this.policy = t.policy;
            return builder();
        }

        public Builder withTransportIsLive(Boolean transportIsLive) {
            this.transportIsLive = transportIsLive;
            return builder();
        }

        public Builder withTransportSubType(TransportSubType transportSubType) {
            this.transportSubType = transportSubType;
            return builder();
        }

        public Builder withTransportType(TransportType transportType) {
            this.transportType = transportType;
            return builder();
        }

        public Builder withUri(String uri) {
            this.uri = uri;
            return builder();
        }

        public Builder withEmbedCode(String embedCode) {
            this.embedCode = embedCode;
            return builder();
        }

        public Builder withEmbedId(String embedId) {
            this.embedId = embedId;
            return builder();
        }

        public Builder withPolicy(Policy policy) {
            this.policy = policy;
            return builder();
        }

        public Builder withPolicy(Policy.Builder policyBuilder) {
            this.policy = policyBuilder.build();
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Location build() {
            return new Location(this);
        }
        
    }
    
    private final Boolean transportIsLive;
    private final TransportSubType transportSubType;
    private final TransportType transportType;
    private final String uri;
    private final String embedCode;
    private final String embedId;
    private final Policy policy;
    
    private Location(Builder builder) {
        super(builder);
        this.transportIsLive = builder.transportIsLive;
        this.transportSubType = builder.transportSubType;
        this.transportType = builder.transportType;
        this.uri = builder.uri;
        this.embedCode = builder.embedCode;
        this.embedId = builder.embedId;
        this.policy = builder.policy;
    }
    
    public Builder copy() {
        return null;
    }
    
    public Policy policy() { 
        return this.policy; 
    }

    public Boolean transportIsLive() {
        return this.transportIsLive;
    }
    
    public TransportSubType transportSubType() { 
        return this.transportSubType; 
    }

    public TransportType transportType() { 
        return this.transportType; 
    }
    
    public boolean available(Iterable<Predicate<Location>> tests) {
    	return Predicates.and(tests).apply(this);
    }

    public String uri() {
		return uri;
	}
    
    public String embedCode() {
		return embedCode;
	}
    
    public String embedId() {
        return embedId;
    }
    
    @Override
    public Identifier toIdentifier() {
        return new LocationIdentifier(this);
    }
    
    public static final class LocationIdentifier extends Identifier {

        public LocationIdentifier(Location location) {
            super(location);
        }
        
    }
}