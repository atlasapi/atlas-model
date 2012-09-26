package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 */
@JsonFilter(value = "ContentGroup")
public interface FilteredContentGroupConfiguration {

    public static final String FILTER = "ContentGroup";
    public static final String CONTENTS_FILTER = "contents";
}
