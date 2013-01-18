package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.metabroadcast.common.intl.Country;

public abstract class ChannelGroup extends Identified {

    private Publisher publisher;
    private Set<TemporalString> titles = Sets.newHashSet();
    private Set<Country> availableCountries = Sets.newHashSet();
    private Set<ChannelNumbering> channelNumberings = ImmutableSet.of();

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public String getTitle() {
        return TemporalString.currentValue(titles);
    }
    
    public String getTitleForDate(LocalDate date) {
        return TemporalString.valueForDate(titles, date);
    }
    
    public Iterable<TemporalString> getAllTitles() {
        return ImmutableSet.copyOf(titles);
    }

    public void addTitle(String title) {
        addTitle(title, null, null);
    }

    public void addTitle(String title, LocalDate startDate) {
        addTitle(title, startDate, null);
    }

    public void addTitle(String title, LocalDate startDate, LocalDate endDate) {
        this.titles.add(new TemporalString(title, startDate, endDate));
    }

    public void addTitle(TemporalString title) {
        this.titles.add(title);
    }

    public void setTitles(Iterable<TemporalString> titles) {
        this.titles = Sets.newHashSet(titles);
    }

    public Set<Country> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(Set<Country> availableCountries) {
        this.availableCountries = availableCountries;
    }
    
    public void addAvailableCountry(Country country) {
        this.availableCountries.add(country);
    }

    @Deprecated
    public Set<Id> getChannels() {
        return ImmutableSet.copyOf(Iterables.transform(getChannelNumberings(), new Function<ChannelNumbering, Id>() {
            @Override
            public Id apply(ChannelNumbering input) {
                return input.getChannel();
            }
        }));
    }

    @Deprecated
    public void setChannels(Iterable<Id> channels) {
        for (Id channelId : channels) {
            addChannelNumbering(ChannelNumbering.builder()
                .withChannel(channelId)
                .withChannelGroup(getId())
                .build());
        }
    }
    
    @Deprecated
    public void addChannel(Id channel) {
        addChannelNumbering(ChannelNumbering.builder()
                .withChannel(channel)
                .withChannelGroup(getId())
                .build());
    }
    
    public Set<ChannelNumbering> getChannelNumberings() {
        return ImmutableSet.copyOf(channelNumberings);
    }

    public void setChannelNumberings(Iterable<ChannelNumbering> channelNumberings) {
        this.channelNumberings = ImmutableSet.copyOf(channelNumberings);
    }
    
    public void addChannelNumbering(ChannelNumbering channelNumbering) {
        this.channelNumberings = ImmutableSet.<ChannelNumbering>builder().addAll(channelNumberings).add(channelNumbering).build();
    }
}
