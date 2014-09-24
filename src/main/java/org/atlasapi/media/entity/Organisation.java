package org.atlasapi.media.entity;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;


public class Organisation extends ContentGroup {

    private List<Person> members;
    private Set<Topic> eventGroups = Sets.newHashSet();
    
    public Organisation() {
        this(ImmutableList.<Person>of());
    }
    
    public Organisation(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
    }
    
    public List<Person> members() {
        return members;
    }
    
    public void setMembers(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
    }
    
    public Set<Topic> eventGroups() {
        return eventGroups;
    }
    
    public void setEventGroups(Iterable<Topic> eventGroups) {
        this.eventGroups = Sets.newHashSet(eventGroups);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Organisation.class)
                .add("title", getTitle())
                .add("members", members)
                .add("eventGroups", eventGroups)
                .add("uri", getCanonicalUri())
                .toString();
    }
    
    @Override
    public Organisation copy() {
        Organisation copy = new Organisation(this.members);
        copy.setEventGroups(this.eventGroups);
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
