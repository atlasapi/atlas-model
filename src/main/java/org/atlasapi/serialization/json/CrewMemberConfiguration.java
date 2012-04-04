package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.content.item.CrewMember;

/**
 */
@JsonDeserialize(builder=CrewMember.Builder.class)
public interface CrewMemberConfiguration {
    
}
