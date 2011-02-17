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
    
    @Override
    public Actor withName(String name) {
        this.name = name;
        return this;
    }
    
    @Override
    public Actor withProfileLink(String profileLink) {
        this.profileLink = profileLink;
        return this;
    }
    
    public static Actor actor(String name, String character, Publisher publisher) {
        return new Actor(String.format(BASE_URI, publisher.key(), formatForUri(name)))
            .withCharacter(character)
            .withName(name);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Actor) {
            Actor actor = (Actor) obj;
            return this.getCanonicalUri().equals(actor.getCanonicalUri()) && name.equals(actor.name) && character.equals(character);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getCanonicalUri().hashCode();
    }
    
    @Override
    public String toString() {
        return "Actor "+name+" plays "+character;
    }
}
