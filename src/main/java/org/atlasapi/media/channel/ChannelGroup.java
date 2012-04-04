package org.atlasapi.media.channel;

import org.atlasapi.media.channel.Channel.ChannelIdentifier;
import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.atlasapi.media.common.Publisher;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.intl.Country;

public final class ChannelGroup extends Identified implements Described {
    
    public static final Builder builder(ChannelGroupType channelGroupType) {
        return new Builder(channelGroupType);
    }
    
    public static final class Builder extends Identified.Builder<ChannelGroup, Builder> {

        private ChannelGroupType type;
        private Description description;
        private ImmutableSet<Country> countries;
        private ImmutableSet<ChannelIdentifier> channels;

        public Builder(ChannelGroupType channelGroupType) {
            this.type = channelGroupType;
        }

        @Override
        public Builder copy(ChannelGroup t) {
            super.copy(t);
            this.description = t.description;
            this.countries = t.countries;
            this.type = t.type;
            this.channels = t.channels;
            return builder();
        }
        
        public Builder withType(ChannelGroupType type) {
            this.type = type;
            return builder();
        }
        
        public Builder withDescription(Description description) {
            this.description = description;
            return builder();
        }
        
        public Builder withDescription(Description.Builder description) {
            return withDescription(description.build());
        }
        
        public Builder withAvailableCountries(Iterable<Country> countries) {
            this.countries = ImmutableSet.copyOf(countries);
            return builder();
        }
        
        public Builder withChannel(Iterable<ChannelIdentifier> channels) {
            this.channels = ImmutableSet.copyOf(channels);
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public ChannelGroup build() {
            return new ChannelGroup(this);
        }
        
    }
    
    private final ChannelGroupType type;
    private final Description description;
    private final ImmutableSet<Country> countries;
    private final ImmutableSet<ChannelIdentifier> channels;

    private ChannelGroup(Builder builder) {
        super(builder);
        this.description = builder.description;
        this.type = builder.type;
        this.countries = builder.countries;
        this.channels = builder.channels;
        
    }

    @Override
    public Builder copy() {
        return new Builder(type).copy(this);
    }
    
    public ChannelGroupType type() {
        return type;
    }
    
    public Description description() {
        return description();
    }
    
    public ImmutableSet<Country> availableCountries() {
        return countries;
    }

    public ImmutableSet<ChannelIdentifier> channels() {
        return channels;
    }

    @Override
    public Identifier toIdentifier() {
        return new ChannelGroupIdentifier(this);
    }
    
    public static final class ChannelGroupIdentifier extends Identifier {

        protected ChannelGroupIdentifier(Identified identified) {
            super(identified);
        }
        
        public ChannelGroupIdentifier(Long id, Publisher source) {
            super(id, source);
        }
        
    }
}
