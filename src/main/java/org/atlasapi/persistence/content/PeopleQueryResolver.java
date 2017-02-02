package org.atlasapi.persistence.content;

import org.atlasapi.application.v3.ApplicationConfiguration;
import org.atlasapi.media.entity.Person;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.metabroadcast.common.query.Selection;

public interface PeopleQueryResolver {

    Optional<Person> person(String uri, ApplicationConfiguration config);
    
    Optional<Person> person(Long id, ApplicationConfiguration config);
    
    Iterable<Person> people(Iterable<String> uris, ApplicationConfiguration config);

    Iterable<Person> people(Iterable<Publisher> publishers, ApplicationConfiguration config,
            Selection selection);
    
}
