package org.atlasapi.media.entity.simple;

import java.util.Date;

public class ReleaseDate {

    private Date release_date;
    private String country;
    private String type;

    public ReleaseDate() {    }
    
    public ReleaseDate(Date date, String country, String type) {
        this.release_date = date;
        this.country = country;
        this.type = type;
    }

    public Date getDate() {
        return this.release_date;
    }

    public void setDate(Date date) {
        this.release_date = date;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
