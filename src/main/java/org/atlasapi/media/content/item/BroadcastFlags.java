package org.atlasapi.media.content.item;

public final class BroadcastFlags {

    public static final Builder builder() {
        //TODO: given there's a large but limited number of distinct sets, can we memoize?
        return new Builder();
    }
    
    public final static class Builder {
        
        private Boolean repeat;
        private Boolean subtitled;
        private Boolean signed;
        private Boolean audioDescribed;
        private Boolean highDefinition;
        private Boolean widescreen;
        private Boolean surround;
        private Boolean live;
        private Boolean newSeries;
        private Boolean premiere;
        
        public Builder copy(BroadcastFlags broadcast) {
            this.repeat = broadcast.repeat;
            this.subtitled = broadcast.subtitled;
            this.signed = broadcast.signed;
            this.audioDescribed = broadcast.audioDescribed;
            this.highDefinition = broadcast.highDefinition;
            this.widescreen = broadcast.widescreen;
            this.surround = broadcast.surround;
            this.live = broadcast.live;
            this.newSeries = broadcast.newSeries;
            this.premiere = broadcast.premiere;
            return this;
        }

        public Builder withrepeat(Boolean repeat) {
            this.repeat = repeat;
            return this;
        }

        public Builder withsubtitled(Boolean subtitled) {
            this.subtitled = subtitled;
            return this;
        }

        public Builder withsigned(Boolean signed) {
            this.signed = signed;
            return this;
        }

        public Builder withaudioDescribed(Boolean audioDescribed) {
            this.audioDescribed = audioDescribed;
            return this;
        }

        public Builder withhighDefinition(Boolean highDefinition) {
            this.highDefinition = highDefinition;
            return this;
        }

        public Builder withwidescreen(Boolean widescreen) {
            this.widescreen = widescreen;
            return this;
        }

        public Builder withsurround(Boolean surround) {
            this.surround = surround;
            return this;
        }

        public Builder withlive(Boolean live) {
            this.live = live;
            return this;
        }

        public Builder withnewSeries(Boolean newSeries) {
            this.newSeries = newSeries;
            return this;
        }

        public Builder withpremiere(Boolean premiere) {
            this.premiere = premiere;
            return this;
        }
        
        public BroadcastFlags build() {
            return new BroadcastFlags(this);
        }
    }
    
    private final Boolean repeat;
    private final Boolean subtitled;
    private final Boolean signed;
    private final Boolean audioDescribed;
    private final Boolean highDefinition;
    private final Boolean widescreen;
    private final Boolean surround;
    private final Boolean live;
    private final Boolean newSeries;
    private final Boolean premiere;
    
    public BroadcastFlags(Builder b) {
        this.repeat = b.repeat;
        this.subtitled = b.subtitled;
        this.signed = b.signed;
        this.audioDescribed = b.audioDescribed;
        this.highDefinition = b.highDefinition;
        this.widescreen = b.widescreen;
        this.surround = b.surround;
        this.live = b.live;
        this.newSeries = b.newSeries;
        this.premiere = b.premiere;
    }
    

    public Boolean isRepeat() {
        return repeat;
    }
    public Boolean isSubtitled() {
        return subtitled;
    }
    
    public Boolean isSigned() {
        return signed;
    }

    public Boolean isAudioDescribed() {
        return audioDescribed;
    }

    public Boolean isHighDefinition() {
        return highDefinition;
    }

    public Boolean isWidescreen() {
        return widescreen;
    }

    public Boolean isSurround() {
        return surround;
    }

    public Boolean isLive() {
        return live;
    }
    
    public Boolean isPremiere() {
        return premiere;
    }
    
    public Boolean isNewSeries() {
        return newSeries;
    }
}
