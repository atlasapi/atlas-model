package org.atlasapi.media.content.item;

import org.atlasapi.media.content.container.Container.ContainerIdentifier;
import org.atlasapi.media.content.container.Series;

public final class Episode extends Item {

    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends Item.BaseBuilder<Episode,Builder> {

        private ContainerIdentifier seriesRef;

        private Integer seriesNumber;
        private Integer episodeNumber;
        private Integer partNumber;

        public Builder withSeriesNumber(Integer seriesNumber) {
            this.seriesNumber = seriesNumber;
            return builder();
        }

        public Builder withEpisodeNumber(Integer episodeNumber) {
            this.episodeNumber = episodeNumber;
            return builder();
        }

        public Builder withPartNumber(Integer partNumber) {
            this.partNumber = partNumber;
            return builder();
        }

        public Builder withSeries(Series series) {
            this.seriesRef = series.toIdentifier();
            return builder();
        }

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Episode build() {
            return new Episode(this);
        }

    }

    private final ContainerIdentifier seriesRef;

    private final Integer seriesNumber;
    private final Integer episodeNumber;
    private final Integer partNumber;

    public Episode(Builder builder) {
        super(builder);
        this.seriesNumber = builder.seriesNumber;
        this.episodeNumber = builder.episodeNumber;
        this.partNumber = builder.partNumber;
        this.seriesRef = builder.seriesRef;
    }
    
    public ContainerIdentifier getSeriesRef() {
        return seriesRef;
    }

	public Integer getPartNumber() {
	    return this.partNumber;
	}

	public Integer getEpisodeNumber() {
		return episodeNumber;
	}
	
	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	
}
