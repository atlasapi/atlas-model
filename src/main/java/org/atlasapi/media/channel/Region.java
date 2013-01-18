package org.atlasapi.media.channel;

import org.atlasapi.media.common.Id;

public class Region extends ChannelGroup {
    
    private Id platform;

    public Id getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform.getId();
    }
    
    public void setPlatform(Id platformId) {
        this.platform = platformId;
    }
}
