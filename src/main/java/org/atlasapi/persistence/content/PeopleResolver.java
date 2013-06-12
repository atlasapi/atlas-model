package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.Person;

public interface PeopleResolver {

    Person person(String uri);
    
    Person person(Long id);
    
}
