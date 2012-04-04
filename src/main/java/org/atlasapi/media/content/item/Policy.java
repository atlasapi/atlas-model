package org.atlasapi.media.content.item;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.currency.Price;
import com.metabroadcast.common.intl.Country;

public final class Policy {
    
    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        
        private DateTime availabilityStart;
        private DateTime availabilityEnd;
        private DateTime drmPlayableFrom;
        private ImmutableSet<Country> availableCountries;
        private Integer availabilityLength;
        private RevenueContract revenueContract;
        private Price price;
        private Platform platform;
        
        public Builder copy(Policy policy) {
            this.availabilityStart = policy.availabilityStart;
            this.availabilityEnd = policy.availabilityEnd;
            this.availabilityLength = policy.availabilityLength;
            this.availableCountries = policy.availableCountries;
            this.drmPlayableFrom = policy.drmPlayableFrom;
            this.revenueContract = policy.revenueContract;
            this.platform = policy.platform;
            this.price = policy.price;
            return builder();
        }
        
        public Builder withAvailabilityStart(DateTime availabilityStart) {
            this.availabilityStart = availabilityStart;
            return builder();
        }

        public Builder withAvailabilityEnd(DateTime availabilityEnd) {
            this.availabilityEnd = availabilityEnd;
            return builder();
        }

        public Builder withDrmPlayableFrom(DateTime drmPlayableFrom) {
            this.drmPlayableFrom = drmPlayableFrom;
            return builder();
        }

        public Builder withAvailableCountries(Iterable<Country> availableCountries) {
            this.availableCountries = ImmutableSet.copyOf(availableCountries);
            return builder();
        }

        public Builder withAvailabilityLength(Integer availabilityLength) {
            this.availabilityLength = availabilityLength;
            return builder();
        }

        public Builder withRevenueContract(RevenueContract revenueContract) {
            this.revenueContract = revenueContract;
            return builder();
        }

        public Builder withPrice(Price price) {
            this.price = price;
            return builder();
        }

        public Builder withPlatform(Platform platform) {
            this.platform = platform;
            return builder();
        }
        
        protected Builder builder() {
            return this;
        }

        public Policy build() {
            return new Policy(this);
        }
        
    }

    private final DateTime availabilityStart;
	private final DateTime availabilityEnd;
	private final Integer availabilityLength;
    private final DateTime drmPlayableFrom;
    
    private final ImmutableSet<Country> availableCountries;

	private final RevenueContract revenueContract;
	private final Price price;
	private final Platform platform;
    
	private Policy(Builder builder) {
	    this.availabilityStart = builder.availabilityStart;
	    this.availabilityEnd = builder.availabilityEnd;
	    this.availabilityLength = builder.availabilityLength;
	    this.availableCountries = builder.availableCountries;
	    this.drmPlayableFrom = builder.drmPlayableFrom;
	    this.revenueContract = builder.revenueContract;
	    this.price = builder.price;
	    this.platform = builder.platform;
	}

    public Builder copy() {
        return new Builder().copy(this);
    }
	
    public ImmutableSet<Country> availableCountries() {
		return availableCountries;
	}
    
    public DateTime availabilityStart() { 
        return availabilityStart;
    }

    public DateTime drmPlayableFrom() { 
        return drmPlayableFrom;
    }
    
    public DateTime availabilityEnd() {
		return availabilityEnd;
	}
    
    public Platform platform() {
    	return platform;
    }
	
	public RevenueContract revenueContract() {
        return revenueContract;
    }
	
	public Price price() {
        return price;
    }

	public Integer availabilityLength() {
		return availabilityLength;
	}

}
