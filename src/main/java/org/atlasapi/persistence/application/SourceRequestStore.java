package org.atlasapi.persistence.application;

import java.util.Set;

import org.atlasapi.application.SourceRequest;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;
import com.google.common.base.Optional;

public interface SourceRequestStore {
    
    void store(SourceRequest sourceRequest);
    
    Optional<SourceRequest> getBy(Id applicationId, Publisher source);
    
    Set<SourceRequest> sourceRequestsFor(Publisher source);
    
    Set<SourceRequest> all();
    
    Optional<SourceRequest> sourceRequestFor(Id id);

}
