package org.atlasapi.media.entity.testing;

import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Version;
import org.joda.time.DateTime;
import org.joda.time.Duration;

public class ComplexBroadcastTestDataBuilder {
    private String channel;
    private DateTime startTime;
    private Duration duration;
    
    public static ComplexBroadcastTestDataBuilder broadcast() {
        return new ComplexBroadcastTestDataBuilder();
    }
    
    private ComplexBroadcastTestDataBuilder() {
        channel = "bbcone";
        startTime = new DateTime();
        duration = Duration.standardMinutes(60);
    }
    
    public Broadcast build() {
        Broadcast broadcast = new Broadcast(channel, startTime, duration);
        
        return broadcast;
    }
    
    public Version buildInVersion() {
        Version version = new Version();
        
        version.addBroadcast(build());
        
        return version;
    }
    
    public ComplexBroadcastTestDataBuilder withChannel(String channel) {
        this.channel = channel;
        return this;
    }
    
    public ComplexBroadcastTestDataBuilder withStartTime(DateTime startTime) {
        this.startTime = startTime;
        return this;
    }
    
    public ComplexBroadcastTestDataBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
}
