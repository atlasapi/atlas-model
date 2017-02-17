package org.atlasapi.application.v3;

import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.ImmutableList;
import com.metabroadcast.applications.client.metric.Metrics;
import com.metabroadcast.applications.client.model.internal.AccessRoles;
import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.applications.client.model.internal.ApplicationConfiguration;
import com.metabroadcast.applications.client.model.internal.Environment;
import com.metabroadcast.common.properties.Configurer;
import com.metabroadcast.common.stream.MoreCollectors;
import org.atlasapi.media.entity.Publisher;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultApplication {

    private static final Environment APP_CLIENT_ENV = Environment.parse(checkNotNull(Configurer.get("applications.client.env").get()));

    public static Application createDefault() {
        return createInternal(
                getDefaultPublishers()
        );
    }

    public static Application createWithReads(List<Publisher> reads) {
        return createInternal(
                ImmutableList.<Publisher>builder()
                        .addAll(reads)
                        .addAll(
                                getDefaultPublishers().stream()
                                        .filter(publisher -> !reads.contains(publisher))
                                        .collect(Collectors.toList()))
                        .build()
        );
    }

    private static Application createInternal(
            List<Publisher> reads
    ) {
        return Application.builder()
                .withId(-1L)
                .withTitle("defaultApplication")
                .withDescription("Default application")
                .withEnvironment(APP_CLIENT_ENV)
                .withCreated(ZonedDateTime.now())
                .withApiKey("default")
                .withSources(configWithPrecedence(reads, ImmutableList.of()))
                .withAllowedDomains(ImmutableList.of())
                .withAccessRoles(
                        AccessRoles.create(
                                ImmutableList.of(),
                                Metrics.create(new MetricRegistry())
                        )
                )
                .withRevoked(false)
                .build();
    }

    private static ApplicationConfiguration configWithPrecedence(
            List<Publisher> reads,
            List<Publisher> writes
    ) {
        return ApplicationConfiguration.builder()
                .withPrecedence(reads)
                .withEnabledWriteSources(writes)
                .build();
    }

    private static List<Publisher> getDefaultPublishers() {
        return Publisher.all()
                .stream()
                .filter(Publisher::enabledWithNoApiKey)
                .collect(MoreCollectors.toImmutableList());
    }
}
