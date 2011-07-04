package org.atlasapi.media.entity;

import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class Film extends Item {
    
    private Integer year = null;
    private String websiteUrl = null;
    
    public Film(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
        setSpecialization(Specialization.FILM);
    }
    
    public Film() {
        setSpecialization(Specialization.FILM);
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
    
    public String getWebsiteUrl() {
        return websiteUrl;
    }
    
    @Override
	public Content copy() {
	    return copyWithVersions(Sets.newHashSet(Iterables.transform(this.getVersions(), Version.COPY)));
	}
	
    @Override
	public Film copyWithVersions(Set<Version> versions) {
	    Film film = new Film();
	    Item.copyToWithVersions(this, film, versions);
	    film.setYear(getYear());
	    film.setWebsiteUrl(getWebsiteUrl());
	    return film;
	}
}
