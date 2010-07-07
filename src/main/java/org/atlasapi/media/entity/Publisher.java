package org.atlasapi.media.entity;

import com.metabroadcast.common.base.Maybe;

public enum Publisher {
    BBC("BBC", "bbc.co.uk", Countries.GB),
    C4("Channel 4", "channel4.com", Countries.GB),
    HULU("Hulu", "hulu.com", Countries.US),
    YOUTUBE("YouTube", "youtube.com", Countries.ALL),
    TED("TED", "ted.com", Countries.ALL),
    VIMEO("VIMEO", "vimeo.com", Countries.ALL);
    
    private final String key;
    private final Country country;
    private final String title;

    Publisher(String title, String key, Country country) {
        this.title = title;
        this.key = key;
        this.country = country;
    }
    public String title() {
        return title;
    }
    public String key() {
        return key;
    }
    public Country country() {
        return country;
    }
    
    public static Maybe<Publisher> fromKey(String key) {
        for (Publisher publisher: Publisher.values()) {
            if (key.equals(publisher.key())) {
                return Maybe.just(publisher);
            }
        }
        return Maybe.nothing();
    }
}
