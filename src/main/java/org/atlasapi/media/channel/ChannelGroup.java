package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.intl.Country;

public class ChannelGroup extends Identified {

    private Publisher publisher;
    private String title;
    private Set<Country> availableCountries;
    private ChannelGroupType type;
    private Set<Id> channels = ImmutableSet.of();

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Country> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(Set<Country> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public Set<Id> getChannels() {
        return channels;
    }

    public void setChannels(Iterable<Id> channels) {
        this.channels = ImmutableSet.copyOf(channels);
    }
    
    public void addChannel(Channel channel) {
        addChannel(channel.getId());
    }
    
    public void addChannel(Id id) {
        this.channels = ImmutableSet.<Id>builder().addAll(channels).add(id).build();
    }
    
    public ChannelGroupType getType() {
        return type;
    }

    public void setType(ChannelGroupType type) {
        this.type = type;
    }

    public enum ChannelGroupType {
        PLATFORM("platform"),
        REGION("region");
        
        private final String key;

        private ChannelGroupType(String key) {
            this.key = key;
        }
        
        public String key() {
            return key;
        }
        
        public static ChannelGroupType fromKey(String key) {
            for (ChannelGroupType value : ChannelGroupType.values()) {
                if (value.key.equals(key)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Key " + key + " does not map to a ChannelGroupType");
        }
    }
}
