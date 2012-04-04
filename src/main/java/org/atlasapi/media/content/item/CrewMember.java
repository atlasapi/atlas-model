package org.atlasapi.media.content.item;

import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;

public class CrewMember extends Identified {
    
    public static BaseBuilder<? extends CrewMember, ?> builder() {
        return new Builder();
    }

    public static abstract class BaseBuilder<T extends CrewMember, B extends BaseBuilder<T, B>> extends Identified.Builder<T, B> {

        protected CrewRole role;
        protected String name;
        
        public B copy(T crewMember) {
            super.copy(crewMember);
            this.role = crewMember.role;
            this.name = crewMember.name;
            return builder();
        };

        public B withrole(CrewRole role) {
            this.role = role;
            return builder();
        }

        public B withname(String name) {
            this.name = name;
            return builder();
        }

    }

    public static class Builder extends BaseBuilder<CrewMember,Builder> {

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public CrewMember build() {
            return new CrewMember(this);
        }
        
    }
    
    protected final CrewRole role;
    protected final String name;
    
    CrewMember(BaseBuilder<? extends CrewMember, ? extends BaseBuilder<? extends CrewMember,?>> b) {
        super(b);
        this.role = b.role;
        this.name = b.name;
    }
    
    public BaseBuilder<? extends CrewMember, ? extends BaseBuilder<? extends CrewMember, ?>> copy() {
        return new Builder().copy(this);
    }

    public CrewRole role() {
        return role;
    }
    
    public String name() {
        return name;
    }
 
    @Override
    public Identifier toIdentifier() {
        return new CrewMemberIdentifier(this);
    }
    
    public static class CrewMemberIdentifier extends Identifier {

        protected CrewMemberIdentifier(Identified identified) {
            super(identified);
        }
        
    }
}
