package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(value = "Container")
public interface ContainerConfiguration {

    public static final String FILTER = "Container";
    public static final String CLIPS_FILTER = "clips";
    public static final String SUB_ITEMS_FILTER = "subItems";

}
