package org.atlasapi.media.topic;

import java.util.Map;

import org.atlasapi.media.common.Aliased;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Sourced;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public class Topic extends Described implements Sourced, Aliased {
    
    private Type type;
    private String namespace;
    private String value;
    private Publisher publisher;

    public enum Type {
        SUBJECT("subject"),
        PERSON("person"),
        PLACE("place"),
        ARTIST("artist"),
        EVENT("event"),
        PRODUCT("product"),
        WORK("work"),
        UNKNOWN("unknown");
        
        private final String key;

        private Type(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }
        
        @Override
        public String toString() {
            return key;
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
        
        public static Type fromKey(String key, Type deflt) {
            return TYPE_KEY_LOOKUP.containsKey(key) ? TYPE_KEY_LOOKUP.get(key) : deflt;
        }
    }
    
    public Topic() {
        this(null, null, null);
    }
    
    public Topic(long id) {
        this(Id.valueOf(id), null, null);
    }
    
    public Topic(Id id) {
        this(id, null, null);
    }
    
    public Topic(Id id, String namespace, String value) {
        setId(id);
        setMediaType(null);
        this.namespace = namespace;
        this.value = value;
    }
    
    @Override
    public Topic copy() {
        Topic topic = new Topic(getId(), namespace, value);
        topic.type = type;
        Described.copyTo(this, topic);
        return topic;
    }

    public Type getType() {
        return this.type;
    }

    @Deprecated
    public String getNamespace() {
        return this.namespace;
    }

    @Deprecated
    public String getValue() {
        return this.value;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    @Deprecated
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    @Deprecated
    public void setValue(String value) {
        this.value = value;
    }
    
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public Publisher getPublisher() {
        return this.publisher;
    }
}
