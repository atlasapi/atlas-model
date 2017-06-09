package org.atlasapi.media.entity.simple;

public class ChannelRef {

    private String id;
    private String uri;
    private String publisher;

    public ChannelRef() {

    }

    public ChannelRef(String id, String uri, String publisher) {
        this.id = id;
        this.uri = uri;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
