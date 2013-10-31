package org.atlasapi.application.users;

import java.util.Set;

import org.atlasapi.application.Application;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.social.model.UserRef;

public class User implements Identifiable {

    private final Id id;
    private final UserRef userRef;
    private final String screenName;
    private final String fullName;
    private final String company;
    private final String email;
    private final String website;
    private final String profileImage;
    private final Role role;
    private final boolean profileComplete;
    
    private final Set<String> applicationSlugs;
    private final Set<Publisher> sources;
    
    private User(Id id, UserRef userRef, String screenName, String fullName,
            String company, String email, String website, String profileImage, Role role,
            Set<String> applicationSlugs, Set<Publisher> publishers, boolean profileComplete) {
        this.id = id;
        this.userRef = userRef;
        this.screenName = screenName;
        this.fullName = fullName;
        this.company = company;
        this.email = email;
        this.website = website;
        this.profileImage = profileImage;
        this.role = role;
        this.applicationSlugs = ImmutableSet.copyOf(applicationSlugs);
        this.sources = ImmutableSet.copyOf(publishers);
        this.profileComplete = profileComplete;
    }

    public UserRef getUserRef() {
        return this.userRef;
    }
    
    public String getScreenName() {
        return screenName;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getCompany() {
        return company;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public String getProfileImage() {
        return profileImage;
    }
    
    public Set<String> getApplicationSlugs() {
        return applicationSlugs;
    }
    
    public Set<Publisher> getSources() {
        return sources;
    }

    public Role getRole() {
        return this.role;
    }

    public Set<String> getApplications() {
        return this.applicationSlugs;
    }
    
    public boolean isProfileComplete() {
        return profileComplete;
    }
    
    public boolean manages(Application application) {
        return manages(application.getSlug());
    }
    
    public boolean manages(String applicationSlug) {
        return applicationSlugs.contains(applicationSlug);
    }
    
    public boolean manages(Optional<Publisher> possibleSource) {
        return possibleSource.isPresent() && sources.contains(possibleSource.get());
    }

    public Id getId() {
        return this.id;
    }

    public boolean is(Role role) {
        return this.role == role;
    }

    public User copyWithAdditionalApplication(Application application) {
        Set<String> applicationSlugs = ImmutableSet.<String>builder().add(application.getSlug()).addAll(this.getApplicationSlugs()).build();
        return this.copy().withApplicationSlugs(applicationSlugs).build();
    }

    public Builder copy() {
        return new Builder()
                    .withId(this.getId())
                    .withUserRef(this.getUserRef())
                    .withScreenName(this.getScreenName())
                    .withFullName(this.getFullName())
                    .withCompany(this.getCompany())
                    .withEmail(this.getEmail())
                    .withWebsite(this.getWebsite())
                    .withProfileImage(this.getProfileImage())
                    .withApplicationSlugs(this.getApplicationSlugs())
                    .withSources(this.getSources())
                    .withRole(this.getRole())
                    .withProfileComplete(this.isProfileComplete());
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Id id;
        private UserRef userRef;
        private String screenName;
        private String fullName;
        private String company;
        private String email;
        private String website;
        private String profileImage;
        private Role role = Role.REGULAR;
        private Set<String> applicationSlugs = ImmutableSet.of();
        private Set<Publisher> sources = ImmutableSet.of();
        private boolean profileComplete = false;
        
        public Builder withId(Id id) {
            this.id = id;
            return this;
        }
        
        public Builder withUserRef(UserRef userRef) {
            this.userRef = userRef;
            return this;
        }
        
        public Builder withScreenName(String screenName) {
            this.screenName = screenName;
            return this;
        }
        
        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        
        public Builder withCompany(String company) {
            this.company = company;
            return this;
        }
        
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        
        public Builder withWebsite(String website) {
            this.website = website;
            return this;
        }
        
        public Builder withProfileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }
        
        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }
        
        public Builder withApplicationSlugs(Set<String> applicationSlugs) {
            this.applicationSlugs = applicationSlugs;
            return this;
        }
        
        public Builder withSources(Set<Publisher> sources) {
            this.sources = sources;
            return this;
        }
        
        public Builder withProfileComplete(boolean profileComplete) {
            this.profileComplete = profileComplete;
            return this;
        }
        
        public User build() {
            Preconditions.checkNotNull(id);
            return new User(id, userRef, screenName, fullName,
                    company, email, website, profileImage, role,
                    applicationSlugs, sources, profileComplete);
        }
    }
    
}
