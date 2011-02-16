package org.atlasapi.media.entity.simple;

public class CrewMember extends Person {
    
    private String role;
    
    public CrewMember() {
        super();
    }
    
    public String role() {
        return role;
    }
    
    public CrewMember withRole(String role) {
        this.role = role;
        return this;
    }
}
