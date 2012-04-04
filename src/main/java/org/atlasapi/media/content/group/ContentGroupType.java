package org.atlasapi.media.content.group;

import java.util.Map;

import org.atlasapi.media.common.OptionalMap;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public enum ContentGroupType {
    
    FRANCHISE("franchise"),
    SEASON("season"),
    PLAYLIST("playlist"),
    PERSON("person");
    
    private String key;

    private ContentGroupType(String key) {
        this.key = key;
    }
    
    public String key() {
        return key;
    }
    
    public static final ImmutableSet<ContentGroupType> CONTENT_GROUP_TYPES
        = ImmutableSet.copyOf(values());

    public static final Function<ContentGroupType, String> TO_KEY
        = new Function<ContentGroupType, String>() {
            @Override
            public String apply(ContentGroupType from) {
                return from.key();
            }
        };

    private static final Map<String, Optional<ContentGroupType>> KEY_MAP
        = OptionalMap.fromMap(Maps.uniqueIndex(CONTENT_GROUP_TYPES, TO_KEY));

    public static final Optional<ContentGroupType> fromKey(String key) {
        return KEY_MAP.get(key);
    }

    public static final Function<String, Optional<ContentGroupType>> FROM_KEY
        = new Function<String, Optional<ContentGroupType>>() {
            @Override
            public Optional<ContentGroupType> apply(String key) {
                return fromKey(key);
            }
        };
    
}
