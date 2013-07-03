package org.atlasapi.persistence.content;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.media.entity.Person;

import com.google.common.base.Optional;

public interface PeopleQueryResolver {

    Optional<Person> person(String uri, ApplicationConfiguration config);
    
    Optional<Person> person(Long id, ApplicationConfiguration config);
    
}
