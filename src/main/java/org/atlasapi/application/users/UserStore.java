package org.atlasapi.application.users;

import org.atlasapi.media.common.Id;

import com.google.common.base.Optional;
import com.metabroadcast.common.social.model.UserRef;

public interface UserStore {

    Optional<User> userForRef(UserRef ref);
    
    Optional<User> userForId(Id id);
    
    Iterable<User> usersFor(Iterable<Id> ids);
    
    Iterable<User> allUsers();
    
    void store(User user);
    
}
