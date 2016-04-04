package org.atlasapi.media.entity;

import java.util.Objects;

public class Award {

    private String outcome;
    private String title;
    private String description;
    private String year;

    public Award() {

    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj instanceof Award) {
            Award award = (Award) obj;
            return  Objects.equals(outcome, award.outcome) &&
                    Objects.equals(title, award.title) &&
                    Objects.equals(description, award.description) &&
                    Objects.equals(year, award.year);
        }

        return false;
    }
}
