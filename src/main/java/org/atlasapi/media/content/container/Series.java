package org.atlasapi.media.content.container;


public final class Series extends Container {
	
    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends Container.Builder<Series, Builder> {
        
        private ContainerIdentifier brand;
        private Integer seriesNumber;
        private Integer totalEpisodes;
        
        @Override
        public Builder copy(Series series) {
            super.copy(series);
            return builder();
        }

        public Builder withBrand(Brand brand) {
            this.brand = brand.toIdentifier();
            return builder();
        }

        public Builder withSeriesNumber(Integer seriesNumber) {
            this.seriesNumber = seriesNumber;
            return builder();
        }

        public Builder withTotalEpisodes(Integer totalEpisodes) {
            this.totalEpisodes = totalEpisodes;
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Series build() {
            return new Series(this);
        }
        
    }
    
    private final ContainerIdentifier brand;
    private final Integer seriesNumber;
    private final Integer totalEpisodes;
	
	public Series(Builder builder) {
	    super(builder);
	    this.brand = builder.brand;
	    this.seriesNumber = builder.seriesNumber;
	    this.totalEpisodes = builder.totalEpisodes;
	}
	
	@Override
	public Builder copy() {
	    return new Builder().copy(this);
	}
	
	public ContainerIdentifier parent() {
	    return this.brand;
	}

	public Integer seriesNumber() {
		return this.seriesNumber;
	}
    public Integer totalEpisodes() {
        return this.totalEpisodes;
    }
}
