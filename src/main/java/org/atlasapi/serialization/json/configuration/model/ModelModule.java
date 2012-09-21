package org.atlasapi.serialization.json.configuration.model;

import org.atlasapi.equiv.ContentRef;
import org.atlasapi.equiv.EquivalenceSummary;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Certificate;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.RelatedLink;
import org.atlasapi.media.entity.ReleaseDate;
import org.atlasapi.media.entity.Subtitles;
import org.atlasapi.serialization.json.ContentRefConfiguration;
import org.atlasapi.serialization.json.EquivalenceSummaryConfiguration;
import org.atlasapi.serialization.json.PublisherConfiguration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.metabroadcast.common.currency.Price;
import com.metabroadcast.common.intl.Country;

/**
 */
public class ModelModule extends SimpleModule {

    public ModelModule() {
        super("Model Module", new com.fasterxml.jackson.core.Version(0, 0, 1, null, null, null));
    }

    @Override
    public void setupModule(Module.SetupContext context) {
        addKeyDeserializer(Publisher.class, new PublisherConfiguration.PublisherKeyDeserializer());
        super.setupModule(context);
        context.setMixInAnnotations(Container.class, FilteredContainerConfiguration.class);
        context.setMixInAnnotations(Item.class, FilteredItemConfiguration.class);
        context.setMixInAnnotations(Broadcast.class, BroadcastConfiguration.class);
        context.setMixInAnnotations(Country.class, CountryConfiguration.class);
        context.setMixInAnnotations(RelatedLink.class, RelatedLinkConfiguration.class);
        context.setMixInAnnotations(ParentRef.class, ParentRefConfiguration.class);
        context.setMixInAnnotations(ChildRef.class, ChildRefConfiguration.class);
        context.setMixInAnnotations(LookupRef.class, LookupRefConfiguration.class);
        context.setMixInAnnotations(Price.class, PriceConfiguration.class);
        context.setMixInAnnotations(Subtitles.class, SubtitlesConfiguration.class);
        context.setMixInAnnotations(ReleaseDate.class, ReleaseDateConfiguration.class);
        context.setMixInAnnotations(Certificate.class, CertificateConfiguration.class);
        context.setMixInAnnotations(EquivalenceSummary.class, EquivalenceSummaryConfiguration.class);
        context.setMixInAnnotations(ContentRef.class, ContentRefConfiguration.class);
    }
}
