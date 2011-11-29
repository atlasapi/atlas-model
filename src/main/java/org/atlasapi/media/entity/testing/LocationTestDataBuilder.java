package org.atlasapi.media.entity.testing;

import java.util.Date;
import java.util.Set;

import org.atlasapi.media.TransportSubType;
import org.atlasapi.media.TransportType;
import org.atlasapi.media.entity.Policy.RevenueContract;
import org.atlasapi.media.entity.simple.Location;
import org.atlasapi.media.entity.simple.Restriction;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.metabroadcast.common.intl.Countries;
import com.metabroadcast.common.media.MimeType;

public class LocationTestDataBuilder {
    private static Integer uniqueId = 1;

    private Integer duration;
    private Integer publishedDuration;
    private Restriction restriction;
    
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
    private Date availabilityEnd;
    private Date drmPlayableFrom;
    private Set<String> availableCountries = Sets.newHashSet();
    private String currency;
    private Integer price;
    private String revenueContract;
    
    private String restrictedBy;
    
    private Boolean transportIsLive;
    private String transportSubType;
    private String transportType;
    private String uri;
    private String embedCode;
    
    private boolean available = true;
    
    public static LocationTestDataBuilder location() {
        return new LocationTestDataBuilder();
    }
    
    private LocationTestDataBuilder() {
        resetAttributes();
    }
    
    private void resetAttributes() {
        duration = 28;
        publishedDuration = 30;
        restriction = new Restriction();
        restriction.setMessage("restriction message");
        restriction.setMinimumAge(18);
        restriction.setRestricted(true);
        advertisingDuration = 2;
        audioBitRate = 128;
        audioChannels = 2;
        audioCoding = MimeType.AUDIO_MPEG.name();
        bitRate = 1000;
        containsAdvertising = true;
        dataContainerFormat = MimeType.VIDEO_MP4.name();
        dataSize = 10000000l;
        distributor = "distributor";
        hasDOG = true;
        source = "source";
        videoAspectRatio = "4:3";
        videoBitRate = 878;
        videoCoding = MimeType.VIDEO_H264.name();
        videoFrameRate = 25.0f;
        videoHorizontalSize = 128;
        videoProgressiveScan = true;
        videoVerticalSize = 72;
        
        availabilityStart = new Date();
        availabilityEnd = new Date();
        drmPlayableFrom = new Date();
        availableCountries = ImmutableSet.of(Countries.GB.code());
        currency = "GBP";
        price = 199;
        revenueContract = RevenueContract.PAY_TO_BUY.key();
        
        restrictedBy = "restrictedBy";
        
        transportIsLive = true;
        transportSubType = TransportSubType.HTTP.name();
        transportType = TransportType.STREAM.name();
        uri = "http://metabroadcast.com/" + uniqueId++;
        embedCode = "<stream uri='http://this.com'>";
        available = true;
    }
    
    public Location build() {
        return buildItemAttributes(new Location());
    }
    
    private Location buildItemAttributes(Location location) {
        location.setDuration(duration);
        location.setPublishedDuration(publishedDuration);
        location.setRestriction(restriction);
        
        location.setAdvertisingDuration(advertisingDuration);
        location.setAudioBitRate(audioBitRate);
        location.setAudioChannels(audioChannels);
        location.setAudioCoding(audioCoding);
        location.setBitRate(bitRate);
        location.setContainsAdvertising(containsAdvertising);
        location.setDataContainerFormat(dataContainerFormat);
        location.setDataSize(dataSize);
        location.setDistributor(distributor);
        location.setHasDOG(hasDOG);
        location.setSource(source);
        location.setVideoAspectRatio(videoAspectRatio);
        location.setVideoBitRate(videoBitRate);
        location.setVideoCoding(videoCoding);
        location.setVideoFrameRate(videoFrameRate);
        location.setVideoHorizontalSize(videoHorizontalSize);
        location.setVideoProgressiveScan(videoProgressiveScan);
        location.setVideoVerticalSize(videoVerticalSize);
        location.setAvailabilityStart(availabilityStart);
        location.setAvailabilityEnd(availabilityEnd);
        location.setDrmPlayableFrom(drmPlayableFrom);
        location.setAvailableCountries(availableCountries);
        location.setCurrency(currency);
        location.setPrice(price);
        location.setRevenueContract(revenueContract);
        location.setRestrictedBy(restrictedBy);
        location.setTransportIsLive(transportIsLive);
        location.setTransportSubType(transportSubType);
        location.setTransportType(transportType);
        location.setUri(uri);
        location.setEmbedCode(embedCode);
        
        return location;
    }
    
}
