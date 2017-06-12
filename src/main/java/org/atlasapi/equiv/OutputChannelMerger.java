package org.atlasapi.equiv;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.common.stream.MoreCollectors;
import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Publisher;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;

public class OutputChannelMerger {

    private OutputChannelMerger() {}

    public static OutputChannelMerger create() {
        return new OutputChannelMerger();
    }

    public Channel merge(Application application, Channel channel, Iterable<Channel> equivalents) {

        if (!application.getConfiguration().isPrecedenceEnabled()) {
            return channel;
        }

        Map<Publisher, Channel> channelMap = Maps.newHashMap();

        channelMap.put(channel.getSource(), channel);

        StreamSupport.stream(equivalents.spliterator(), false)
                .filter(equivalentChannel -> application.getConfiguration()
                        .getEnabledReadSources()
                        .contains(equivalentChannel.getSource())
                )
                .forEach(equivalentChannel ->
                        channelMap.put(equivalentChannel.getSource(), equivalentChannel)
                );

        Channel mergedChannel = Channel.builder(channel).build();
        mergedChannel.setId(channel.getId());

        ImmutableList<Publisher> orderedPublishers = getOrderedPublishers(application);

        mergeAdvertiseFrom(orderedPublishers, channelMap, mergedChannel);
        mergeAdvertiseTo(orderedPublishers, channelMap, mergedChannel);
        mergeAliases(orderedPublishers, channelMap, mergedChannel);

        return mergedChannel;
    }

    private ImmutableList<Publisher> getOrderedPublishers(Application application) {
        Ordering<Publisher> ordering = application.getConfiguration()
                .getReadPrecedenceOrdering();

        return application.getConfiguration()
                .getEnabledReadSources()
                .stream()
                .sorted(ordering)
                .collect(MoreCollectors.toImmutableList());

    }

    private void mergeAdvertiseFrom(
            List<Publisher> publishers,
            Map<Publisher, Channel> channelMap,
            Channel mergedChannel
    ) {
        for (Publisher publisher : publishers) {
            Channel channel = channelMap.get(publisher);
            if(channel != null && channel.getAdvertiseFrom() != null) {
                mergedChannel.setAdvertiseFrom(channel.getAdvertiseFrom());
                break;
            }
        }
    }

    private void mergeAdvertiseTo(
            List<Publisher> publishers,
            Map<Publisher, Channel> channelMap,
            Channel mergedChannel
    ) {
        for (Publisher publisher : publishers) {
            Channel channel = channelMap.get(publisher);
            if(channel != null && channel.getAdvertiseTo() != null) {
                mergedChannel.setAdvertiseTo(channel.getAdvertiseTo());
                break;
            }
        }
    }

    private void mergeAliases(
            List<Publisher> publishers,
            Map<Publisher, Channel> channelMap,
            Channel mergedChannel
    ) {
        for (Publisher publisher : publishers) {
            Channel channel = channelMap.get(publisher);
            if (channel != null && channel.getAliases() != null && !channel.getAliases().isEmpty()) {
                channel.getAliases().forEach(alias -> {
                    if (!mergedChannel.getAliases().contains(alias)) {
                        mergedChannel.addAlias(alias);
                    }
                });
            }
        }
    }

}
