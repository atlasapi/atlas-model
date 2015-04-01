package org.atlasapi.media.entity;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class Film extends Item {
    
    private String websiteUrl = null;
    private Set<Subtitles> subtitles = ImmutableSet.of();

    public Film(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
        setSpecialization(Specialization.FILM);
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
	    return film;
	}

}
