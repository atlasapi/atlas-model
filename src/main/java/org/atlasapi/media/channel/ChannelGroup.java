package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
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
        TemporalString currentTitle = Iterables.getFirst(Iterables.filter(titles, new Predicate<TemporalString>() {
            @Override
            public boolean apply(TemporalString input) {
                if (input.getStartDate() != null) {
                    if (input.getEndDate() != null) {
                        return input.getStartDate().compareTo(new LocalDate()) <= 0
                            && input.getEndDate().compareTo(new LocalDate()) > 0;
                    } else {
                        return input.getStartDate().compareTo(new LocalDate()) <= 0;
                    }
                } else {
                    return true;
                }
            }
        }), null);
        if (currentTitle != null) {
            return currentTitle.getValue();
        }
        return null;
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
    public Set<Long> getChannels() {
        return ImmutableSet.copyOf(Iterables.transform(getChannelNumberings(), new Function<ChannelNumbering, Long>() {
            @Override
            public Long apply(ChannelNumbering input) {
                return input.getChannel();
            }
        }));
    }

    @Deprecated
    public void setChannels(Iterable<Long> channels) {
        for (Long channelId : channels) {
            addChannelNumbering(ChannelNumbering.builder()
                .withChannel(channelId)
                .withChannelGroup(getId())
                .build());
        }
    }
    
    @Deprecated
    public void addChannel(Long channel) {
        addChannelNumbering(ChannelNumbering.builder()
                .withChannel(channel)
                .withChannelGroup(getId())
                .build());
    }
    
    public Set<ChannelNumbering> getChannelNumberings() {
        return ImmutableSet.copyOf(Iterables.filter(channelNumberings, new Predicate<ChannelNumbering>() {
            @Override
            public boolean apply(ChannelNumbering input) {
                if (input.getStartDate() != null) {
                    if (input.getEndDate() != null) {
                        return input.getStartDate().compareTo(new LocalDate()) <= 0
                            && input.getEndDate().compareTo(new LocalDate()) > 0;
                        } else {
                            return input.getStartDate().compareTo(new LocalDate()) <= 0;
                    }
                } else {
                    return true;
                }
            }
        }));
    }
    
    public Set<ChannelNumbering> getAllChannelNumberings() {
        return ImmutableSet.copyOf(channelNumberings);
    }

    public void setChannelNumberings(Iterable<ChannelNumbering> channelNumberings) {
        this.channelNumberings = ImmutableSet.copyOf(channelNumberings);
    }
    
    public void addChannelNumbering(ChannelNumbering channelNumbering) {
        this.channelNumberings = ImmutableSet.<ChannelNumbering>builder().addAll(channelNumberings).add(channelNumbering).build();
    }
}
