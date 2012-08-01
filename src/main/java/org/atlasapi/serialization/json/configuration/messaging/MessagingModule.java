package org.atlasapi.serialization.json.configuration.messaging;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.atlasapi.persistence.messaging.event.EntityUpdatedEvent;

/**
 */
public class MessagingModule extends SimpleModule {

    public MessagingModule() {
        super("Messaging Module", new com.fasterxml.jackson.core.Version(0, 0, 1, null, null, null));
    }

    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        context.setMixInAnnotations(EntityUpdatedEvent.class, AbstractEventConfiguration.class);
    }
}
