package org.atlasapi.media.channel;

public class Region extends ChannelGroup {
    private Long platform;

    public Long getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform.getId();
    }
    
    public void setPlatform(Long platformId) {
        this.platform = platformId;
    }
}
