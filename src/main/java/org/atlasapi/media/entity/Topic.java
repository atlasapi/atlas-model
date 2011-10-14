package org.atlasapi.media.entity;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public class Topic extends Described {
    
    private static final String TOPIC_URI_BASE = "http://atlas.metabroadcast.com/topics/";
    
    public static final String topicUriForId(String id) {
        return TOPIC_URI_BASE + id;
    }
    
    private Type type;
    private String namespace;
    private String value;
    private Set<Publisher> publishers = ImmutableSet.of();

    public enum Type {
        SUBJECT("subject", "Subject"),
        PERSON("person", "Person"),
        PLACE("place", "Place"),
        UNKNOWN("unknown", "Unknown");
        
        private final String key;
        private final String display;

        private Type(String key, String display) {
            this.key = key;
            this.display = display;
        }

        public String key() {
            return key;
        }
        
        @Override
        public String toString() {
            return display;
        }
        
        public static Map<String, Type> TYPE_KEY_LOOKUP = Maps.uniqueIndex(ImmutableSet.copyOf(Type.values()), new Function<Type, String>() {
            @Override
            public String apply(Type input) {
                return input.key;
            }
        });
        
        public static Type fromKey(String key) {
            return TYPE_KEY_LOOKUP.get(key);
        }
    }
    
    public Topic(String canonicalUri) {
        super(canonicalUri);
        setMediaType(null);
    }
    
    @Override
    public Topic copy() {
        Topic topic = new Topic(getCanonicalUri());
        topic.type = type;
        topic.namespace = namespace;
        topic.value = value;
        Described.copyTo(this, topic);
        return topic;
    }

    public Type getType() {
        return this.type;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void addPublisher(Publisher publisher) {
        this.publishers = ImmutableSet.<Publisher>builder().addAll(publishers).add(publisher).build();
    }
    
    public void setPublishers(Iterable<Publisher> publishers) {
        this.publishers = ImmutableSet.copyOf(publishers);
    }
    
    public Set<Publisher> getPublishers() {
        return this.publishers;
    }
}
