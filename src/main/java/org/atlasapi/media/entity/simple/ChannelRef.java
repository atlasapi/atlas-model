package org.atlasapi.media.entity.simple;

public class ChannelRef {

    private final String id;
    private final String uri;
    private final String publisher;

    public ChannelRef(String id, String uri, String publisher) {
        this.id = id;
        this.uri = uri;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getPublisher() {
        return publisher;
    }

}
