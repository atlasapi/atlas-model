package org.atlasapi.media.entity;

import java.util.Set;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.ItemVisitor;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class Film extends Item {
    
    private String websiteUrl = null;
    private Set<Subtitles> subtitles = ImmutableSet.of();
    private Set<ReleaseDate> releaseDates = ImmutableSet.of();
    
    public Film(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
        setSpecialization(Specialization.FILM);
    }
        
    public Film(Id id, Publisher source) {
        super(id, source);
    }
    
    public Film() {
        setSpecialization(Specialization.FILM);
    }
    
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
    
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public Set<Subtitles> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(Iterable<Subtitles> subtitles) {
        this.subtitles = ImmutableSet.copyOf(subtitles);
    }

    public Set<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(Iterable<ReleaseDate> releaseDates) {
        this.releaseDates = ImmutableSet.copyOf(releaseDates);
    }

    @Override
	public Film copy() {
	    return copyWithVersions(Sets.newHashSet(Iterables.transform(this.getVersions(), Version.COPY)));
	}
	
    @Override
	public Film copyWithVersions(Set<Version> versions) {
	    Film film = new Film();
	    Item.copyToWithVersions(this, film, versions);
	    film.setWebsiteUrl(getWebsiteUrl());
	    film.setSubtitles(getSubtitles());
	    film.setReleaseDates(getReleaseDates());
	    return film;
	}

    public <V> V accept(ItemVisitor<V> visitor) {
        return visitor.visit(this);
    }
    
}
