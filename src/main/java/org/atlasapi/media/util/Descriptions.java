package org.atlasapi.media.util;

import java.util.List;

import org.atlasapi.media.entity.simple.Description;
import org.atlasapi.media.entity.simple.Identified;
import org.atlasapi.media.entity.simple.Item;
import org.atlasapi.media.entity.simple.Person;
import org.atlasapi.media.entity.simple.Playlist;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableList.Builder;

public class Descriptions {
    private Descriptions() {
    }
    
    public static List<Item> getItems(Iterable<? extends Identified> descriptions) {
        return ImmutableList.copyOf(Iterables.transform(Iterables.filter(descriptions, IS_ITEM), TO_ITEM));
    }
    
    public static List<Playlist> getPlaylists(Iterable<? extends Identified> descriptions) {
        return ImmutableList.copyOf(Iterables.transform(Iterables.filter(descriptions, IS_PLAYLIST), TO_PLAYLIST));
    }
    
    public static List<Person> getPeople(Iterable<? extends Identified> descriptions) {
        return ImmutableList.copyOf(Iterables.transform(Iterables.filter(descriptions, IS_PERSON), TO_PERSON));
    }
    
    public static final Predicate<Identified> IS_PERSON = new Predicate<Identified>() {
        @Override
        public boolean apply(Identified input) {
            return input instanceof Person;
        }
    };
    
    public static final Predicate<Identified> IS_PLAYLIST = new Predicate<Identified>() {
        @Override
        public boolean apply(Identified input) {
            return input instanceof Playlist;
        }
    };
    
    public static final Predicate<Identified> IS_ITEM = new Predicate<Identified>() {
        @Override
        public boolean apply(Identified input) {
            return input instanceof Item;
        }
    };
    
    public static final Function<Identified, Person> TO_PERSON = new Function<Identified, Person>() {
        @Override
        public Person apply(Identified input) {
            return (Person) input;
        }
    };
    
    public static final Function<Identified, Playlist> TO_PLAYLIST = new Function<Identified, Playlist>() {
        @Override
        public Playlist apply(Identified input) {
            return (Playlist) input;
        }
    };
    
    public static final Function<Identified, Item> TO_ITEM = new Function<Identified, Item>() {
        @Override
        public Item apply(Identified input) {
            return (Item) input;
        }
    };
    
    public static final Function<Playlist, Iterable<Description>> FLATTEN_PLAYLIST = new Function<Playlist, Iterable<Description>>() {
        @Override
        public Iterable<Description> apply(Playlist from) {
            Builder<Description> flattened = ImmutableList.builder();
            
            flattened.add(from);
            if (from.getContent() != null && ! from.getContent().isEmpty()) {
                flattened.addAll(from.getContent());
            }
            
            return flattened.build();
        }
    };
}
