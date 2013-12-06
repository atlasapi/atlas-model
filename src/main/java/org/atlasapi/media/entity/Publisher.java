package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkArgument;

import org.atlasapi.application.v3.SourceRestriction;
import org.atlasapi.application.v3.SourceStatus;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.intl.Countries;
import com.metabroadcast.common.intl.Country;

public enum Publisher {
    BBC("BBC", "bbc.co.uk", Countries.GB, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    C4("Channel 4", "channel4.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    HULU("Hulu", "hulu.com", Countries.US, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    YOUTUBE("YouTube", "youtube.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    TED("TED", "ted.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    VIMEO("VIMEO", "vimeo.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    ITV("ITV", "itv.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY), 
    BLIP("blip.tv", "blip.tv", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY), 
    DAILYMOTION("Dailymotion", "dailymotion.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY), 
    FLICKR("Flickr", "flickr.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY), 
    FIVE("Five", "five.tv", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    SEESAW("SeeSaw", "seesaw.com", Countries.GB, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    TVBLOB("TV Blob", "tvblob.com", Countries.IT, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    ICTOMORROW("ICTomorrow", "ictomorrow.co.uk", Countries.GB, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    HBO("HBO", "hbo.com", Countries.US, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    ITUNES("iTunes", "itunes.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    MSN_VIDEO("MSN Video", "video.uk.msn.com", Countries.GB, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.ADMIN_ONLY),
    PA("PA", "pressassociation.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.NONE),
    PA_SERIES_SUMMARIES("PA Series Summaries", "summaries.pressassociation.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.NONE),
    RADIO_TIMES("Radio Times", "radiotimes.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    PREVIEW_NETWORKS("Preview Networks", "previewnetworks.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    ARCHIVE_ORG("Archive.org", "archive.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    WORLD_SERVICE("BBC World Service Archive", "wsarchive.bbc.co.uk", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BBC_REDUX("BBC Redux", "bbcredux.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    METABROADCAST("MetaBroadcast", "metabroadcast.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    DBPEDIA("DBpedia", "dbpedia.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    LOVEFILM("LoveFilm", "lovefilm.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BBC_PRODUCTS("BBC Commercial Availability", "products.bbc.co.uk", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    MUSIC_BRAINZ("MusicBrainz", "musicbrainz.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    EMI_PUB("EMI Music Publishing", "emimusicpub.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    EMI_MUSIC("EMI Music", "emimusic.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BBC_KIWI("BBC Kiwi", "kiwi.bbc.co.uk", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    CANARY("Canary", "canary.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    THESPACE("The Space", "thespace.org", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    VOILA("Voila", "voila.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    MAGPIE("Magpie", "magpie.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    LONDON_ALSO("London ALSO", "london.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BBC_MUSIC("BBC Music","music.bbc.co.uk", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    SPOTIFY("Spotify", "spotify.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    SOUNDCLOUD("Soundcloud", "soundcloud.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    RDIO("rdio", "rdio.com",Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    AMAZON_UK("Amazon UK", "amazon.co.uk", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BBC_RD_TOPIC("BBC R&D Live Topics", "live-topics.prototype0.net", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    PA_FEATURES("PA Features", "features.pressassociation.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    PA_PEOPLE("PA People Profiles", "people.pressassociation.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BT("BT", "bt.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BT_FEATURED_CONTENT("BT Featured Content", "featured.bt.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    NETFLIX("Netflix", "gb.netflix.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    YOUVIEW("YouView", "youview.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    FACEBOOK("Facebook Graph API", "graph.facebook.com", Countries.ALL, SourceStatus.AVAILABLE_ENABLED, SourceRestriction.NONE),
    SCRAPERWIKI("ScraperWiki", "scraperwiki.com", Countries.ALL, SourceStatus.AVAILABLE_DISABLED, SourceRestriction.ADMIN_ONLY),
    SVERIGES_RADIO("Sveriges Radio", "sverigesradio.se", Countries.ALL, SourceStatus.AVAILABLE_DISABLED, SourceRestriction.ADMIN_ONLY),
    TALK_TALK("Talk Talk", "talktalk.net", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    KANDL_TOPICS("kandl topics", "kandl.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    THE_SUN("The Sun", "thesun.co.uk", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    ADAPT_BBC_PODCASTS("Adapt BBC Podcasts", "bbc-podcasts.adapt.fm", Countries.GB, SourceStatus.AVAILABLE_DISABLED, SourceRestriction.ADMIN_ONLY),
    YURI("yuri", "yuri.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    COYOTE("coyote", "coyote.metabroadcast.com", Countries.ALL, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    BBC_NITRO("BBC Nitro", "nitro.bbc.co.uk", Countries.ALL, SourceStatus.AVAILABLE_DISABLED, SourceRestriction.ADMIN_ONLY),
    AMAZON_UNBOX("Amazon Unbox GB (Lovefilm)", "unbox.amazon.co.uk", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    METABROADCAST_PICKS("MetaBroadcast Picks", "picks.metabroadcast.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    C4_PMLSD("Channel 4 PMLSD", "pmlsd.channel4.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    WIKIPEDIA("Wikipedia", "wikipedia.org", Countries.ALL, SourceStatus.AVAILABLE_DISABLED, SourceRestriction.NONE),
    C5_TV_CLIPS("Channel 5 TV Clips", "c5tvclips.metabroadcast.com", Countries.GB, SourceStatus.UNAVAILABLE, SourceRestriction.ADMIN_ONLY),
    ;

    private static final Splitter CSV_SPLITTER = Splitter.on(',').trimResults();

    private final String key;
    private final Country country;
    private final String title;
    private final SourceStatus defaultStatus;
    private final SourceRestriction restriction;

    Publisher(String title, String key, Country country, SourceStatus defaultStatus, SourceRestriction restriction) {
        this.title = title;
        this.key = key;
        this.country = country;
        this.defaultStatus = defaultStatus;
        this.restriction = restriction;
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
    
    public SourceRestriction restriction() {
        return restriction;
    }

    public static Maybe<Publisher> fromKey(String key) {
        for (Publisher publisher : Publisher.values()) {
            if (key.equals(publisher.key())) {
                return Maybe.just(publisher);
            }
        }
        return Maybe.nothing();
    }

    @Override
    public String toString() {
    	return key();
    }

    public static Function<Publisher, String> TO_KEY = new Function<Publisher, String>() {
        @Override
        public String apply(Publisher from) {
            return from.key();
        }
    };

    public static Function<String, Publisher> FROM_KEY = new Function<String, Publisher>() {
        @Override
        public Publisher apply(String key) {
            Maybe<Publisher> found = fromKey(key);
            checkArgument(found.hasValue(), "Not a valid publisher key: " + key);
            return found.requireValue();
        }
    };

    public static ImmutableList<Publisher> fromCsv(String csv) {
        return ImmutableList.copyOf(Iterables.transform(CSV_SPLITTER.split(csv), FROM_KEY));
    }

    public SourceStatus getDefaultSourceStatus() {
        return defaultStatus;
    }
    
    private static final ImmutableSet<Publisher> ALL = ImmutableSet.copyOf(values());
    
    public static final ImmutableSet<Publisher> all() {
        return ALL;
    }
}
