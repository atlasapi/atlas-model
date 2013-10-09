package org.atlasapi.persistence.application;

import org.atlasapi.application.Application;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.IdResolver;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;

public interface ApplicationStore extends IdResolver<Application> {

    Iterable<Application> allApplications();

    Optional<Application> applicationFor(Id id);
    
    Application createApplication(Application application);
    
    Application updateApplication(Application application);

    Iterable<Application> applicationsFor(Iterable<Id> ids);
    
    Iterable<Application> readersFor(Publisher source);
    
    Iterable<Application> writersFor(Publisher source);

    Optional<Application> applicationForKey(String apiKey);
}
