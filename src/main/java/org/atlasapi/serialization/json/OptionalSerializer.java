package org.atlasapi.serialization.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Optional;

public class OptionalSerializer extends StdSerializer<Optional<?>> {

    public OptionalSerializer() {
        super(Optional.class, false);
    }
    
    @Override
    public void serialize(Optional<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (!value.isPresent()) {
            jgen.writeNullField("reference");
        }
        jgen.writeObjectField("reference", value.get());
    }

}
