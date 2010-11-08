package org.atlasapi.media.entity;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.model.SelfModelling;
import com.metabroadcast.common.model.SimpleModel;

public enum Channel implements SelfModelling {

    BBC_IPLAYER("iPlayer", "http://www.bbc.co.uk/iplayer", "iplayer"), 
    HULU("Hulu", "http://www.hulu.com", "hulu"), 
    YOUTUBE("YouTube", "http://www.youtube.com", "youtube"), 
    SEESAW("Seesaw", "http://www.seesaw.com", "seesaw"), 
    C4_4OD("4oD", "http://www.channel4.com", "4od"), 
    
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
    BBC_PARLIMENT("BBC Parliment", "http://www.bbc.co.uk/services/parliament", "bbcparliment");

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
