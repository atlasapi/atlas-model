package org.atlasapi.media.content.container;

import com.google.common.collect.ImmutableList;

/**
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 */
public final class Brand extends Container {
    
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Container.Builder<Brand, Builder> {

        private ImmutableList<ContainerIdentifier> seriesRefs;
        
        public Builder withSeries(ImmutableList<Series> series) {
            ImmutableList.Builder<ContainerIdentifier> seriesRefs = ImmutableList.builder();
            for (Series serie : series) {
                seriesRefs.add(serie.toIdentifier());
            }
            this.seriesRefs = seriesRefs.build();
            return builder();
        }

        //TODO: check visibility;
        public Builder withSeriesRefs(ImmutableList<ContainerIdentifier> series){
            this.seriesRefs = series;
            return builder();
        }
        
        @Override
        public Builder copy(Brand brand) {
            super.copy(brand);
            
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Brand build() {
            return new Brand(this);
        }
        
    }
    
    private final ImmutableList<ContainerIdentifier> seriesRefs;

    public Brand(Builder builder) {
        super(builder);
        this.seriesRefs = builder.seriesRefs;
    }
    
    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }

    public ImmutableList<ContainerIdentifier> seriesRefs() {
        return seriesRefs;
    }

}
