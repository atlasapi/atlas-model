package org.atlasapi.equiv;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Publisher;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChannelRef {

    private final long id;
    private final String uri;
    private final Publisher publisher;

    private ChannelRef(long id, String uri, Publisher publisher) {
        this.id = checkNotNull(id);
        this.uri = checkNotNull(uri);
        this.publisher = checkNotNull(publisher);
    }

    public static ChannelRef fromChannel(Channel channel) {
        return new ChannelRef(
                channel.getId(),
                channel.getCanonicalUri(),
                channel.getSource()
        );
    }

    public long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
