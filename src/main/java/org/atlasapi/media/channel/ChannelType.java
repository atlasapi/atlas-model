package org.atlasapi.media.channel;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public enum ChannelType {

    CHANNEL,
    MASTERBRAND;

    public String toKey() {
        return this.name().toLowerCase();
    }

    private static final ImmutableSet<ChannelType> ALL = ImmutableSet.copyOf(values());

    public static final ImmutableSet<ChannelType> all() {
        return ALL;
    }

    private static final ImmutableMap<String, Optional<ChannelType>> KEY_MAP =
            ImmutableMap.copyOf(Maps.transformValues(Maps.uniqueIndex(all(), new Function<ChannelType, String>() {
                @Override
                @Nullable
                public String apply(@Nullable ChannelType input) {
                    return input.toKey();
                }
            }), new Function<ChannelType, Optional<ChannelType>>() {

                @Override
                public Optional<ChannelType> apply(ChannelType o) {
                    return Optional.fromNullable(o);
                }
            }));

    public static Optional<ChannelType> fromKey(String key) {
        Optional<ChannelType> possibleMediaType = KEY_MAP.get(key);
        return possibleMediaType != null ? possibleMediaType
                                         : Optional.<ChannelType>absent();
    }
}
