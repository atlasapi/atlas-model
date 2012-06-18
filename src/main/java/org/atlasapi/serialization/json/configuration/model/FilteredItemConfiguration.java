package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 */
@JsonFilter(value = "Item")
public interface FilteredItemConfiguration {

    public static final String FILTER = "Item";
    public static final String CLIPS_FILTER = "clips";
    public static final String VERSIONS_FILTER = "versions";
}
