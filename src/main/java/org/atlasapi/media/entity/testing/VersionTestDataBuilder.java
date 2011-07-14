package org.atlasapi.media.entity.testing;

import java.util.Set;

import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Encoding;
import org.atlasapi.media.entity.Version;

import com.google.common.collect.ImmutableSet;

public class VersionTestDataBuilder {
    private Set<Broadcast> broadcasts;
    private Set<Encoding> encodings;
    
    public static VersionTestDataBuilder version() {
        return new VersionTestDataBuilder();
    }
    
    private VersionTestDataBuilder() {
        broadcasts = ImmutableSet.of();
        encodings = ImmutableSet.of();
    }
    
    public Version build() {
        Version version = new Version();
        
        version.setBroadcasts(broadcasts);
        version.setManifestedAs(encodings);
        
        return version;
    }
    
    public VersionTestDataBuilder withBroadcasts(Broadcast...broadcasts) {
        this.broadcasts = ImmutableSet.copyOf(broadcasts);
        return this;
    }
}
