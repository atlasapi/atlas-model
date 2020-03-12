package org.atlasapi.equiv;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.applications.client.model.internal.ApplicationConfiguration;
import com.metabroadcast.applications.client.model.internal.SourcesOrdering;

import org.atlasapi.media.entity.Encoding;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Quality;
import org.atlasapi.media.entity.Version;

import org.joda.time.Duration;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YouViewOutputContentMergerTest {

    @Mock
    private final Application application = mock(Application.class);
    @Mock
    private final ApplicationConfiguration applicationConfig = mock(ApplicationConfiguration.class);

    private final OutputContentMerger merger = new YouViewOutputContentMerger();

    private final Item fourKItem = createItem(1L, Quality.FOUR_K);
    private final Item fourKItemWithSd = createItem(2L, Quality.FOUR_K, Quality.SD);
    private final Item hdItem = createItem(3L, Quality.HD);

    @Test
    public void mergeIn() {

        when(applicationConfig.isPrecedenceEnabled()).thenReturn(true);
        when(application.getConfiguration()).thenReturn(applicationConfig);
        when(application.getConfiguration().getReadPrecedenceOrdering()).thenReturn(SourcesOrdering.create(ImmutableList.of(Publisher.BBC)));

        merger.mergeIn(application, fourKItemWithSd, ImmutableList.of(fourKItem, hdItem));

        assertThat(fourKItemWithSd.getDescription(), is((hdItem.getDescription())));
    }


    private Item createItem(Long id, Quality... qs) {
        Item item = new Item();
        item.setId(id);
        for (Quality q : qs) {
            item.addVersion(createVersion(q));
        }

        item.setDescription(Arrays.toString(qs));
        item.setPublisher(Publisher.AMAZON_UNBOX);

        return item;
    }


    private Version createVersion(Quality q) {
        Encoding encoding = new Encoding();
        encoding.setQuality(q);

        Version version = new Version();
        version.setManifestedAs(ImmutableSet.of(encoding));
        return version;
    }
}