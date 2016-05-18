package org.atlasapi.media.channel;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public enum ScheduleResolvableType {

    CHANNEL(Channel.class),
    MASTERBRAND(Masterbrand.class);

    private static Map<String, ScheduleResolvableType> STRING_LOOKUP = Maps.uniqueIndex(ImmutableList.copyOf(values()), Functions
            .toStringFunction());

    private static Map<Class<? extends ScheduleResolvable>, ScheduleResolvableType> CLASS_LOOKUP = Maps.uniqueIndex(ImmutableList.copyOf(values()), new Function<ScheduleResolvableType, Class<? extends ScheduleResolvable>>(){
        @Override
        public Class<? extends ScheduleResolvable> apply(ScheduleResolvableType input) {
            return input.modelClass;
        }
    });

    private final Class<? extends ScheduleResolvable> modelClass;

    private ScheduleResolvableType(Class<? extends ScheduleResolvable> modelClass) {
        this.modelClass = modelClass;
    }

    public String toString() {
        return name().toLowerCase();
    }

    public Class<? extends ScheduleResolvable> getModelClass() {
        return modelClass;
    }

    public static ScheduleResolvableType from(String s) {
        ScheduleResolvableType type = STRING_LOOKUP.get(s);
        if (type == null) {
            throw new IllegalArgumentException("Entity type: " + s + " not recognised");
        }
        return type;
    }

    public static ScheduleResolvableType from(ScheduleResolvable entity) {
        return from(entity.getClass());
    }

    public static ScheduleResolvableType from(Class<? extends ScheduleResolvable> entity) {
        ScheduleResolvableType type = CLASS_LOOKUP.get(entity);
        if (type == null) {
            throw new IllegalArgumentException("Entity type: " + entity + " not recognised");
        }
        return type;
    }
}
