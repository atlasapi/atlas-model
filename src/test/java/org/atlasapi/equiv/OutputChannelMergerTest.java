package org.atlasapi.equiv;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.applications.client.model.internal.ApplicationConfiguration;
import com.metabroadcast.common.stream.MoreCollectors;
import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Publisher;
import org.hamcrest.Matchers;
import org.jmock.auto.Mock;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutputChannelMergerTest {

    private OutputChannelMerger channelMerger;
    private Map<Long, Channel> testChannelStore;

    @Mock private Application btProdApp = mock(Application.class);
    @Mock private Application btTest1App = mock(Application.class);
    @Mock private Application noPrecedenceApp = mock(Application.class);


    @Before
    public void setUp() {
        this.channelMerger = OutputChannelMerger.create();
        buildTestChannels();

        when(btProdApp.getConfiguration()).thenReturn(
                ApplicationConfiguration.builder()
                    .withPrecedence(ImmutableList.of(
                            Publisher.BT_TVE_VOD,
                            Publisher.BT_TV_CHANNELS,
                            Publisher.BT_TV_CHANNELS_TEST1,
                            Publisher.PA,
                            Publisher.METABROADCAST
                    ))
                    .withEnabledWriteSources(ImmutableList.of())
                    .build()
        );

        when(btTest1App.getConfiguration()).thenReturn(
                ApplicationConfiguration.builder()
                    .withPrecedence(ImmutableList.of(
                            Publisher.BT_TVE_VOD,
                            Publisher.BT_TV_CHANNELS_TEST1,
                            Publisher.PA,
                            Publisher.METABROADCAST
                    ))
                    .withEnabledWriteSources(ImmutableList.of())
                    .build()
        );

        when(noPrecedenceApp.getConfiguration()).thenReturn(
                ApplicationConfiguration.builder()
                        .withNoPrecedence(ImmutableList.of(
                                Publisher.BT_TVE_VOD,
                                Publisher.PA,
                                Publisher.METABROADCAST,
                                Publisher.BT_TV_CHANNELS_TEST1
                        ))
                        .withEnabledWriteSources(ImmutableList.of())
                        .build()
        );

    }

    @Test
    public void channelIsMergedWithPrecedentData() throws Exception {

        Channel requestedChannel = testChannelStore.get(1L);
        Channel expectedEquivalent = testChannelStore.get(3L);
        Iterable<Channel> resolvedEquivalents = resolveSameAs(requestedChannel.getSameAs());

        Channel mergedChannel = channelMerger.merge(btProdApp, requestedChannel, resolvedEquivalents);

        checkIdAndSource(mergedChannel, requestedChannel);
        assertThat(mergedChannel.getAdvertiseFrom(), is(expectedEquivalent.getAdvertiseFrom()));
        assertThat(mergedChannel.getAdvertiseTo(), is(expectedEquivalent.getAdvertiseTo()));
    }

    @Test
    public void testMergesWithPrecedenceAndUsesExistingAsFallback() throws Exception {

        Channel requestedChannel = testChannelStore.get(1L);
        Channel expectedEquivalent = testChannelStore.get(4L);
        Iterable<Channel> resolvedEquivalents = resolveSameAs(requestedChannel.getSameAs());

        Channel mergedChannel = channelMerger.merge(btTest1App, requestedChannel, resolvedEquivalents);

        checkIdAndSource(mergedChannel, requestedChannel);
        assertThat(mergedChannel.getAdvertiseFrom(), is(expectedEquivalent.getAdvertiseFrom()));
        assertThat(mergedChannel.getAdvertiseTo(), is(requestedChannel.getAdvertiseTo()));

    }

    @Test
    public void testDoesntMergeOnApplicationWithNoPrecedence() throws Exception {

        Channel requestedChannel = testChannelStore.get(1L);
        Iterable<Channel> resolvedEquivalents = resolveSameAs(requestedChannel.getSameAs());

        Channel mergedChannel = channelMerger.merge(noPrecedenceApp, requestedChannel, resolvedEquivalents);

        checkIdAndSource(mergedChannel, requestedChannel);
        assertThat(mergedChannel.getAdvertiseFrom(), is(requestedChannel.getAdvertiseFrom()));
        assertThat(mergedChannel.getAdvertiseTo(), is(requestedChannel.getAdvertiseTo()));

    }

    @Test
    public void testDatesAreNullWhenNoPrecedenceOnApplication() throws Exception {

        Channel requestedChannel = testChannelStore.get(2L);
        Iterable<Channel> resolvedEquivalents = resolveSameAs(requestedChannel.getSameAs());

        Channel mergedChannel = channelMerger.merge(noPrecedenceApp, requestedChannel, resolvedEquivalents);

        assertThat(mergedChannel.getId(), is(requestedChannel.getId()));
        assertThat(mergedChannel.getAdvertiseFrom(), is(Matchers.nullValue()));
        assertThat(mergedChannel.getAdvertiseTo(), is(Matchers.nullValue()));
        assertThat(mergedChannel.getSource(), is(requestedChannel.getSource()));
    }

    @Test
    public void testDoesntMergeWhenRequestingEquivalentChannel() throws Exception {

        Channel requestedChannel = testChannelStore.get(5L);
        Iterable<Channel> resolvedEquivalents = resolveSameAs(requestedChannel.getSameAs());

        Channel mergedChannel = channelMerger.merge(btProdApp, requestedChannel, resolvedEquivalents);

        checkIdAndSource(mergedChannel, requestedChannel);
        assertThat(mergedChannel.getAdvertiseFrom(), is(requestedChannel.getAdvertiseFrom()));
        assertThat(mergedChannel.getAdvertiseTo(), is(requestedChannel.getAdvertiseTo()));
    }


    private void checkIdAndSource(Channel mergedChannel, Channel requestedChannel) {
        assertThat(mergedChannel.getId(), is(requestedChannel.getId()));
        assertThat(mergedChannel.getSource(), is(requestedChannel.getSource()));
    }

    private Iterable<Channel> resolveSameAs(Set<ChannelRef> refs) {
        return refs.stream()
                .map(ChannelRef::getId)
                .map(id -> testChannelStore.get(id))
                .collect(MoreCollectors.toImmutableList());
    }

    private void buildTestChannels() {

        DateTime now = DateTime.now()
                .withTime(0,0,0,0);

        testChannelStore = ImmutableMap.of(
            1L, createChannel(1L, Publisher.METABROADCAST, now.minusDays(3), now.plusDays(3), 1L, 3L, 4L),
            2L, createChannel(2L, Publisher.METABROADCAST, null, null, 2L, 5L),
            3L, createChannel(3L, Publisher.BT_TV_CHANNELS, now.minusDays(1), now.plusDays(1), 3L),
            4L, createChannel(4L, Publisher.BT_TV_CHANNELS_TEST1, now.minusDays(2), null, 4L),
            5L, createChannel(5L, Publisher.BT_TV_CHANNELS_TEST1, now.minusDays(7), now.plusDays(7), 5L)
        );
    }

    private Channel createChannel(
            long id,
            Publisher source,
            @Nullable DateTime advFrom,
            @Nullable DateTime advTo,
            long... equivIds
    ) {
        Channel channel = Channel.builder()
                .withSource(source)
                .withSameAs(Arrays.stream(equivIds)
                        .mapToObj(eId -> ChannelRef.create(eId, source.key() + eId, source))
                        .collect(Collectors.toSet())
                )
                .withAliases(
                        ImmutableSet.of(new Alias("pa:channel:id", "9" + id))
                )
                .withAdvertiseFrom(advFrom)
                .withAdvertiseTo(advTo)
                .build();
        channel.setId(id);

        return channel;
    }

}
