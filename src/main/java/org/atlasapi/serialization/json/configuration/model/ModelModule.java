package org.atlasapi.serialization.json.configuration.model;

import org.atlasapi.equiv.ContentRef;
import org.atlasapi.equiv.EquivalenceSummary;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Container;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Certificate;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Description;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.RelatedLink;
import org.atlasapi.media.entity.ReleaseDate;
import org.atlasapi.media.entity.Subtitles;
import org.atlasapi.media.entity.Topic;
import org.atlasapi.media.product.ProductLocation;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.metabroadcast.common.currency.Price;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.media.entity.ContentGroup;
import org.atlasapi.media.entity.Person;
import org.atlasapi.media.segment.SegmentRef;

/**
 */
public class ModelModule extends SimpleModule {

    public ModelModule() {
        super("Model Module", new com.fasterxml.jackson.core.Version(0, 0, 1, null, null, null));
    }

    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        this.addKeyDeserializer(Publisher.class, new PublisherConfiguration.PublisherKeyDeserializer());
        context.setMixInAnnotations(Id.class, IdConfiguration.class);
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
        context.setMixInAnnotations(Topic.class, TopicConfiguration.class);
        context.setMixInAnnotations(Description.class, DescriptionConfiguration.class);
        context.setMixInAnnotations(ProductLocation.class, ProductLocationConfiguration.class);
        context.setMixInAnnotations(LookupEntry.class, LookupEntryConfiguration.class);
        context.setMixInAnnotations(EquivalenceSummary.class, EquivalenceSummaryConfiguration.class);
        context.setMixInAnnotations(ContentRef.class, ContentRefConfiguration.class);
        context.setMixInAnnotations(ContentGroup.class, FilteredContentGroupConfiguration.class);
        context.setMixInAnnotations(Person.class, FilteredContentGroupConfiguration.class);
        context.setMixInAnnotations(SegmentRef.class, SegmentRefConfiguration.class);
        context.setMixInAnnotations(ContentRef.class, ContentRefConfiguration.class);
        context.setMixInAnnotations(Image.class, ImageConfiguration.class);
    }
}
