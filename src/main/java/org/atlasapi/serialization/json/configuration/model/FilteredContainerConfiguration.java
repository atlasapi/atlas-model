package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 */
@JsonFilter(value = "Container")
public interface FilteredContainerConfiguration {

    public static final String FILTER = "Container";
    public static final String CLIPS_FILTER = "clips";
    public static final String CHILD_REFS_FILTER = "childRefs";
}
