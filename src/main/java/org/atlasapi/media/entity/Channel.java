package org.atlasapi.media.entity;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableList.Builder;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.model.SelfModelling;
import com.metabroadcast.common.model.SimpleModel;

public enum Channel implements SelfModelling {

    BBC_IPLAYER("iPlayer", "http://www.bbc.co.uk/iplayer", "iplayer"), 
    HULU("Hulu", "http://www.hulu.com", "hulu"), 
    YOUTUBE("YouTube", "http://www.youtube.com", "youtube"), 
    SEESAW("Seesaw", "http://www.seesaw.com", "seesaw"), 
    C4_4OD("4oD", "http://www.channel4.com/programmes/4od", "4od"), 
    
    BBC_ONE("BBC One", "http://www.bbc.co.uk/services/bbcone/london", "bbcone"),
    BBC_ONE_LONDON("BBC One London", "http://www.bbc.co.uk/services/bbcone/london", "bbcone-london"), 
    BBC_ONE_NORTHERN_IRELAND("BBC One Northern Ireland", "http://www.bbc.co.uk/services/bbcone/ni", "bbcone-ni"), 
    BBC_ONE_CAMBRIDGE("BBC One Cambridgeshire", "http://www.bbc.co.uk/services/bbcone/cambridge", "bbcone-cambridge"),
    BBC_ONE_CHANNEL_ISLANDS("BBC One Channel Islands", "http://www.bbc.co.uk/services/bbcone/channel_islands", "bbcone-channel_islands"),
    BBC_ONE_EAST("BBC One East", "http://www.bbc.co.uk/services/bbcone/east", "bbcone-east"),
    BBC_ONE_EAST_MIDLANDS("BBC One East Midlands", "http://www.bbc.co.uk/services/bbcone/east_midlands", "bbcone-east_midlands"),
    BBC_ONE_HD("BBC One HD", "http://www.bbc.co.uk/services/bbcone/hd", "bbcone-hd"),
    BBC_ONE_NORTH_EAST("BBC One North East & Cumbria", "http://www.bbc.co.uk/services/bbcone/north_east", "bbcone-north_east"),
    BBC_ONE_NORTH_WEST("BBC One North West", "http://www.bbc.co.uk/services/bbcone/north_west", "bbcone-north_west"),
    BBC_ONE_OXFORD("BBC One Oxfordshire", "http://www.bbc.co.uk/services/bbcone/oxford", "bbcone-oxford"),
    BBC_ONE_SCOTLAND("BBC One Scotland", "http://www.bbc.co.uk/services/bbcone/scotland", "bbcone-scotland"),
    BBC_ONE_SOUTH("BBC One South", "http://www.bbc.co.uk/services/bbcone/south", "bbcone-south"),
    BBC_ONE_SOUTH_EAST("BBC One South East", "http://www.bbc.co.uk/services/bbcone/south_east", "bbcone-south_east"),
    BBC_ONE_WALES("BBC One Wales", "http://www.bbc.co.uk/services/bbcone/wales", "bbcone-wales"),
    BBC_ONE_SOUTH_WEST("BBC One South West", "http://www.bbc.co.uk/services/bbcone/south_west", "bbcone-south_west"),
    BBC_ONE_WEST("BBC One West", "http://www.bbc.co.uk/services/bbcone/west", "bbcone-west"),
    BBC_ONE_WEST_MIDLANDS("BBC One West Midlands", "http://www.bbc.co.uk/services/bbcone/west_midlands", "bbcone-west_midlands"),
    BBC_ONE_EAST_YORKSHIRE("BBC One Yorks & Lincs", "http://www.bbc.co.uk/services/bbcone/east_yorkshire", "bbcone-east_yorkshire"),
    BBC_ONE_YORKSHIRE("BBC One Yorkshire", "http://www.bbc.co.uk/services/bbcone/oxford", "bbcone-yorkshire"),
    
    BBC_TWO("BBC Two", "http://www.bbc.co.uk/services/bbctwo/england", "bbctwo"),
    BBC_TWO_ENGLAND("BBC Two England", "http://www.bbc.co.uk/services/bbctwo/england", "bbctwo-england"),
    BBC_TWO_NORTHERN_IRELAND("BBC Two Northern Ireland", "http://www.bbc.co.uk/services/bbctwo/ni", "bbctwo-ni"),
    BBC_TWO_NORTHERN_IRELAND_ALALOGUE("BBC Two Northern Ireland (Analogue)", "http://www.bbc.co.uk/services/bbctwo/ni_analogue", "bbctwo-ni_analogue"),
    BBC_TWO_SCOTLAND("BBC Two Scotland", "http://www.bbc.co.uk/services/bbctwo/scotland", "bbctwo-scotland"),
    BBC_TWO_WALES("BBC Two Wales", "http://www.bbc.co.uk/services/bbctwo/wales", "bbctwo-wales"),
    BBC_TWO_WALES_ANALOGUE("BBC Two Wales (Analogue)", "http://www.bbc.co.uk/services/bbctwo/wales_analogue", "bbctwo-wales"),
    
    BBC_THREE("BBC Three", "http://www.bbc.co.uk/services/bbcthree", "bbcthree"),
    BBC_FOUR("BBC Four", "http://www.bbc.co.uk/services/bbcfour", "bbcfour"),
    BBC_NEWS("BBC News", "http://www.bbc.co.uk/services/bbcnews", "bbcnews"), 
    BBC_WORLD_NEWS("BBC World News", "http://www.bbc.co.uk/services/bbcworldnews", "bbcworldnews"), 
    BBC_PARLIMENT("BBC Parliment", "http://www.bbc.co.uk/services/parliament", "bbcparliment"),
    BBC_HD("BBC HD", "http://www.bbc.co.uk/services/bbchd", "bbchd"),
    
    CHANNEL_FOUR("Channel 4", "http://www.channel4.com", "channel4"),
    MORE_FOUR("More 4", "http://www.channel4.com/more4", "more4"),
    FILM_4("Film 4", "http://film4.com", "film4"),
    E_FOUR("E4", "http://www.e4.com", "e4"),
    FOUR_MUSIC("4 Music", "http://www.4music.com", "4music"),
    
    FIVE("Five", "http://www.five.tv", "five"),
    FIVER("Fiver", "http://www.five.tv/channels/fiver", "fiver"),
    FIVE_USA("Five USA", "http://www.five.tv/channels/five-usa", "fiveusa"),
    
    ITV1_LONDON("ITV1 London", "http://www.itv.com/channels/itv1/london", "itv1london"),
    ITV1_GRANADA("ITV1 Granada", "http://www.itv.com/channels/itv1/granada", "itv1granada"),
    ITV1_TYNE_TEES("ITV1 Tyne Tees", "http://www.itv.com/channels/itv1/tynetees", "itv1tynetees"),
    ITV1_BORDER_SOUTH("ITV1 Border South", "http://www.itv.com/channels/itv1/bordersouth", "itv1bordersouth"),
    ITV1_MERIDIAN("ITV1 Meridian", "http://www.itv.com/channels/itv1/meridian", "itv1meridian"),
    ITV1_ANGLIA("ITV1 Anglia", "http://www.itv.com/channels/itv1/anglia", "itv1anglia"),
    ITV1_CHANNEL("ITV1 Channel", "http://www.itv.com/channels/itv1/channel", "itv1channel"),
    ITV1_WALES("ITV1 Wales", "http://www.itv.com/channels/itv1/wales", "itv1wales"),
    ITV1_WEST("ITV1 West", "http://www.itv.com/channels/itv1/west", "itv1west"),
    ITV1_CARLTON_CENTRAL("ITV1 Carlton-Central", "http://www.itv.com/channels/itv1/carltoncentral", "itv1carltoncentral"),
    ITV1_CARLTON_WESTCOUNTRY("ITV1 Carlton-Westcountry", "http://www.itv.com/channels/itv1/carltonwestcountry", "itv1carltonwestcountry"),
    ITV1_BORDER_NORTH("ITV1 Border North", "http://www.itv.com/channels/itv1/bordernorth", "itv1bordernorth"),
    ITV1_THAMES_VALLEY_NORTH("ITV1 Thames Valley North", "http://www.itv.com/channels/itv1/thamesvalleynorth", "itv1thamesvalleynorth"),
    ITV1_THAMES_VALLEY_SOUTH("ITV1 Thames Valley South", "http://www.itv.com/channels/itv1/thamesvalleysouth", "itv1thamesvalleysouth"),
    ITV1_HD("ITV1 HD", "http://www.itv.com/channels/itv1/hd", "itv1hd"),
    
    ITV2("ITV2", "http://www.itv.com/channels/itv2", "itv2"),
    ITV2_HD("ITV2 HD", "http://www.itv.com/channels/itv2/hd", "itv2hd"),
    ITV3("ITV3", "http://www.itv.com/channels/itv3", "itv3"),
    ITV3_HD("ITV3 HD", "http://www.itv.com/channels/itv3/hd", "itv3hd"),
    ITV4("ITV4", "http://www.itv.com/channels/itv4", "itv4"),
    ITV4_HD("ITV4 HD", "http://www.itv.com/channels/itv4/hd", "itv4hd");
    
    private final String uri;
    private final String title;

    private final static List<Channel> VOD_SERVICES = ImmutableList.of(BBC_IPLAYER, HULU, C4_4OD, YOUTUBE, SEESAW);
    private final String key;

    Channel(String title, String uri, String key) {
        this.title = title;
        this.uri = uri;
        this.key = key;
    }

    public String uri() {
        return uri;
    }

    public String title() {
        return title;
    }
    
    public String key() {
        return key;
    }

    public static Maybe<Channel> fromUri(String uri) {
        for (Channel channel : values()) {
            if (channel.uri.equals(uri)) {
                return Maybe.just(channel);
            }
        }
        return Maybe.nothing();
    }
    
    public static Maybe<Channel> fromKey(String key) {
        for (Channel channel : values()) {
            if (channel.key.equals(key)) {
                return Maybe.just(channel);
            }
        }
        return Maybe.nothing();
    }
    
    public static List<Channel> fromKeys(Iterable<String> keys) {
        Builder<Channel> builder = ImmutableList.builder();
        
        for (String key: keys) {
            Maybe<Channel> channel = fromKey(key);
            if (channel.hasValue()) {
                builder.add(channel.requireValue());
            }
        }
        
        return builder.build();
    }

    public static List<Map<String, ?>> mapList() {
        List<Map<String, ?>> channelList = Lists.newArrayList();
        for (Channel channel : values()) {
            channelList.add(channel.toSimpleModel().asMap());
        }
        return channelList;
    }

    public static List<Map<String, ?>> mapListWithoutVodServices() {
        List<Map<String, ?>> channelList = Lists.newArrayList();
        for (Channel channel : values()) {
            if (!VOD_SERVICES.contains(channel)) {
                channelList.add(channel.toSimpleModel().asMap());
            }
        }
        return channelList;
    }

    @Override
    public SimpleModel toSimpleModel() {
        SimpleModel model = new SimpleModel();
        model.put("name", title);
        model.put("uri", uri);
        
        return model;
    }
}
