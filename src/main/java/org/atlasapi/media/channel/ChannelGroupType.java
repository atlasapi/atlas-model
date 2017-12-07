package org.atlasapi.media.channel;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public enum ChannelGroupType {
    PLATFORM(Platform.class), 
    REGION(Region.class);
    
    private static Map<String, ChannelGroupType> STRING_LOOKUP = Maps.uniqueIndex(
            ImmutableList.copyOf(values()), Functions.toStringFunction()
    );
    
    private static Map<Class<? extends ChannelGroup>, ChannelGroupType> CLASS_LOOKUP =
            Maps.uniqueIndex(ImmutableList.copyOf(values()), input -> input.modelClass);

    private final Class<? extends ChannelGroup> modelClass;
    
    private ChannelGroupType(Class<? extends ChannelGroup> modelClass) {
        this.modelClass = modelClass;
    }
    
    public String toString() {
        return name().toLowerCase();
    }
    
    public Class<? extends ChannelGroup> getModelClass() {
        return modelClass;
    }
    
    public static ChannelGroupType from(String s) {
        ChannelGroupType type = STRING_LOOKUP.get(s);
        if (type == null) {
            throw new IllegalArgumentException("ChannelGroup type: " + s + " not recognised");
        }
        return type;
    }
    
    public static ChannelGroupType from(ChannelGroup channelGroup) {
        return from(channelGroup.getClass());
    }

    public static ChannelGroupType from(Class<? extends ChannelGroup> channelGroup) {
        ChannelGroupType type = CLASS_LOOKUP.get(channelGroup);
        if (type == null) {
            throw new IllegalArgumentException("ChannelGroup type: " + channelGroup + " not recognised");
        }
        return type;
    }
}
