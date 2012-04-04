package org.atlasapi.media.content.item;

import java.util.Map;

import org.atlasapi.media.common.OptionalMap;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public enum CrewRole {
    
    ABRIDGED_BY("abridged_by", "Abridged By"),
    ART_DIRECTOR("art_director", "Art Director"), 
    SUPERVISING_DIRECTOR("supervising_director", "Supervising Director"),
    SOURCE_WRITER("source_writer", "Source Writer"),
    ASSISTANT_DIRECTOR("assistant_director", "Assistant Director"),
    ASSOCIATE_DIRECTOR("associate_director", "Associate Director"),
    ADDITIONAL_DIRECTOR("additional_director", "Additional Director"),
    COLLABORATING_DIRECTOR("collaborating_director", "Collaborating Director"),
    CO_DIRECTOR("co-director", "Co-Director"),
    DEPUTY_EDITOR("deputy_editor", "Deputy Editor"),
    DIRECTOR("director", "Director"),
    DRAMATISED_BY("dramatised_by", "Dramatised By"),
    EDITOR("editor", "Editor"),
    EXECUTIVE_EDITOR("executive_editor", "Executive Editor"),
    EXECUTIVE_PRODUCER("executive_producer", "Executive Producer"),
    PRODUCER("producer", "Producer"),
    SERIES_DIRECTOR("series_director", "Series Director"),
    SERIES_EDITOR("series_editor", "Series Editor"),
    SERIES_PRODUCER("series_producer", "Series Producer"),
    WRITER("writer", "Writer"),
    ADAPTED_BY("adapted_by", "Adapted By"),
    PRESENTER("presenter", "Presenter"),
    COMPOSER("composer", "Composer"),
    ACTOR("actor", "Actor"),
    PARTICIPANT("participant", "Participant"),
    NARRATOR("narrator", "Narrator"),
    REPORTER("reporter", "Reporter"),
    COMMENTATOR("commentator", "Commentator"),
    EXPERT("expert", "Expert"),
    ARTIST("artist", "Artist");
    
    private final String key;
    private final String title;
    
    private CrewRole(String key, String title) {
        this.key = key;
        this.title = title;
    }
    
    public String key() {
        return key;
    }
    
    public String title() {
        return title;
    }
    
    public static final ImmutableSet<CrewRole> CREW_ROLES = ImmutableSet.copyOf(values());

    
    public static Function<CrewRole, String> TO_KEY = new Function<CrewRole, String>() {
        @Override
        public String apply(CrewRole from) {
            return from.key();
        }
    };

    private static final Map<String, Optional<CrewRole>> KEY_MAP = 
            OptionalMap.fromMap(Maps.uniqueIndex(CREW_ROLES, TO_KEY));

    
    public static final Optional<CrewRole> fromKey(String key) {
        return KEY_MAP.get(key);
    }

    public static final Function<String, Optional<CrewRole>> FROM_KEY = new Function<String, Optional<CrewRole>>() {
        @Override
        public Optional<CrewRole> apply(String key) {
            return fromKey(key);
        }
    };

}