package org.atlasapi.media.entity;

public class CrewMember extends Person {
    
    public enum Role {
        ABRIDGED_BY("abridged_by", "Abridged By"),
        DEPUTY_EDITOR("deputy_editor", "Deputy Editor"),
        DIRECTOR("director", "Director"),
        DRAMATISED_BY("dramatised_by", "Dramatised By"),
        EDITOR("editor", "Editor"),
        EXECUTIVE_EDITOR("executive_editor", "Executive Editor"),
        EXECUTIVE_PRODUCER("executive_producer", "Executive Producer"),
        PRODUCER("producer", "Producer"),
        SERIES_DIRECTOR("series_director", "Series Director"),
        SERIES_EDITOR("series_editor", "Series Editor"),
        SERIES_PRODUCER("series_producer", "Series Producer"),
        WRITER("writer", "Writer");
        
        private final String key;
        private final String title;
        
        private Role(String key, String title) {
            this.key = key;
            this.title = title;
        }
        
        public String key() {
            return key;
        }
        
        public String title() {
            return title;
        }
        
        public static Role fromKey(String key) {
            for (Role role: Role.values()) {
                if (role.key.equals(key)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Unkown role: "+key);
        }
    }
    
    private Role role;
    
    public CrewMember() {
        super();
    }
    
    public CrewMember(String uri) {
        super(uri);
    }
    
    public Role role() {
        return role;
    }
    
    public CrewMember withRole(Role role) {
        this.role = role;
        return this;
    }
    
    @Override
    public CrewMember withName(String name) {
        this.name = name;
        return this;
    }
    
    @Override
    public CrewMember withProfileLink(String profileLink) {
        this.profileLink = profileLink;
        return this;
    }
    
    public static CrewMember crewMember(String name, String roleKey, Publisher publisher) {
        Role role = Role.fromKey(roleKey);
        return new CrewMember(String.format(BASE_URI, publisher.key(), formatForUri(name+':'+role.key)))
            .withRole(role)
            .withName(name);
    }
}
