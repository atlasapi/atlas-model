package org.atlasapi.media.entity;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class Organisation extends ContentGroup {

    private List<Person> members;
    private Set<String> alternativeTitles;

    public Organisation() {
        this(ImmutableList.<Person>of(), ImmutableSet.<String>of());
    }

    public Organisation(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
    }
    
    public Organisation(Iterable<Person> members, Iterable<String> alternativeTitles) {
        this.members = ImmutableList.copyOf(members);
        this.alternativeTitles = ImmutableSet.copyOf(alternativeTitles);
    }

    public Set<String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(Iterable<String> alternativeTitles) {
        this.alternativeTitles = ImmutableSet.copyOf(alternativeTitles);
    }
    public List<Person> members() {
        return members;
    }
    
    public void setMembers(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
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
        Organisation copy = new Organisation(this.members, this.alternativeTitles);
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
