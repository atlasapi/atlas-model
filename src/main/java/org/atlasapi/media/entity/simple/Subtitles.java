package org.atlasapi.media.entity.simple;

public class Subtitles {

    private Language language;

    public Subtitles() {    }
    
    public Subtitles(Language lang) {
        this.language = lang;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
}
