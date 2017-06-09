package org.atlasapi.equiv;

import com.google.common.base.Objects;
import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Publisher;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChannelRef {

    private long id;
    private String uri;
    private Publisher publisher;

    public ChannelRef() {

    }

    private ChannelRef(long id, String uri, Publisher publisher) {
        this.id = checkNotNull(id);
        this.uri = checkNotNull(uri);
        this.publisher = checkNotNull(publisher);
    }

    public static ChannelRef create(long id, String uri, Publisher publisher) {
        return new ChannelRef(id, uri, publisher);
    }

    public static ChannelRef fromChannel(Channel channel) {
        return new ChannelRef(channel.getId(), channel.getUri(), channel.getSource());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ChannelRef) {
            ChannelRef target = (ChannelRef) obj;

        return Objects.equal(id, target.getId())
                && Objects.equal(uri, target.getUri())
                && Objects.equal(publisher, target.getPublisher());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, uri, publisher);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(ChannelRef.class)
                .add("id", id)
                .add("uri", uri)
                .add("publisher", publisher.key())
                .toString();
    }
}
