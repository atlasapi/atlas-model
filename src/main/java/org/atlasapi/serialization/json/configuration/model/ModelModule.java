package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.RelatedLink;

/**
 */
public class ModelModule extends SimpleModule {

    public ModelModule() {
        super("Model Module", new com.fasterxml.jackson.core.Version(0, 0, 1, null, null, null));
    }

    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        context.setMixInAnnotations(Container.class, FilteredContainerConfiguration.class);
        context.setMixInAnnotations(Item.class, FilteredItemConfiguration.class);
        context.setMixInAnnotations(Broadcast.class, BroadcastConfiguration.class);
        context.setMixInAnnotations(Country.class, CountryConfiguration.class);
        context.setMixInAnnotations(RelatedLink.class, RelatedLinkConfiguration.class);
        context.setMixInAnnotations(ParentRef.class, ParentRefConfiguration.class);
        context.setMixInAnnotations(ChildRef.class, ChildRefConfiguration.class);
    }
}
