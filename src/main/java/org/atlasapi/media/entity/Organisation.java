package org.atlasapi.media.entity;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;


public class Organisation extends ContentGroup {

    private final List<Person> members;
    
    public Organisation(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
    }
    
    public List<Person> members() {
        return members;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Organisation.class)
                .add("title", getTitle())
                .add("members", members)
                .add("uri", getCanonicalUri())
                .toString();
    }
    
    @Override
    public Organisation copy() {
        Organisation copy = new Organisation(this.members);
        ContentGroup.copyTo(this, copy);
        return copy;
    }
    
    public static Function<Organisation, Organisation> COPY = new Function<Organisation, Organisation>() {
        @Override
        public Organisation apply(Organisation input) {
            return input.copy();
        }
    }; 
}
