package org.atlasapi.media.content.item;

import java.util.Set;

import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;

import com.metabroadcast.common.media.MimeType;
import com.metabroadcast.common.units.ByteCount;

/**
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 */
public final class Encoding extends Identified {
    
    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends Identified.Builder<Encoding, Builder> {

        protected Integer advertisingDuration;
        protected Boolean containsAdvertising;
        
        protected Integer bitRate;
        protected Integer audioBitRate;
        protected Integer audioChannels;
        protected MimeType audioCoding;
        
        protected String videoAspectRatio;
        protected Integer videoBitRate;
        protected MimeType videoCoding;
        protected Float videoFrameRate;
        protected Integer videoHorizontalSize;
        protected Boolean videoProgressiveScan;
        protected Integer videoVerticalSize;
        
        protected MimeType dataContainerFormat;
        protected Long dataSize;
        protected String source;
        protected String distributor;
        protected Boolean hasDOG;
        protected Set<Location> locations;

        @Override
        public Builder copy(Encoding encoding) {
            super.copy(encoding);
            this.advertisingDuration = encoding.advertisingDuration;
            this.containsAdvertising = encoding.containsAdvertising;
            this.bitRate = encoding.bitRate;
            this.audioBitRate = encoding.audioBitRate;
            this.audioChannels = encoding.audioChannels;
            this.audioCoding = encoding.audioCoding;
            this.videoAspectRatio = encoding.videoAspectRatio;
            this.videoBitRate = encoding.videoBitRate;
            this.videoCoding = encoding.videoCoding;
            this.videoFrameRate = encoding.videoFrameRate;
            this.videoHorizontalSize = encoding.videoHorizontalSize;
            this.videoProgressiveScan = encoding.videoProgressiveScan;
            this.videoVerticalSize = encoding.videoVerticalSize;
            this.dataContainerFormat = encoding.dataContainerFormat;
            this.dataSize = encoding.dataSize;
            this.source = encoding.source;
            this.distributor = encoding.distributor;
            this.hasDOG = encoding.hasDOG;
            this.locations = encoding.locations;
            return builder();
        }
        
        public Builder withadvertisingDuration(Integer advertisingDuration) {
            this.advertisingDuration = advertisingDuration;
            return builder();
        }

        public Builder withcontainsAdvertising(Boolean containsAdvertising) {
            this.containsAdvertising = containsAdvertising;
            return builder();
        }

        public Builder withbitRate(Integer bitRate) {
            this.bitRate = bitRate;
            return builder();
        }

        public Builder withaudioBitRate(Integer audioBitRate) {
            this.audioBitRate = audioBitRate;
            return builder();
        }

        public Builder withaudioChannels(Integer audioChannels) {
            this.audioChannels = audioChannels;
            return builder();
        }

        public Builder withaudioCoding(MimeType audioCoding) {
            this.audioCoding = audioCoding;
            return builder();
        }

        public Builder withvideoAspectRatio(String videoAspectRatio) {
            this.videoAspectRatio = videoAspectRatio;
            return builder();
        }

        public Builder withvideoBitRate(Integer videoBitRate) {
            this.videoBitRate = videoBitRate;
            return builder();
        }

        public Builder withvideoCoding(MimeType videoCoding) {
            this.videoCoding = videoCoding;
            return builder();
        }

        public Builder withvideoFrameRate(Float videoFrameRate) {
            this.videoFrameRate = videoFrameRate;
            return builder();
        }

        public Builder withvideoHorizontalSize(Integer videoHorizontalSize) {
            this.videoHorizontalSize = videoHorizontalSize;
            return builder();
        }

        public Builder withvideoProgressiveScan(Boolean videoProgressiveScan) {
            this.videoProgressiveScan = videoProgressiveScan;
            return builder();
        }

        public Builder withvideoVerticalSize(Integer videoVerticalSize) {
            this.videoVerticalSize = videoVerticalSize;
            return builder();
        }

        public Builder withdataContainerFormat(MimeType dataContainerFormat) {
            this.dataContainerFormat = dataContainerFormat;
            return builder();
        }

        public Builder withdataSize(Long dataSize) {
            this.dataSize = dataSize;
            return builder();
        }

        public Builder withsource(String source) {
            this.source = source;
            return builder();
        }

        public Builder withdistributor(String distributor) {
            this.distributor = distributor;
            return builder();
        }

        public Builder withhasDOG(Boolean hasDOG) {
            this.hasDOG = hasDOG;
            return builder();
        }

        public Builder withlocations(Set<Location> locations) {
            this.locations = locations;
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Encoding build() {
            return new Encoding(this);
        }
        
    }

	private final Integer advertisingDuration;
	private final Boolean containsAdvertising;

	private final Integer bitRate;

	private final Integer audioBitRate;
	private final Integer audioChannels;
	private final MimeType audioCoding;

	private final String videoAspectRatio;
	private final Integer videoBitRate;
	private final MimeType videoCoding;
	private final Float videoFrameRate;
	private final Integer videoHorizontalSize;
	private final Boolean videoProgressiveScan;
	private final Integer videoVerticalSize;


	private final MimeType dataContainerFormat;
	private final Long dataSize;

	private final String source;
	private final String distributor;

	private final Boolean hasDOG;

	private final Set<Location> locations;

	public Encoding(Builder builder) {
	    super(builder);
	    this.advertisingDuration = builder.advertisingDuration;
	    this.containsAdvertising = builder.containsAdvertising;
	    this.bitRate = builder.bitRate;
	    this.audioBitRate = builder.audioBitRate;
	    this.audioChannels = builder.audioChannels;
	    this.audioCoding = builder.audioCoding;
	    this.videoAspectRatio = builder.videoAspectRatio;
	    this.videoBitRate = builder.videoBitRate;
	    this.videoCoding = builder.videoCoding;
	    this.videoFrameRate = builder.videoFrameRate;
	    this.videoHorizontalSize = builder.videoHorizontalSize;
	    this.videoProgressiveScan = builder.videoProgressiveScan;
	    this.videoVerticalSize = builder.videoVerticalSize;
	    this.dataContainerFormat = builder.dataContainerFormat;
	    this.dataSize = builder.dataSize;
	    this.source = builder.source;
	    this.distributor = builder.distributor;
	    this.hasDOG = builder.hasDOG;
	    this.locations = builder.locations;
    }
	
	@Override
	public Builder copy() {
	    return new Builder().copy(this);
	}

    public Set<Location> locations() { 
        return this.locations; 
    }

   
    public String formatDataSize() {
        if(dataSize != null) {
            return ByteCount.bytes(dataSize).prettyPrint();
        } else {
            return null;
        }
    }
  
    public Integer getAdvertisingDuration() { 
        return this.advertisingDuration;
    }

    public Integer getAudioBitRate() { 
        return this.audioBitRate;
    }

    public Integer getAudioChannels() { 
        return this.audioChannels;
    }
        
    public MimeType getAudioCoding() { 
        return this.audioCoding; 
    }

    public Integer getBitRate() {
        return this.bitRate;
    }
    
    public Boolean getContainsAdvertising() { 
        return this.containsAdvertising;
    }
    
    public MimeType getDataContainerFormat() { 
        return this.dataContainerFormat; 
    }
    
    public Long getDataSize() { 
        return this.dataSize;
    }

    public String getDistributor() {
        return this.distributor;
    }

    public Boolean getHasDOG() { 
        return this.hasDOG;
    }

    public String getSource() {
        return this.source;
    }

    public String getVideoAspectRatio() { 
        return this.videoAspectRatio;
    }

    public Integer getVideoBitRate() { 
        return this.videoBitRate;
    }

    public MimeType getVideoCoding() { 
        return this.videoCoding; 
    }

    public Float getVideoFrameRate() { 
        return this.videoFrameRate;
    }

    public Integer getVideoHorizontalSize() { 
        return this.videoHorizontalSize;
    }

    public Boolean getVideoProgressiveScan() { 
        return this.videoProgressiveScan;
    }

    public Integer getVideoVerticalSize() { 
        return this.videoVerticalSize;
    }
    
    @Override
    public Identifier toIdentifier() {
        return new EncodingIdentifier(this);
    }
    
    public static final class EncodingIdentifier extends Identifier {

        public EncodingIdentifier(Encoding encoding) {
            super(encoding);
        }
    }
}
