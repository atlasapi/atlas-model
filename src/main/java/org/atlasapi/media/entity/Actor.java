package org.atlasapi.media.entity;

public class Actor extends Person {

    public Actor() {
        super();
    }
    
    private String character;
    
    public Actor(String uri) {
        super(uri);
    }
    
    public String character() {
        return character;
    }
    
    public Actor withCharacter(String character) {
        this.character = character;
        return this;
    }
}
