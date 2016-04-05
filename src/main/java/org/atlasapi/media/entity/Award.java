package org.atlasapi.media.entity;

import java.util.Objects;

public class Award {

    private String outcome;
    private String title;
    private String description;
    private Integer year;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Award award = (Award) o;
        return Objects.equals(outcome, award.outcome) &&
                Objects.equals(title, award.title) &&
                Objects.equals(description, award.description) &&
                Objects.equals(year, award.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outcome, title, description, year);
    }
}
