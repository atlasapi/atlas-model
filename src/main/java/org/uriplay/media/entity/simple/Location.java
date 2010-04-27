package org.uriplay.media.entity.simple;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.entity.Encoding;
import org.uriplay.media.vocabulary.PLAY;

@XmlRootElement(namespace=PLAY.NS)
@XmlType(name="location", namespace=PLAY.NS)
public class Location {
	
	private Integer duration;
	private Integer publishedDuration;
	private String rating;
	private String ratingText;
	private Date transmissionTime;
	
    private Integer advertisingDuration;
    private Integer audioBitRate;
    private Integer audioChannels;
    private String audioCoding;
    private Integer bitRate;
    private Boolean containsAdvertising;
    private String dataContainerFormat;
    private Long dataSize;
    private String distributor;
    private Boolean hasDOG;
    private String source;
    private String videoAspectRatio;
    private Integer videoBitRate;
    private String videoCoding;
    private Float videoFrameRate;
    private Integer videoHorizontalSize;
    private Boolean videoProgressiveScan;
    private Integer videoVerticalSize;
    
	private Date availabilityStart;
    private Date drmPlayableFrom;
    private Encoding encoding;
    private String restrictedBy;
    private Boolean transportIsLive;
    private String transportSubType;
    private String transportType;
    private String uri;
    private String embedCode;
    
    private boolean available = true;
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDataContainerFormat() {
		return dataContainerFormat;
	}
	
	public void setDataContainerFormat(String dataContainerFormat) {
		this.dataContainerFormat = dataContainerFormat;
	}

	public String getRatingText() {
		return ratingText;
	}
	
	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getPublishedDuration() {
		return publishedDuration;
	}

	public void setPublishedDuration(Integer publishedDuration) {
		this.publishedDuration = publishedDuration;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getTransmissionTime() {
		return transmissionTime;
	}

	public void setTransmissionTime(Date transmissionTime) {
		this.transmissionTime = transmissionTime;
	}

	public Integer getAdvertisingDuration() {
		return advertisingDuration;
	}

	public void setAdvertisingDuration(Integer advertisingDuration) {
		this.advertisingDuration = advertisingDuration;
	}

	public Integer getAudioBitRate() {
		return audioBitRate;
	}

	public void setAudioBitRate(Integer audioBitRate) {
		this.audioBitRate = audioBitRate;
	}

	public Integer getAudioChannels() {
		return audioChannels;
	}

	public void setAudioChannels(Integer audioChannels) {
		this.audioChannels = audioChannels;
	}

	public String getAudioCoding() {
		return audioCoding;
	}

	public void setAudioCoding(String audioCoding) {
		this.audioCoding = audioCoding;
	}

	public Integer getBitRate() {
		return bitRate;
	}

	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}

	public Boolean getContainsAdvertising() {
		return containsAdvertising;
	}

	public void setContainsAdvertising(Boolean containsAdvertising) {
		this.containsAdvertising = containsAdvertising;
	}

	public Long getDataSize() {
		return dataSize;
	}

	public void setDataSize(Long dataSize) {
		this.dataSize = dataSize;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public Boolean getHasDOG() {
		return hasDOG;
	}

	public void setHasDOG(Boolean hasDOG) {
		this.hasDOG = hasDOG;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVideoAspectRatio() {
		return videoAspectRatio;
	}

	public void setVideoAspectRatio(String videoAspectRatio) {
		this.videoAspectRatio = videoAspectRatio;
	}

	public Integer getVideoBitRate() {
		return videoBitRate;
	}

	public void setVideoBitRate(Integer videoBitRate) {
		this.videoBitRate = videoBitRate;
	}

	public String getVideoCoding() {
		return videoCoding;
	}

	public void setVideoCoding(String videoCoding) {
		this.videoCoding = videoCoding;
	}

	public Float getVideoFrameRate() {
		return videoFrameRate;
	}

	public void setVideoFrameRate(Float videoFrameRate) {
		this.videoFrameRate = videoFrameRate;
	}

	public Integer getVideoHorizontalSize() {
		return videoHorizontalSize;
	}

	public void setVideoHorizontalSize(Integer videoHorizontalSize) {
		this.videoHorizontalSize = videoHorizontalSize;
	}

	public Boolean getVideoProgressiveScan() {
		return videoProgressiveScan;
	}

	public void setVideoProgressiveScan(Boolean videoProgressiveScan) {
		this.videoProgressiveScan = videoProgressiveScan;
	}

	public Integer getVideoVerticalSize() {
		return videoVerticalSize;
	}

	public void setVideoVerticalSize(Integer videoVerticalSize) {
		this.videoVerticalSize = videoVerticalSize;
	}

	public Date getAvailabilityStart() {
		return availabilityStart;
	}

	public void setAvailabilityStart(Date availabilityStart) {
		this.availabilityStart = availabilityStart;
	}

	public Date getDrmPlayableFrom() {
		return drmPlayableFrom;
	}

	public void setDrmPlayableFrom(Date drmPlayableFrom) {
		this.drmPlayableFrom = drmPlayableFrom;
	}

	public Encoding getEncoding() {
		return encoding;
	}

	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}

	public String getRestrictedBy() {
		return restrictedBy;
	}

	public void setRestrictedBy(String restrictedBy) {
		this.restrictedBy = restrictedBy;
	}

	public Boolean getTransportIsLive() {
		return transportIsLive;
	}

	public void setTransportIsLive(Boolean transportIsLive) {
		this.transportIsLive = transportIsLive;
	}

	public String getTransportSubType() {
		return transportSubType;
	}

	public void setTransportSubType(String transportSubType) {
		this.transportSubType = transportSubType;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
