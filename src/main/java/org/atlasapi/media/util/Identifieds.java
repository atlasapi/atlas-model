package org.atlasapi.media.util;

import static org.atlasapi.media.entity.simple.Item.TO_PEOPLE;

import java.util.List;

import org.atlasapi.media.entity.simple.ContentIdentifier;
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

public class Identifieds {

    private Identifieds() {
    }
    
    public static Function<Identified, Description> TO_DESCRIPTION = new Function<Identified, Description>() {
        @Override
        public Description apply(Identified input) {
            return (Description) input;
        }
    };
    
    public static Predicate<Identified> IS_DESCRIPTION = new Predicate<Identified>() {
        @Override
        public boolean apply(Identified input) {
            return input instanceof Description;
        }
    };
    
    public static List<Description> getDescriptions(Iterable<? extends Identified> identifieds) {
        return ImmutableList.copyOf(Iterables.transform(Iterables.filter(identifieds, IS_DESCRIPTION), TO_DESCRIPTION));
    }
    
    private static Iterable<Person> getEmbeddedPeople(Iterable<Item> items) {
        return Iterables.concat(Iterables.transform(items, TO_PEOPLE));
    }
    
    private static Iterable<Identified> getEmbeddedBrandsAndSeries(Iterable<Item> items) {
        return Iterables.concat(Iterables.transform(items, TO_BRAND_AND_SERIES_SUMMARIES));
    }
    
    public static Iterable<Identified> getFlattenedIdentifieds(Iterable<? extends Identified> identifieds) {
        
        Iterable<Person> people = Iterables.filter(identifieds, Person.class);
        Iterable<Description> allDescriptions = Iterables.filter(identifieds, Description.class);
        
        List<Item> allItems = Descriptions.getItems(allDescriptions);
        Iterable<Identified> embeddedBrandsAndSeries = getEmbeddedBrandsAndSeries(allItems);
        Iterable<Person> embeddedPeople = getEmbeddedPeople(allItems);
        
        return Iterables.concat(allDescriptions, embeddedBrandsAndSeries, people, embeddedPeople);
    }
    
    public static Iterable<ContentIdentifier> getContentIdentifiers(Iterable<? extends Identified> identifieds) {
        Builder<ContentIdentifier> builder = ImmutableList.builder();
        
        Iterable<Playlist> playlists = Iterables.filter(identifieds, Playlist.class);
        for (Playlist playlist : playlists) {
            builder.addAll(playlist.getContent());
            builder.addAll(playlist.getSeriesList());
            if (playlist.getAvailableContent() != null) {
                builder.addAll(playlist.getAvailableContent());
            }
            if (playlist.getUpcomingContent() != null) {
                builder.addAll(playlist.getUpcomingContent());
            }
        }
        
        Iterable<Person> people = Iterables.filter(identifieds, Person.class);
        for (Person person : people) {
           // people embedded in items/brands don't have content attached
           if (person.getContent() != null) {
               builder.addAll(person.getContent());
           }
        }
        
        return builder.build();
    }
    
    private static final Function<Item, Iterable<Identified>> TO_BRAND_AND_SERIES_SUMMARIES = new Function<Item, Iterable<Identified>>() {
        @Override
        public Iterable<Identified> apply(Item input) {
            
            Builder<Identified> builder = ImmutableList.builder();
            if (input.getSeriesSummary() != null) {
                builder.add(input.getSeriesSummary());
            }
            if (input.getBrandSummary() != null) {
                builder.add(input.getBrandSummary());
            }
            return builder.build();
        }
    };
}
