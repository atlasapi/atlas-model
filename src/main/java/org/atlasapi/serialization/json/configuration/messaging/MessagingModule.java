package org.atlasapi.serialization.json.configuration.messaging;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.atlasapi.messaging.BeginReplayMessage;
import org.atlasapi.messaging.EndReplayMessage;
import org.atlasapi.messaging.EntityUpdatedMessage;
import org.atlasapi.messaging.ReplayMessage;

/**
 */
public class MessagingModule extends SimpleModule {

    public MessagingModule() {
        super("Messaging Module", new com.fasterxml.jackson.core.Version(0, 0, 1, null, null, null));
    }

    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        context.setMixInAnnotations(EntityUpdatedMessage.class, AbstractMessageConfiguration.class);
        context.setMixInAnnotations(BeginReplayMessage.class, AbstractMessageConfiguration.class);
        context.setMixInAnnotations(ReplayMessage.class, ReplayMessageConfiguration.class);
        context.setMixInAnnotations(EndReplayMessage.class, AbstractMessageConfiguration.class);
    }
}
