package org.atlasapi.media.channel;

import java.util.Map;

import org.atlasapi.media.common.OptionalMap;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;


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
    
    public static final ImmutableSet<ChannelGroupType> CHANNEL_GROUP_TYPES = ImmutableSet.copyOf(values());

    public static final Function<ChannelGroupType, String> TO_KEY = new Function<ChannelGroupType, String>() {
        @Override
        public String apply(ChannelGroupType from) {
            return from.key();
        }
    };

    private static final Map<String, Optional<ChannelGroupType>> KEY_MAP = OptionalMap.fromMap(Maps.uniqueIndex(CHANNEL_GROUP_TYPES, TO_KEY));

    public static final Optional<ChannelGroupType> fromKey(String key) {
        return KEY_MAP.get(key);
    }

    public static final Function<String, Optional<ChannelGroupType>> FROM_KEY = new Function<String, Optional<ChannelGroupType>>() {
        @Override
        public Optional<ChannelGroupType> apply(String key) {
            return fromKey(key);
        }
    };
}
