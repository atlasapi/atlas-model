package org.atlasapi.media.entity;

import java.util.List;
import java.util.Map;

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
    ITV4_HD("ITV4 HD", "http://www.itv.com/channels/itv4/hd", "itv4hd"),
    
    YTV("Y TV", "http://ref.atlasapi.org/channels/ytv", "ytv"),
    ULSTER("Ulster", "http://ref.atlasapi.org/channels/ulster", "ulster"),
    ULSTER_HD("Ulster HD", "http://ref.atlasapi.org/channels/ulsterhd", "ulsterhd"),
    STV_CENTRAL("STV Central", "http://ref.atlasapi.org/channels/stvcentral", "stvcentral"),
    STV_HD("SVT HD", "http://ref.atlasapi.org/channels/stvhd", "stvhd"),
    STV_NORTH("STV NORTH", "http://ref.atlasapi.org/channels/stvnorth", "stvnorth"),
    Channel_4_PLUS1("Channel 4 +1", "http://www.channel4.com/cchannel4plus1", "channel4plus1"),
    Channel_4_HD("Channel 4 HD", "http://ref.atlasapi.org/channels/channel4hd", "channel4hd"),
    S4C("S4C", "http://ref.atlasapi.org/channels/s4c", "s4c"),
    S4C_CLIRLUN("S4C Clirlun", "http://ref.atlasapi.org/channels/s4cclirlun", "s4cclirlun"),
    RTE1("RTE 1", "http://ref.atlasapi.org/channels/rte1", "rte1"),
    RTE2("RTE 2", "http://ref.atlasapi.org/channels/rte2", "rte2"),
    TG4("TG 4", "http://ref.atlasapi.org/channels/tg4", "tg4"),
    TV3("TV 3", "http://ref.atlasapi.org/channels/tv3", "tv3"),
    GMTV_DIGITAL("GMTV Digital", "http://ref.atlasapi.org/channels/gmtv", "gmtv"),
    ITV2_PLUS1("ITV2 +1", "http://www.itv.com/channels/itv2#plus1", "itv2plus1"),
    E4_PLUS1("E4 +1", "http://www.e4.com/plus1", "e4plus1"),
    E4_HD("E4 HD", "http://www.e4.com/hd", "e4hd"),
    ITV3_PLUS1("ITV3 +1", "http://www.itv.com/channels/itv3#plus1", "itv3plus1"),
    MORE4_PLUS1("More4 +1", "http://www.channel4.com/more4#plus1", "more4plus1"),
    ITV4_PLUS1("ITV1 +1", "http://www.itv.com/channels/itv1#plus1", "itv1plus1"),
    CITV("Children's ITV", "http://www.itv.com/channels/citv", "citv"),
    FIVER_PLUS1("Fiver +1", "http://www.five.tv/channels/fiver#plus1", "fiverplus1"),
    FIVE_USA_PLUS1("Fiver USA +1", "http://www.five.tv/channels/fiver-usa#plus1", "fiverusaplus1"),
/*    THE_ADULT_CHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    MTV_HITS("", "http://ref.atlasapi.org/channels/", ""),
    MTV_BASE("", "http://ref.atlasapi.org/channels/", ""),
    MTV("", "http://ref.atlasapi.org/channels/", ""),
    MTV_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    TRAVELCHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    TRAVELCHANNEL_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    TV5("", "http://ref.atlasapi.org/channels/", ""),
    ZEE_TV("", "http://ref.atlasapi.org/channels/", ""),
    PHOENIX_CNE("", "http://ref.atlasapi.org/channels/", ""),
    CHALLENGE("", "http://ref.atlasapi.org/channels/", ""),
    CHALLENGE_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    HOME("", "http://ref.atlasapi.org/channels/", ""),
    HOME_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SKY_BOX_OFFICE_DIGITAL("", "http://ref.atlasapi.org/channels/", ""),
    BLOOMBERG_TV("", "http://ref.atlasapi.org/channels/", ""),
    THE_BOX("", "http://ref.atlasapi.org/channels/", ""),
    CNN("", "http://ref.atlasapi.org/channels/", ""),
    CARTOON_NETWORK("", "http://ref.atlasapi.org/channels/", ""),
    GOD_CHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    PLAYBOY_TV("", "http://ref.atlasapi.org/channels/", ""),
    ANIMAL_PLANET("", "http://ref.atlasapi.org/channels/", ""),
    ANIMAL_PLANET_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SKY_NEWS("", "http://ref.atlasapi.org/channels/", ""),
    SKY_NEWS_HD("", "http://ref.atlasapi.org/channels/", ""),
    MTV_ROCKS("", "http://ref.atlasapi.org/channels/", ""),
    LIVING("", "http://ref.atlasapi.org/channels/", ""),
    LIVING_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    LIVING_HD("", "http://ref.atlasapi.org/channels/", ""),
    BRAVO("", "http://ref.atlasapi.org/channels/", ""),
    BRAVO_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_HISTORY("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_HISTORY_PLUS_1("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_SCIENCE("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_SCIENCE_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_TRAVEL_AND_LIVING("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_TRAVEL_AND_LIVING_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    HISTORY("", "http://ref.atlasapi.org/channels/", ""),
    HISTORY_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    NATIONAL_GEOGRAPHIC("", "http://ref.atlasapi.org/channels/", ""),
    NATIONAL_GEOGRAPHIC_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    GOLD("", "http://ref.atlasapi.org/channels/", ""),
    GOLD_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    THE_DISNEY_CHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    THE_DISNEY_CHANNEL_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    TELEVISION_X("", "http://ref.atlasapi.org/channels/", ""),
    VH1("", "http://ref.atlasapi.org/channels/", ""),
    MTV_CLASSIC("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_PLUS1_POINT5("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_TURBO("", "http://ref.atlasapi.org/channels/", ""),
    ALIBI("", "http://ref.atlasapi.org/channels/", ""),
    ALIBI_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    UNIVERSAL("", "http://ref.atlasapi.org/channels/", ""),
    UNIVERSAL_HD("", "http://ref.atlasapi.org/channels/", ""),
    UNIVERSAL_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SYFY("", "http://ref.atlasapi.org/channels/", ""),
    SYFY_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SYFY_HD("", "http://ref.atlasapi.org/channels/", ""),
    COMEDY_CENTRAL("", "http://ref.atlasapi.org/channels/", ""),
    COMEDY_CENTRAL_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SONY_ENTERTAINMENT_TV_ASIA("", "http://ref.atlasapi.org/channels/", ""),
    TCM("", "http://ref.atlasapi.org/channels/", ""),
    NICKELODEON("", "http://ref.atlasapi.org/channels/", ""),
    NICKELODEON_REPLAY("", "http://ref.atlasapi.org/channels/", ""),
    CNBC("", "http://ref.atlasapi.org/channels/", ""),
    NICK_JR("", "http://ref.atlasapi.org/channels/", ""),
    BOOMERANG("", "http://ref.atlasapi.org/channels/", ""),
    BOOMERANG_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    QVC("", "http://ref.atlasapi.org/channels/", ""),
    GBC("", "http://ref.atlasapi.org/channels/", ""),
    BBC_ENTERTAINMENT("", "http://ref.atlasapi.org/channels/", ""),
    FILM4_HD("", "http://ref.atlasapi.org/channels/", ""),
    FILM4_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SKY1("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_HOME_AND_HEALTH("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_HOME_AND_HEALTH_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    MUTV("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_NEWS("", "http://ref.atlasapi.org/channels/", ""),
    EUROSPORT("", "http://ref.atlasapi.org/channels/", ""),
    EUROSPORT_HD("", "http://ref.atlasapi.org/channels/", ""),
    KISS("", "http://ref.atlasapi.org/channels/", ""),
    PLAYHOUSE_DISNEY("", "http://ref.atlasapi.org/channels/", ""),
    PLAYHOUSE_DISNEY_PLUS("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_1("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_2("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_3("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_4("", "http://ref.atlasapi.org/channels/", ""),
    BIO("", "http://ref.atlasapi.org/channels/", ""),
    BID_TV("", "http://ref.atlasapi.org/channels/", ""),
    SKY_ARTS_1("", "http://ref.atlasapi.org/channels/", ""),
    EURONEWS("", "http://ref.atlasapi.org/channels/", ""),
    B4U_MOVIES("", "http://ref.atlasapi.org/channels/", ""),
    RED_HOT_AMATEUR("", "http://ref.atlasapi.org/channels/", ""),
    EXTREME_SPORTS("", "http://ref.atlasapi.org/channels/", ""),
    MTV_DANCE("", "http://ref.atlasapi.org/channels/", ""),
    STAR_PLUS("", "http://ref.atlasapi.org/channels/", ""),
    TELEG("", "http://ref.atlasapi.org/channels/", ""),
    CHANNEL_9("", "http://ref.atlasapi.org/channels/", ""),
    GOOD_FOOD("", "http://ref.atlasapi.org/channels/", ""),
    GOOD_FOOD_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    ATTHERACES("", "http://ref.atlasapi.org/channels/", ""),
    NICKTOONS_TV("", "http://ref.atlasapi.org/channels/", ""),
    NICKTOONS_REPLAY("", "http://ref.atlasapi.org/channels/", ""),
    CHART_SHOW_TV("", "http://ref.atlasapi.org/channels/", ""),
    YESTERDAY("", "http://ref.atlasapi.org/channels/", ""),
    YESTERDAY_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    MAGIC("", "http://ref.atlasapi.org/channels/", ""),
    SMASH_HITS("", "http://ref.atlasapi.org/channels/", ""),
    KERRANG("", "http://ref.atlasapi.org/channels/", ""),
    THE_COMMUNITY_CHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    SKY2("", "http://ref.atlasapi.org/channels/", ""),
    E_EXLAMATION("", "http://ref.atlasapi.org/channels/", ""),
    FLAUNT("", "http://ref.atlasapi.org/channels/", ""),
    SCUZZ("", "http://ref.atlasapi.org/channels/", ""),
    BLISS("", "http://ref.atlasapi.org/channels/", ""),
    ESPN_AMERICA("", "http://ref.atlasapi.org/channels/", ""),
    ESPN_AMERICA_HD("", "http://ref.atlasapi.org/channels/", ""),
    RED_HOT_40("", "http://ref.atlasapi.org/channels/", ""),
    DAVE("", "http://ref.atlasapi.org/channels/", ""),
    DAVE_JA_VU("", "http://ref.atlasapi.org/channels/", ""),
    PRICE_DROP_TV("", "http://ref.atlasapi.org/channels/", ""),
    FX("", "http://ref.atlasapi.org/channels/", ""),
    FX_PLUS("", "http://ref.atlasapi.org/channels/", ""),
    EDEN("", "http://ref.atlasapi.org/channels/", ""),
    EDEN_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    EDEN_HD("", "http://ref.atlasapi.org/channels/", ""),
    BLIGHTY("", "http://ref.atlasapi.org/channels/", ""),
    THE_HORROR_CHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    THE_HORROR_CHANNEL_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    RACING_UK("", "http://ref.atlasapi.org/channels/", ""),
    CHELSEA_TV("", "http://ref.atlasapi.org/channels/", ""),
    STAR_NEWS("", "http://ref.atlasapi.org/channels/", ""),
    SETANTA_IRELAND("", "http://ref.atlasapi.org/channels/", ""),
    LIVINGIT("", "http://ref.atlasapi.org/channels/", ""),
    LIVINGIT_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    EUROSPORT_2("", "http://ref.atlasapi.org/channels/", ""),
    REALLY("", "http://ref.atlasapi.org/channels/", ""),
    RED_HOT_TV("", "http://ref.atlasapi.org/channels/", ""),
    COMEDY_CENTRAL_EXTRA("", "http://ref.atlasapi.org/channels/", ""),
    COMEDY_CENTRAL_EXTRA_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    COMEDY_CENTRAL_HD("", "http://ref.atlasapi.org/channels/", ""),
    M95_TV_MARBELLA("", "http://ref.atlasapi.org/channels/", ""),
    TV3_SPANISH("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_REAL_TIME("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_REAL_TIME_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    MOTORS_TV("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_SHED("", "http://ref.atlasapi.org/channels/", ""),
    TRUE_MOVIES("", "http://ref.atlasapi.org/channels/", ""),
    SKY3("", "http://ref.atlasapi.org/channels/", ""),
    SKY_3_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DISNEY_CINEMAGIC("", "http://ref.atlasapi.org/channels/", ""),
    DISNEY_CINEMAGIC_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DISNEY_CINEMAGIC_HD("", "http://ref.atlasapi.org/channels/", ""),
    ESPN_CLASSIC("", "http://ref.atlasapi.org/channels/", ""),
    THREE_E("", "http://ref.atlasapi.org/channels/", ""),
    FILMFLEX("", "http://ref.atlasapi.org/channels/", ""),
    TCM2("", "http://ref.atlasapi.org/channels/", ""),
    CHRISTMAS24("", "http://ref.atlasapi.org/channels/", ""),
    CHRISTMAS24_PLUS("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_HD("", "http://ref.atlasapi.org/channels/", ""),
    NATIONAL_GEOGRAPHIC_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_BOX_OFFICE_HD1("", "http://ref.atlasapi.org/channels/", ""),
    SKY_BOX_OFFICE_HD2("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_1_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_2_HD("", "http://ref.atlasapi.org/channels/", ""),
    E_EUROPE("", "http://ref.atlasapi.org/channels/", ""),
    NAT_GEO_WILD("", "http://ref.atlasapi.org/channels/", ""),
    CRIME_AND_INVESTIGATION("", "http://ref.atlasapi.org/channels/", ""),
    CRIME_AND_INVESTIGATION_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    Channel("", "http://ref.atlasapi.org/channels/", ""),
    Channel_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_CRIME_AND_THRILLER("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_CRIME_AND_THRILLER_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_PREMIERE("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_PREMIERE_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_PREMIERE_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_COMEDY("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_COMEDY_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_ACTION_AND_ADVENTURE("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_ACTION_AND_ADVENTURE_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_FAMILY("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_FAMILY_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_DRAMA_AND_ROMANCE("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_DRAMA_AND_ROMANCE_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_SCIFI_HORROR("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_SCIFI_HORROR_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_CLASSICS("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_CLASSICS_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_MODERN_GREATS("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_MODERN_GREATS_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_INDIE("", "http://ref.atlasapi.org/channels/", ""),
    SKY_MOVIES_INDIE_HD("", "http://ref.atlasapi.org/channels/", ""),
    AL_JAZEERA_ENGLISH("", "http://ref.atlasapi.org/channels/", ""),
    HISTORY_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY1_HD("", "http://ref.atlasapi.org/channels/", ""),
    SKY_ARTS_1_HD("", "http://ref.atlasapi.org/channels/", ""),
    CARTOONITO("", "http://ref.atlasapi.org/channels/", ""),
    BEST_DIRECT("", "http://ref.atlasapi.org/channels/", ""),
    BRAVO_2("", "http://ref.atlasapi.org/channels/", ""),
    DEUTSCHE_WELLE("", "http://ref.atlasapi.org/channels/", ""),
    GEMS_TV("", "http://ref.atlasapi.org/channels/", ""),
    GEM_COLLECTOR("", "http://ref.atlasapi.org/channels/", ""),
    Channel_S("", "http://ref.atlasapi.org/channels/", ""),
    SETANTA_SPORTS_1_IRELAND("", "http://ref.atlasapi.org/channels/", ""),
    DIVA("", "http://ref.atlasapi.org/channels/", ""),
    DIVA_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    Channel_ONE("", "http://ref.atlasapi.org/channels/", ""),
    Channel_ONE_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    CN_TOO("", "http://ref.atlasapi.org/channels/", ""),
    POP("", "http://ref.atlasapi.org/channels/", ""),
    TINY_POP("", "http://ref.atlasapi.org/channels/", ""),
    DMAX("", "http://ref.atlasapi.org/channels/", ""),
    DMAX_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DMAX_2("", "http://ref.atlasapi.org/channels/", ""),
    HORSE_AND_COUNTRY("", "http://ref.atlasapi.org/channels/", ""),
    Channel_7("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_3_HD("", "http://ref.atlasapi.org/channels/", ""),
    FLAVA("", "http://ref.atlasapi.org/channels/", ""),
    FX_HD("", "http://ref.atlasapi.org/channels/", ""),
    NATIONAL_GEOGRAPHIC_HD_PAN_EUROPEAN("", "http://ref.atlasapi.org/channels/", ""),
    MOVIES4MEN("", "http://ref.atlasapi.org/channels/", ""),
    MOVIES4MEN_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    TRUE_MOVIES_2("", "http://ref.atlasapi.org/channels/", ""),
    MOVIES4MEN2("", "http://ref.atlasapi.org/channels/", ""),
    MOVIES4MEN2_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    MILITARY_HISTORY("", "http://ref.atlasapi.org/channels/", ""),
    THE_STYLE_NETWORK("", "http://ref.atlasapi.org/channels/", ""),
    WATCH("", "http://ref.atlasapi.org/channels/", ""),
    WATCH_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    SKY_ARTS_2("", "http://ref.atlasapi.org/channels/", ""),
    WEDDING_TV("", "http://ref.atlasapi.org/channels/", ""),
    PROPELLER_TV("", "http://ref.atlasapi.org/channels/", ""),
    MTVN_HD("", "http://ref.atlasapi.org/channels/", ""),
    INVESTIGATION_DISCOVERY("", "http://ref.atlasapi.org/channels/", ""),
    AIT_INTERNATIONAL("", "http://ref.atlasapi.org/channels/", ""),
    CINEMOI("", "http://ref.atlasapi.org/channels/", ""),
    SKY_ARTS_2_HD("", "http://ref.atlasapi.org/channels/", ""),
    BIO_HD("", "http://ref.atlasapi.org/channels/", ""),
    CRIME_AND_INVESTIGATION_HD("", "http://ref.atlasapi.org/channels/", ""),
    NAT_GEO_WILD_HD("", "http://ref.atlasapi.org/channels/", ""),
    QUEST("", "http://ref.atlasapi.org/channels/", ""),
    DISCOVERY_QUEST_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    QUEST_FREEVIEW("", "http://ref.atlasapi.org/channels/", ""),
    ESPN("", "http://ref.atlasapi.org/channels/", ""),
    ESPN_HD("", "http://ref.atlasapi.org/channels/", ""),
    DISNEY_XD("", "http://ref.atlasapi.org/channels/", ""),
    DISNEY_XD_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    DISNEY_XD_HD("", "http://ref.atlasapi.org/channels/", ""),
    CBS_REALITY("", "http://ref.atlasapi.org/channels/", ""),
    MGM("", "http://ref.atlasapi.org/channels/", ""),
    CBS_DRAMA("", "http://ref.atlasapi.org/channels/", ""),
    CBS_ACTION("", "http://ref.atlasapi.org/channels/", ""),
    VIVA("", "http://ref.atlasapi.org/channels/", ""),
    FOOD_NETWORK("", "http://ref.atlasapi.org/channels/", ""),
    FOOD_NETWORK_PLUS1("", "http://ref.atlasapi.org/channels/", ""),
    MGM_HD("", "http://ref.atlasapi.org/channels/", ""),
    MTV_SHOWS("", "http://ref.atlasapi.org/channels/", ""),
    NICK_JR_2("", "http://ref.atlasapi.org/channels/", ""),
    NHK_WORLD("", "http://ref.atlasapi.org/channels/", ""),
    TRUEENT("", "http://ref.atlasapi.org/channels/", ""),
    BODY_IN_BALANCE("", "http://ref.atlasapi.org/channels/", ""),
    THE_ACTIVE_CHANNEL("", "http://ref.atlasapi.org/channels/", ""),
    FITNESS_TV("", "http://ref.atlasapi.org/channels/", ""),
    RUSH_HD("", "http://ref.atlasapi.org/channels/", ""),
    BBC_SPORT_INTERACTIVE_BBC_ONE("", "http://ref.atlasapi.org/channels/", ""),
    BBC_SPORT_INTERACTIVE_FREEVIEW("", "http://ref.atlasapi.org/channels/", ""),
    SKY_3D("", "http://ref.atlasapi.org/channels/", ""),
    SKY_SPORTS_4_HD("", "http://ref.atlasapi.org/channels/", "")*/;
    
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
