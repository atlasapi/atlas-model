package org.atlasapi.media.entity;

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
    public Film copy() {
        Film copy = new Film();
        Film.copyTo(this, copy);
        return copy;
    }
    
    public static void copyTo(Film from, Film to) {
        Item.copyTo(from, to);
        
        to.setYear(from.getYear());
        to.setWebsiteUrl(from.getWebsiteUrl());
    }
}
