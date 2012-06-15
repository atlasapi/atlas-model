package org.atlasapi.persistence.content.people;

import org.atlasapi.media.content.Person;

public interface PersonWriter {

    void updatePersonItems(Person person);
    
    void createOrUpdatePerson(Person person);
}
