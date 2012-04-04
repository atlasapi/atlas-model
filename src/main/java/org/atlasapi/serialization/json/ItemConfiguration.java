package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 */
@JsonFilter(value = "Item")
public interface ItemConfiguration {

    public static final String FILTER = "Item";
    public static final String CLIPS_FILTER = "clips";
    public static final String VERSIONS_FILTER = "versions";

}
