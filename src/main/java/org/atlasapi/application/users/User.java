package org.atlasapi.application.users;

import java.util.Set;

import org.atlasapi.application.Application;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.social.model.UserRef;

public class User {

    private final Long id;
    private UserRef userRef;
    private Role role = Role.REGULAR;
    
    private Set<String> applicationSlugs = ImmutableSet.of();
    private Set<Publisher> publishers = ImmutableSet.of();
    
    public User(Long id) {
        this.id = id;
    }

    public UserRef getUserRef() {
        return this.userRef;
    }

    public void setUserRef(UserRef userRef) {
        this.userRef = userRef;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<String> getApplications() {
        return this.applicationSlugs;
    }

    public void setApplications(Set<String> applicationSlugs) {
        this.applicationSlugs = ImmutableSet.copyOf(applicationSlugs);
    }
    
    public void addApplication(Application application) {
        this.applicationSlugs = ImmutableSet.<String>builder().add(application.getSlug()).addAll(applicationSlugs).build();
    }
    
    public boolean manages(Application application) {
        return manages(application.getSlug());
    }
    
    public boolean manages(String applicationSlug) {
        return applicationSlugs.contains(applicationSlug);
    }
    
    public boolean manages(Optional<Publisher> possibleSource) {
        return possibleSource.isPresent() && publishers.contains(possibleSource.get());
    }

    public Set<Publisher> getSources() {
        return this.publishers;
    }

    public void setSources(Set<Publisher> publisher) {
        this.publishers = publisher;
    }

    public Long getId() {
        return this.id;
    }

    public boolean is(Role role) {
        return this.role == role;
    }
    
}
