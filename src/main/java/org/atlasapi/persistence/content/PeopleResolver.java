package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.Person;

import com.google.common.base.Optional;

public interface PeopleResolver {

    Optional<Person> person(String uri);
    
    Optional<Person> person(Long id);
    
    Iterable<Person> people(Iterable<LookupRef> lookupRefs);
}
