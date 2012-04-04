package org.atlasapi.media.content.item;

public final class Actor extends CrewMember {

    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends CrewMember.BaseBuilder<Actor, Builder> {

        private String character;
        
        @Override
        public Builder copy(Actor actor) {
            super.copy(actor);
            this.character = actor.character;
            return builder();
        }
        
        public Builder withCharacter(String character) {
            this.character = character;
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return builder();
        }

        @Override
        public Actor build() {
            return new Actor(this);
        }
        
    }
    
    private String character;

    private Actor(Builder actorBuilder) {
        super(actorBuilder);
        this.character = actorBuilder.character;
    }
    
    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }
    
    public String character() {
        return character;
    }
}
