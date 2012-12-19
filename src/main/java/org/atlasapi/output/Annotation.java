package org.atlasapi.output;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.metabroadcast.common.collect.ImmutableOptionalMap;

public enum Annotation {

    LICENSE("content.license"),
    ID_SUMMARY("*.id_summary"),
    ID("*.id"),
    EXTENDED_ID("*.extended_id"), 
    DESCRIPTION("content.description"),
    EXTENDED_DESCRIPTION("content.extended_description"),
    BRAND_REFERENCE("content.container"),
    BRAND_SUMMARY("content.container_summary"),
    SERIES_REFERENCE("content.series"),
    SERIES_SUMMARY("content.series_summary"),
    SUB_ITEMS("content.sub_items"),
    CLIPS("content.clips"),
    PEOPLE("content.people"),
    TOPICS("content.topics"),
    CONTENT_GROUPS("content.content_groups"),
    SEGMENT_EVENTS("content.segment_events"),
    RELATED_LINKS("content.related_links"),
    KEY_PHRASES("content.key_phrases"),
    BROADCASTS("content.broadcasts"),   
    LOCATIONS("content.locations"),
    FIRST_BROADCASTS("content.first_broadcasts"),
    NEXT_BROADCASTS("content.next_broadcasts"),
    AVAILABLE_LOCATIONS("content.available_locations"),
    UPCOMING("content.upcoming"), 
    FILTERING_RESOURCE("content.filtering_resource"),
    CHANNEL("channel.detail"),
    CHANNEL_GROUPS("channel.groups"),
    HISTORY("channel.history"),
    PARENT("channel.parent"),
    PRODUCTS("content.products"),
    RECENTLY_BROADCAST("content.recently_broadcast"),
    CHANNELS("content.channels"),

    CONTENT_CHANNEL_SUMMARY("content.channel_summary"),
    PUBLISHER("content.publisher"),
    CONTENT_SUMMARY("content.summary"),
    CONTENT_DETAIL("content.detail"),
    CHANNEL_SUMMARY("channel.summary"),
    AUDIT("content.audit"),
    IMAGES("content.images");
    
    private final String requestName;

    private Annotation(String requestName) {
        this.requestName = requestName;
    }
    
    public String getRequestName() {
        return requestName;
    }
    
    public String toKey() {
        return name().toLowerCase();
    }
    
    private static final Function<Annotation, String> TO_KEY = new Function<Annotation, String>() {
        @Override
        public String apply(Annotation input) {
            return input.toKey();
        }
    };
    
    private static final Function<Annotation, String> TO_REQUEST_NAME = new Function<Annotation, String>() {
        @Override
        public String apply(@Nullable Annotation input) {
            return input.getRequestName();
        }
    };
    
    public static final Function<Annotation, String> toKeyFunction() {
        return TO_KEY;
    }

    public static final Function<Annotation, String> toRequestName() {
        return TO_REQUEST_NAME;
    }
    
    private static final ImmutableSet<Annotation> ALL = ImmutableSet.copyOf(values());
    
    public static final ImmutableSet<Annotation> all() {
        return ALL;
    }

    private static final Map<String,Optional<Annotation>> nameLookup = ImmutableOptionalMap.fromMap(Maps.uniqueIndex(all(), TO_REQUEST_NAME));
    
    public static final Map<String, Optional<Annotation>> nameLookup() {
        return nameLookup;
    }
    
    public static final Optional<Annotation> fromRequestName(String name, Optional<String> context) {
        return nameLookup.get(requestNameForContext(name, context));
    }

    public static String requestNameForContext(String name, Optional<String> context) {
        return context.isPresent() ? context.get() + "." + name : name;
    }

    private static final Map<String,Optional<Annotation>> lookup = ImmutableOptionalMap.fromMap(Maps.uniqueIndex(all(), TO_KEY));
    
    public static final Map<String, Optional<Annotation>> lookup() {
        return lookup;
    }
    
    public static final Optional<Annotation> fromKey(String key) {
        return lookup.get(key);
    }
    
    public static final Set<Annotation> defaultAnnotations() {
        return defaultAnnotations;
    }

    private static final ImmutableSet<Annotation> defaultAnnotations = ImmutableSet.of(
        ID_SUMMARY, LICENSE
    );
    
}
