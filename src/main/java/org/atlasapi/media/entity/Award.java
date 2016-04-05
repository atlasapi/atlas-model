package org.atlasapi.media.entity;

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

        if (!outcome.equals(award.outcome))
            return false;
        if (!title.equals(award.title))
            return false;
        if (!description.equals(award.description))
            return false;
        return year.equals(award.year);

    }

    @Override
    public int hashCode() {
        int result = outcome.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

}
