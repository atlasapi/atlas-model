package org.atlasapi.media.entity.simple;

public class Actor extends Person {

    public Actor() {
        super();
    }
    
    private String character;
    
    public String character() {
        return character;
    }
    
    public Actor withCharacter(String character) {
        this.character = character;
        return this;
    }
}
