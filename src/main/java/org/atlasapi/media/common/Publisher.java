package org.atlasapi.media.common;

import java.util.Map;

import org.atlasapi.application.SourceStatus;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.metabroadcast.common.intl.Countries;
import com.metabroadcast.common.intl.Country;

public enum Publisher {
    
    BBC("BBC", "bbc.co.uk", Countries.GB, SourceStatus.AVAILABLE_ENABLED),
    C4("Channel 4", "channel4.com", Countries.GB, SourceStatus.UNAVAILABLE),
    HULU("Hulu", "hulu.com", Countries.US, SourceStatus.AVAILABLE_ENABLED),
    YOUTUBE("YouTube", "youtube.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    TED("TED", "ted.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    VIMEO("VIMEO", "vimeo.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    ITV("ITV", "itv.com", Countries.GB, SourceStatus.UNAVAILABLE), 
    BLIP("blip.tv", "blip.tv", Countries.ALL, SourceStatus.AVAILABLE_ENABLED), 
    DAILYMOTION("Dailymotion", "dailymotion.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED), 
    FLICKR("Flickr", "flickr.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED), 
    FIVE("Five", "five.tv", Countries.GB, SourceStatus.UNAVAILABLE),
    SEESAW("SeeSaw", "seesaw.com", Countries.GB, SourceStatus.AVAILABLE_ENABLED),
    TVBLOB("TV Blob", "tvblob.com", Countries.IT, SourceStatus.AVAILABLE_ENABLED),
    ICTOMORROW("ICTomorrow", "ictomorrow.co.uk", Countries.GB, SourceStatus.AVAILABLE_ENABLED),
    HBO("HBO", "hbo.com", Countries.US, SourceStatus.AVAILABLE_ENABLED),
    ITUNES("iTunes", "itunes.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    MSN_VIDEO("MSN Video", "video.uk.msn.com", Countries.GB, SourceStatus.AVAILABLE_ENABLED),
    PA("PA", "pressassociation.com", Countries.GB, SourceStatus.UNAVAILABLE),
    RADIO_TIMES("Radio Times", "radiotimes.com", Countries.GB, SourceStatus.UNAVAILABLE),
    PREVIEW_NETWORKS("Preview Networks", "previewnetworks.com", Countries.GB, SourceStatus.UNAVAILABLE),
    ARCHIVE_ORG("Archive.org", "archive.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    WORLD_SERVICE("BBC World Service Archive", "wsarchive.bbc.co.uk", Countries.ALL, SourceStatus.UNAVAILABLE),
    BBC_REDUX("BBC Redux", "bbcredux.com", Countries.GB, SourceStatus.UNAVAILABLE),
    METABROADCAST("MetaBroadcast", "metabroadcast.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    DBPEDIA("DBpedia", "dbpedia.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    BBC_PRODUCTS("BBC Commercial Availability", "products.bbc.co.uk", Countries.GB, SourceStatus.UNAVAILABLE),
    LOVEFILM("LoveFilm", "lovefilm.com", Countries.GB, SourceStatus.UNAVAILABLE),
    MUSIC_BRAINZ("MusicBrainz", "musicbrainz.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    EMI_PUB("EMI Music Publishing", "emimusicpub.com", Countries.GB, SourceStatus.UNAVAILABLE),
    THESPACE("The Space", "thespace.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED),
    VOILA("Voila", "voila.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE),
    MAGPIE("Magpie", "magpie.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE);

    private final String key;
    private final Country country;
    private final String title;
    private final SourceStatus defaultStatus;

    Publisher(String title, String key, Country country, SourceStatus defaultStatus) {
        this.title = title;
        this.key = key;
        this.country = country;
        this.defaultStatus = defaultStatus;
    }

    public String title() {
        return title;
    }

    public String key() {
        return key;
    }

    public Country country() {
        return country;
    }

    public SourceStatus getDefaultSourceStatus() {
        return defaultStatus;
    }
    
    public static final ImmutableSet<Publisher> PUBLISHERS
        = ImmutableSet.copyOf(values());

    public static final Function<Publisher, String> TO_KEY
        = new Function<Publisher, String>() {
            @Override
            public String apply(Publisher from) {
                return from.key();
            }
        };

    private static final Map<String, Optional<Publisher>> KEY_MAP
        = OptionalMap.fromMap(Maps.uniqueIndex(PUBLISHERS, TO_KEY));

    public static final Optional<Publisher> fromKey(String key) {
        return KEY_MAP.get(key);
    }

    public static final Function<String, Optional<Publisher>> FROM_KEY
        = new Function<String, Optional<Publisher>>() {
            @Override
            public Optional<Publisher> apply(String key) {
                return fromKey(key);
            }
        };
    
}
