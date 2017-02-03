package org.atlasapi.persistence.content;

import com.metabroadcast.applications.client.model.internal.Application;
import org.atlasapi.media.entity.Person;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.metabroadcast.common.query.Selection;

public interface PeopleQueryResolver {

    Optional<Person> person(String uri, Application config);
    
    Optional<Person> person(Long id, Application config);
    
    Iterable<Person> people(Iterable<String> uris, Application config);

    Iterable<Person> people(Iterable<Publisher> publishers, Application config,
            Selection selection);
    
}
