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
    THE_ADULT_CHANNEL("The Adult Channel", "http://ref.atlasapi.org/channels/theadultchannel", "theadultchannel"),
    MTV_HITS("MTV Hits", "http://ref.atlasapi.org/channels/mtvhits", "mtvhits"),
    MTV_BASE("MTV Base", "http://ref.atlasapi.org/channels/mtvbase", "mtvbase"),
    MTV("MTV", "http://ref.atlasapi.org/channels/mtv", "mtv"),
    MTV_PLUS1("MTV +1", "http://ref.atlasapi.org/channels/mtvplus1", "mtvplus1"),
    TRAVELCHANNEL("TRAVELCHANNEL", "http://ref.atlasapi.org/channels/travelchannel", "travelchannel"),
    TRAVELCHANNEL_PLUS1("Travelchannel +1", "http://ref.atlasapi.org/channels/travelchannelplus1", "travelchannelplus1"),
    TV5("TV5", "http://ref.atlasapi.org/channels/tv5", "tv5"),
    ZEE_TV("Zee TV", "http://ref.atlasapi.org/channels/zeetv", "zeetv"),
    PHOENIX_CNE("Phoenix Cne", "http://ref.atlasapi.org/channels/phoenixcne", "phoenixcne"),
    CHALLENGE("Challenge", "http://ref.atlasapi.org/channels/challenge", "challenge"),
    CHALLENGE_PLUS1("Challenge +1", "http://ref.atlasapi.org/channels/challengeplus1", "challengeplus1"),
    HOME("HOME", "http://ref.atlasapi.org/channels/home", "home"),
    HOME_PLUS1("Home +1", "http://ref.atlasapi.org/channels/homeplus1", "homeplus1"),
    SKY_BOX_OFFICE_DIGITAL("Sky Box Office Digital", "http://ref.atlasapi.org/channels/skyboxofficedigital", "skyboxofficedigital"),
    BLOOMBERG_TV("Bloomberg TV", "http://ref.atlasapi.org/channels/bloombergtv", "bloombergtv"),
    THE_BOX("The Box", "http://ref.atlasapi.org/channels/thebox", "thebox"),
    CNN("CNN", "http://ref.atlasapi.org/channels/cnn", "cnn"),
    CARTOON_NETWORK("Cartoon Network", "http://ref.atlasapi.org/channels/cartoonnetwork", "cartoonnetwork"),
    GOD_CHANNEL("God Channel", "http://ref.atlasapi.org/channels/godchannel", "godchannel"),
    PLAYBOY_TV("Playboy TV", "http://ref.atlasapi.org/channels/playboytv", "playboytv"),
    ANIMAL_PLANET("Animal Planet", "http://ref.atlasapi.org/channels/animalplanet", "animalplanet"),
    ANIMAL_PLANET_PLUS1("Animal Planet +1", "http://ref.atlasapi.org/channels/animalplanetplus1", "animalplanetplus1"),
    SKY_NEWS("Sky News", "http://ref.atlasapi.org/channels/skynews", "skynews"),
    SKY_NEWS_HD("Sky News HD", "http://ref.atlasapi.org/channels/skynewshd", "skynewshd"),
    MTV_ROCKS("MTV Rocks", "http://ref.atlasapi.org/channels/mtvrocks", "mtvrocks"),
    LIVING("LIVING", "http://ref.atlasapi.org/channels/living", "living"),
    LIVING_PLUS1("Living +1", "http://ref.atlasapi.org/channels/livingplus1", "livingplus1"),
    LIVING_HD("Living HD", "http://ref.atlasapi.org/channels/livinghd", "livinghd"),
    BRAVO("Bravo", "http://ref.atlasapi.org/channels/bravo", "bravo"),
    BRAVO_PLUS1("Bravo +1", "http://ref.atlasapi.org/channels/bravoplus1", "bravoplus1"),
    DISCOVERY_HISTORY("Discovery History", "http://ref.atlasapi.org/channels/discoveryhistory", "discoveryhistory"),
    DISCOVERY_HISTORY_PLUS_1("Discovery History +1", "http://ref.atlasapi.org/channels/discoveryhistoryplus1", "discoveryhistoryplus1"),
    DISCOVERY_SCIENCE("Discovery Science", "http://ref.atlasapi.org/channels/discoveryscience", "discoveryscience"),
    DISCOVERY_SCIENCE_PLUS1("Discovery Science +1", "http://ref.atlasapi.org/channels/discoveryscienceplus1", "discoveryscienceplus1"),
    DISCOVERY_TRAVEL_AND_LIVING("Discovery Travel And Living", "http://ref.atlasapi.org/channels/discoverytravelandliving", "discoverytravelandliving"),
    DISCOVERY_TRAVEL_AND_LIVING_PLUS1("Discovery Travel And Living +1", "http://ref.atlasapi.org/channels/discoverytravelandlivingplus1", "discoverytravelandlivingplus1"),
    HISTORY("History", "http://ref.atlasapi.org/channels/history", "history"),
    HISTORY_PLUS1("History +1", "http://ref.atlasapi.org/channels/historyplus1", "historyplus1"),
    NATIONAL_GEOGRAPHIC("National Geographic", "http://ref.atlasapi.org/channels/nationalgeographic", "nationalgeographic"),
    NATIONAL_GEOGRAPHIC_PLUS1("National Geographic +1", "http://ref.atlasapi.org/channels/nationalgeographicplus1", "nationalgeographicplus1"),
    GOLD("Gold", "http://ref.atlasapi.org/channels/gold", "gold"),
    GOLD_PLUS1("Gold +1", "http://ref.atlasapi.org/channels/goldplus1", "goldplus1"),
    THE_DISNEY_CHANNEL("The Disney Channel", "http://ref.atlasapi.org/channels/thedisneychannel", "thedisneychannel"),
    THE_DISNEY_CHANNEL_PLUS1("The Disney Channel +1", "http://ref.atlasapi.org/channels/thedisneychannelplus1", "thedisneychannelplus1"),
    TELEVISION_X("Television X", "http://ref.atlasapi.org/channels/televisionx", "televisionx"),
    VH1("VH1", "http://ref.atlasapi.org/channels/vh1", "vh1"),
    MTV_CLASSIC("MTV Classic", "http://ref.atlasapi.org/channels/mtvclassic", "mtvclassic"),
    DISCOVERY("DISCOVERY", "http://ref.atlasapi.org/channels/discovery", "discovery"),
    DISCOVERY_PLUS1("Discovery +1", "http://ref.atlasapi.org/channels/discoveryplus1", "discoveryplus1"),
    DISCOVERY_PLUS1_POINT5("Discovery +1 Point5", "http://ref.atlasapi.org/channels/discoveryplus1point5", "discoveryplus1point5"),
    DISCOVERY_TURBO("Discovery Turbo", "http://ref.atlasapi.org/channels/discoveryturbo", "discoveryturbo"),
    ALIBI("Alibi", "http://ref.atlasapi.org/channels/alibi", "alibi"),
    ALIBI_PLUS1("Alibi +1", "http://ref.atlasapi.org/channels/alibiplus1", "alibiplus1"),
    UNIVERSAL("Universal", "http://ref.atlasapi.org/channels/universal", "universal"),
    UNIVERSAL_HD("Universal HD", "http://ref.atlasapi.org/channels/universalhd", "universalhd"),
    UNIVERSAL_PLUS1("Universal +1", "http://ref.atlasapi.org/channels/universalplus1", "universalplus1"),
    SYFY("SyFy", "http://ref.atlasapi.org/channels/syfy", "syfy"),
    SYFY_PLUS1("SyFy +1", "http://ref.atlasapi.org/channels/syfyplus1", "syfyplus1"),
    SYFY_HD("SyFy HD", "http://ref.atlasapi.org/channels/syfyhd", "syfyhd"),
    COMEDY_CENTRAL("Comedy Central", "http://ref.atlasapi.org/channels/comedycentral", "comedycentral"),
    COMEDY_CENTRAL_PLUS1("Comedy Central +1", "http://ref.atlasapi.org/channels/comedycentralplus1", "comedycentralplus1"),
    SONY_ENTERTAINMENT_TV_ASIA("Sony Entertainment TV Asia", "http://ref.atlasapi.org/channels/sonyentertainmenttvasia", "sonyentertainmenttvasia"),
    TCM("TCM", "http://ref.atlasapi.org/channels/tcm", "tcm"),
    NICKELODEON("Nickelodeon", "http://ref.atlasapi.org/channels/nickelodeon", "nickelodeon"),
    NICKELODEON_REPLAY("Nickelodeon Replay", "http://ref.atlasapi.org/channels/nickelodeonreplay", "nickelodeonreplay"),
    CNBC("CNBC", "http://ref.atlasapi.org/channels/cnbc", "cnbc"),
    NICK_JR("Nick Jr", "http://ref.atlasapi.org/channels/nickjr", "nickjr"),
    BOOMERANG("Boomerang", "http://ref.atlasapi.org/channels/boomerang", "boomerang"),
    BOOMERANG_PLUS1("Boomerang +1", "http://ref.atlasapi.org/channels/boomerangplus1", "boomerangplus1"),
    QVC("QVC", "http://ref.atlasapi.org/channels/qvc", "qvc"),
    GBC("GBC", "http://ref.atlasapi.org/channels/gbc", "gbc"),
    BBC_ENTERTAINMENT("BBC Entertainment", "http://ref.atlasapi.org/channels/bbcentertainment", "bbcentertainment"),
    FILM4_HD("FILM4 HD", "http://ref.atlasapi.org/channels/film4hd", "film4hd"),
    FILM4_PLUS1("FILM4 +1", "http://ref.atlasapi.org/channels/film4plus1", "film4plus1"),
    SKY1("SKY1", "http://ref.atlasapi.org/channels/sky1", "sky1"),
    DISCOVERY_HOME_AND_HEALTH("Discovery Home And Health", "http://ref.atlasapi.org/channels/discoveryhomeandhealth", "discoveryhomeandhealth"),
    DISCOVERY_HOME_AND_HEALTH_PLUS1("Discovery Home And Health +1", "http://ref.atlasapi.org/channels/discoveryhomeandhealthplus1", "discoveryhomeandhealthplus1"),
    MUTV("MUTV", "http://ref.atlasapi.org/channels/mutv", "mutv"),
    SKY_SPORTS_NEWS("Sky Sports News", "http://ref.atlasapi.org/channels/skysportsnews", "skysportsnews"),
    EUROSPORT("EUROSPORT", "http://ref.atlasapi.org/channels/eurosport", "eurosport"),
    EUROSPORT_HD("Eurosport HD", "http://ref.atlasapi.org/channels/eurosporthd", "eurosporthd"),
    KISS("KISS", "http://ref.atlasapi.org/channels/kiss", "kiss"),
    PLAYHOUSE_DISNEY("Playhouse Disney", "http://ref.atlasapi.org/channels/playhousedisney", "playhousedisney"),
    PLAYHOUSE_DISNEY_PLUS("Playhouse Disney Plus", "http://ref.atlasapi.org/channels/playhousedisneyplus", "playhousedisneyplus"),
    SKY_SPORTS_1("Sky Sports 1", "http://ref.atlasapi.org/channels/skysports1", "skysports1"),
    SKY_SPORTS_2("Sky Sports 2", "http://ref.atlasapi.org/channels/skysports2", "skysports2"),
    SKY_SPORTS_3("Sky Sports 3", "http://ref.atlasapi.org/channels/skysports3", "skysports3"),
    SKY_SPORTS_4("Sky Sports 4", "http://ref.atlasapi.org/channels/skysports4", "skysports4"),
    BIO("BIO", "http://ref.atlasapi.org/channels/bio", "bio"),
    BID_TV("Bid TV", "http://ref.atlasapi.org/channels/bidtv", "bidtv"),
    SKY_ARTS_1("Sky Arts 1", "http://ref.atlasapi.org/channels/skyarts1", "skyarts1"),
    EURONEWS("EURONEWS", "http://ref.atlasapi.org/channels/euronews", "euronews"),
    B4U_MOVIES("B4U Movies", "http://ref.atlasapi.org/channels/b4umovies", "b4umovies"),
    RED_HOT_AMATEUR("Red Hot Amateur", "http://ref.atlasapi.org/channels/redhotamateur", "redhotamateur"),
    EXTREME_SPORTS("Extreme Sports", "http://ref.atlasapi.org/channels/extremesports", "extremesports"),
    MTV_DANCE("MTV Dance", "http://ref.atlasapi.org/channels/mtvdance", "mtvdance"),
    STAR_PLUS("Star Plus", "http://ref.atlasapi.org/channels/starplus", "starplus"),
    TELEG("TELEG", "http://ref.atlasapi.org/channels/teleg", "teleg"),
    CHANNEL_9("Channel 9", "http://ref.atlasapi.org/channels/channel9", "channel9"),
    GOOD_FOOD("Good Food", "http://ref.atlasapi.org/channels/goodfood", "goodfood"),
    GOOD_FOOD_PLUS1("Good Food +1", "http://ref.atlasapi.org/channels/goodfoodplus1", "goodfoodplus1"),
    ATTHERACES("ATTHERACES", "http://ref.atlasapi.org/channels/attheraces", "attheraces"),
    NICKTOONS_TV("Nicktoons TV", "http://ref.atlasapi.org/channels/nicktoonstv", "nicktoonstv"),
    NICKTOONS_REPLAY("Nicktoons Replay", "http://ref.atlasapi.org/channels/nicktoonsreplay", "nicktoonsreplay"),
    CHART_SHOW_TV("Chart Show TV", "http://ref.atlasapi.org/channels/chartshowtv", "chartshowtv"),
    YESTERDAY("YESTERDAY", "http://ref.atlasapi.org/channels/yesterday", "yesterday"),
    YESTERDAY_PLUS1("Yesterday +1", "http://ref.atlasapi.org/channels/yesterdayplus1", "yesterdayplus1"),
    MAGIC("MAGIC", "http://ref.atlasapi.org/channels/magic", "magic"),
    SMASH_HITS("Smash Hits", "http://ref.atlasapi.org/channels/smashhits", "smashhits"),
    KERRANG("KERRANG", "http://ref.atlasapi.org/channels/kerrang", "kerrang"),
    THE_COMMUNITY_CHANNEL("The Community Channel", "http://ref.atlasapi.org/channels/thecommunitychannel", "thecommunitychannel"),
    SKY2("SKY2", "http://ref.atlasapi.org/channels/sky2", "sky2"),
    E_EXLAMATION("E Exlamation", "http://ref.atlasapi.org/channels/eexlamation", "eexlamation"),
    FLAUNT("FLAUNT", "http://ref.atlasapi.org/channels/flaunt", "flaunt"),
    SCUZZ("SCUZZ", "http://ref.atlasapi.org/channels/scuzz", "scuzz"),
    BLISS("BLISS", "http://ref.atlasapi.org/channels/bliss", "bliss"),
    ESPN_AMERICA("Espn America", "http://ref.atlasapi.org/channels/espnamerica", "espnamerica"),
    ESPN_AMERICA_HD("Espn America HD", "http://ref.atlasapi.org/channels/espnamericahd", "espnamericahd"),
    RED_HOT_40("Red Hot 40", "http://ref.atlasapi.org/channels/redhot40", "redhot40"),
    DAVE("DAVE", "http://ref.atlasapi.org/channels/dave", "dave"),
    DAVE_JA_VU("Dave Ja Vu", "http://ref.atlasapi.org/channels/davejavu", "davejavu"),
    PRICE_DROP_TV("Price Drop TV", "http://ref.atlasapi.org/channels/pricedroptv", "pricedroptv"),
    FX("FX", "http://ref.atlasapi.org/channels/fx", "fx"),
    FX_PLUS("Fx Plus", "http://ref.atlasapi.org/channels/fxplus", "fxplus"),
    EDEN("EDEN", "http://ref.atlasapi.org/channels/eden", "eden"),
    EDEN_PLUS1("Eden +1", "http://ref.atlasapi.org/channels/edenplus1", "edenplus1"),
    EDEN_HD("Eden HD", "http://ref.atlasapi.org/channels/edenhd", "edenhd"),
    BLIGHTY("BLIGHTY", "http://ref.atlasapi.org/channels/blighty", "blighty"),
    THE_HORROR_CHANNEL("The Horror Channel", "http://ref.atlasapi.org/channels/thehorrorchannel", "thehorrorchannel"),
    THE_HORROR_CHANNEL_PLUS1("The Horror Channel +1", "http://ref.atlasapi.org/channels/thehorrorchannelplus1", "thehorrorchannelplus1"),
    RACING_UK("Racing Uk", "http://ref.atlasapi.org/channels/racinguk", "racinguk"),
    CHELSEA_TV("Chelsea TV", "http://ref.atlasapi.org/channels/chelseatv", "chelseatv"),
    STAR_NEWS("Star News", "http://ref.atlasapi.org/channels/starnews", "starnews"),
    SETANTA_IRELAND("Setanta Ireland", "http://ref.atlasapi.org/channels/setantaireland", "setantaireland"),
    LIVINGIT("LIVINGIT", "http://ref.atlasapi.org/channels/livingit", "livingit"),
    LIVINGIT_PLUS1("Livingit +1", "http://ref.atlasapi.org/channels/livingitplus1", "livingitplus1"),
    EUROSPORT_2("Eurosport 2", "http://ref.atlasapi.org/channels/eurosport2", "eurosport2"),
    REALLY("REALLY", "http://ref.atlasapi.org/channels/really", "really"),
    RED_HOT_TV("Red Hot TV", "http://ref.atlasapi.org/channels/redhottv", "redhottv"),
    COMEDY_CENTRAL_EXTRA("Comedy Central Extra", "http://ref.atlasapi.org/channels/comedycentralextra", "comedycentralextra"),
    COMEDY_CENTRAL_EXTRA_PLUS1("Comedy Central Extra +1", "http://ref.atlasapi.org/channels/comedycentralextraplus1", "comedycentralextraplus1"),
    COMEDY_CENTRAL_HD("Comedy Central HD", "http://ref.atlasapi.org/channels/comedycentralhd", "comedycentralhd"),
    M95_TV_MARBELLA("M95 TV Marbella", "http://ref.atlasapi.org/channels/m95tvmarbella", "m95tvmarbella"),
    TV3_SPANISH("TV3 Spanish", "http://ref.atlasapi.org/channels/tv3spanish", "tv3spanish"),
    DISCOVERY_REAL_TIME("Discovery Real Time", "http://ref.atlasapi.org/channels/discoveryrealtime", "discoveryrealtime"),
    DISCOVERY_REAL_TIME_PLUS1("Discovery Real Time +1", "http://ref.atlasapi.org/channels/discoveryrealtimeplus1", "discoveryrealtimeplus1"),
    MOTORS_TV("Motors TV", "http://ref.atlasapi.org/channels/motorstv", "motorstv"),
    DISCOVERY_SHED("Discovery Shed", "http://ref.atlasapi.org/channels/discoveryshed", "discoveryshed"),
    TRUE_MOVIES("True Movies", "http://ref.atlasapi.org/channels/truemovies", "truemovies"),
    SKY3("SKY3", "http://ref.atlasapi.org/channels/sky3", "sky3"),
    SKY_3_PLUS1("Sky 3 +1", "http://ref.atlasapi.org/channels/sky3plus1", "sky3plus1"),
    DISNEY_CINEMAGIC("Disney Cinemagic", "http://ref.atlasapi.org/channels/disneycinemagic", "disneycinemagic"),
    DISNEY_CINEMAGIC_PLUS1("Disney Cinemagic +1", "http://ref.atlasapi.org/channels/disneycinemagicplus1", "disneycinemagicplus1"),
    DISNEY_CINEMAGIC_HD("Disney Cinemagic HD", "http://ref.atlasapi.org/channels/disneycinemagichd", "disneycinemagichd"),
    ESPN_CLASSIC("Espn Classic", "http://ref.atlasapi.org/channels/espnclassic", "espnclassic"),
    THREE_E("Three E", "http://ref.atlasapi.org/channels/threee", "threee"),
    FILMFLEX("FILMFLEX", "http://ref.atlasapi.org/channels/filmflex", "filmflex"),
    TCM2("TCM2", "http://ref.atlasapi.org/channels/tcm2", "tcm2"),
    CHRISTMAS24("CHRISTMAS24", "http://ref.atlasapi.org/channels/christmas24", "christmas24"),
    CHRISTMAS24_PLUS("CHRISTMAS24 Plus", "http://ref.atlasapi.org/channels/christmas24plus", "christmas24plus"),
    DISCOVERY_HD("Discovery HD", "http://ref.atlasapi.org/channels/discoveryhd", "discoveryhd"),
    NATIONAL_GEOGRAPHIC_HD("National Geographic HD", "http://ref.atlasapi.org/channels/nationalgeographichd", "nationalgeographichd"),
    SKY_BOX_OFFICE_HD1("Sky Box Office HD1", "http://ref.atlasapi.org/channels/skyboxofficehd1", "skyboxofficehd1"),
    SKY_BOX_OFFICE_HD2("Sky Box Office HD2", "http://ref.atlasapi.org/channels/skyboxofficehd2", "skyboxofficehd2"),
    SKY_SPORTS_1_HD("Sky Sports 1 HD", "http://ref.atlasapi.org/channels/skysports1hd", "skysports1hd"),
    SKY_SPORTS_2_HD("Sky Sports 2 HD", "http://ref.atlasapi.org/channels/skysports2hd", "skysports2hd"),
    E_EUROPE("E Europe", "http://ref.atlasapi.org/channels/eeurope", "eeurope"),
    NAT_GEO_WILD("Nat Geo Wild", "http://ref.atlasapi.org/channels/natgeowild", "natgeowild"),
    CRIME_AND_INVESTIGATION("Crime And Investigation", "http://ref.atlasapi.org/channels/crimeandinvestigation", "crimeandinvestigation"),
    CRIME_AND_INVESTIGATION_PLUS1("Crime And Investigation +1", "http://ref.atlasapi.org/channels/crimeandinvestigationplus1", "crimeandinvestigationplus1"),
    Channel("Channel", "http://ref.atlasapi.org/channels/channel", "channel"),
    Channel_HD("Channel HD", "http://ref.atlasapi.org/channels/channelhd", "channelhd"),
    SKY_MOVIES_CRIME_AND_THRILLER("Sky Movies Crime And Thriller", "http://ref.atlasapi.org/channels/skymoviescrimeandthriller", "skymoviescrimeandthriller"),
    SKY_MOVIES_CRIME_AND_THRILLER_HD("Sky Movies Crime And Thriller HD", "http://ref.atlasapi.org/channels/skymoviescrimeandthrillerhd", "skymoviescrimeandthrillerhd"),
    SKY_MOVIES_PREMIERE("Sky Movies Premiere", "http://ref.atlasapi.org/channels/skymoviespremiere", "skymoviespremiere"),
    SKY_MOVIES_PREMIERE_PLUS1("Sky Movies Premiere +1", "http://ref.atlasapi.org/channels/skymoviespremiereplus1", "skymoviespremiereplus1"),
    SKY_MOVIES_PREMIERE_HD("Sky Movies Premiere HD", "http://ref.atlasapi.org/channels/skymoviespremierehd", "skymoviespremierehd"),
    SKY_MOVIES_COMEDY("Sky Movies Comedy", "http://ref.atlasapi.org/channels/skymoviescomedy", "skymoviescomedy"),
    SKY_MOVIES_COMEDY_HD("Sky Movies Comedy HD", "http://ref.atlasapi.org/channels/skymoviescomedyhd", "skymoviescomedyhd"),
    SKY_MOVIES_ACTION_AND_ADVENTURE("Sky Movies Action And Adventure", "http://ref.atlasapi.org/channels/skymoviesactionandadventure", "skymoviesactionandadventure"),
    SKY_MOVIES_ACTION_AND_ADVENTURE_HD("Sky Movies Action And Adventure HD", "http://ref.atlasapi.org/channels/skymoviesactionandadventurehd", "skymoviesactionandadventurehd"),
    SKY_MOVIES_FAMILY("Sky Movies Family", "http://ref.atlasapi.org/channels/skymoviesfamily", "skymoviesfamily"),
    SKY_MOVIES_FAMILY_HD("Sky Movies Family HD", "http://ref.atlasapi.org/channels/skymoviesfamilyhd", "skymoviesfamilyhd"),
    SKY_MOVIES_DRAMA_AND_ROMANCE("Sky Movies Drama And Romance", "http://ref.atlasapi.org/channels/skymoviesdramaandromance", "skymoviesdramaandromance"),
    SKY_MOVIES_DRAMA_AND_ROMANCE_HD("Sky Movies Drama And Romance HD", "http://ref.atlasapi.org/channels/skymoviesdramaandromancehd", "skymoviesdramaandromancehd"),
    SKY_MOVIES_SCIFI_HORROR("Sky Movies Scifi Horror", "http://ref.atlasapi.org/channels/skymoviesscifihorror", "skymoviesscifihorror"),
    SKY_MOVIES_SCIFI_HORROR_HD("Sky Movies Scifi Horror HD", "http://ref.atlasapi.org/channels/skymoviesscifihorrorhd", "skymoviesscifihorrorhd"),
    SKY_MOVIES_CLASSICS("Sky Movies Classics", "http://ref.atlasapi.org/channels/skymoviesclassics", "skymoviesclassics"),
    SKY_MOVIES_CLASSICS_HD("Sky Movies Classics HD", "http://ref.atlasapi.org/channels/skymoviesclassicshd", "skymoviesclassicshd"),
    SKY_MOVIES_MODERN_GREATS("Sky Movies Modern Greats", "http://ref.atlasapi.org/channels/skymoviesmoderngreats", "skymoviesmoderngreats"),
    SKY_MOVIES_MODERN_GREATS_HD("Sky Movies Modern Greats HD", "http://ref.atlasapi.org/channels/skymoviesmoderngreatshd", "skymoviesmoderngreatshd"),
    SKY_MOVIES_INDIE("Sky Movies Indie", "http://ref.atlasapi.org/channels/skymoviesindie", "skymoviesindie"),
    SKY_MOVIES_INDIE_HD("Sky Movies Indie HD", "http://ref.atlasapi.org/channels/skymoviesindiehd", "skymoviesindiehd"),
    AL_JAZEERA_ENGLISH("Al Jazeera English", "http://ref.atlasapi.org/channels/aljazeeraenglish", "aljazeeraenglish"),
    HISTORY_HD("History HD", "http://ref.atlasapi.org/channels/historyhd", "historyhd"),
    SKY1_HD("SKY1 HD", "http://ref.atlasapi.org/channels/sky1hd", "sky1hd"),
    SKY_ARTS_1_HD("Sky Arts 1 HD", "http://ref.atlasapi.org/channels/skyarts1hd", "skyarts1hd"),
    CARTOONITO("CARTOONITO", "http://ref.atlasapi.org/channels/cartoonito", "cartoonito"),
    BEST_DIRECT("Best Direct", "http://ref.atlasapi.org/channels/bestdirect", "bestdirect"),
    BRAVO_2("Bravo 2", "http://ref.atlasapi.org/channels/bravo2", "bravo2"),
    DEUTSCHE_WELLE("Deutsche Welle", "http://ref.atlasapi.org/channels/deutschewelle", "deutschewelle"),
    GEMS_TV("Gems TV", "http://ref.atlasapi.org/channels/gemstv", "gemstv"),
    GEM_COLLECTOR("Gem Collector", "http://ref.atlasapi.org/channels/gemcollector", "gemcollector"),
    Channel_S("Channel S", "http://ref.atlasapi.org/channels/channels", "channels"),
    SETANTA_SPORTS_1_IRELAND("Setanta Sports 1 Ireland", "http://ref.atlasapi.org/channels/setantasports1ireland", "setantasports1ireland"),
    DIVA("Diva", "http://ref.atlasapi.org/channels/diva", "diva"),
    DIVA_PLUS1("Diva +1", "http://ref.atlasapi.org/channels/divaplus1", "divaplus1"),
    Channel_ONE("Channel One", "http://ref.atlasapi.org/channels/channelone", "channelone"),
    Channel_ONE_PLUS1("Channel One +1", "http://ref.atlasapi.org/channels/channeloneplus1", "channeloneplus1"),
    CN_TOO("CN Too", "http://ref.atlasapi.org/channels/cntoo", "cntoo"),
    POP("POP", "http://ref.atlasapi.org/channels/pop", "pop"),
    TINY_POP("Tiny Pop", "http://ref.atlasapi.org/channels/tinypop", "tinypop"),
    DMAX("DMAX", "http://ref.atlasapi.org/channels/dmax", "dmax"),
    DMAX_PLUS1("DMAX +1", "http://ref.atlasapi.org/channels/dmaxplus1", "dmaxplus1"),
    DMAX_2("DMAX 2", "http://ref.atlasapi.org/channels/dmax2", "dmax2"),
    HORSE_AND_COUNTRY("Horse And Country", "http://ref.atlasapi.org/channels/horseandcountry", "horseandcountry"),
    Channel_7("Channel 7", "http://ref.atlasapi.org/channels/channel7", "channel7"),
    SKY_SPORTS_3_HD("Sky Sports 3 HD", "http://ref.atlasapi.org/channels/skysports3hd", "skysports3hd"),
    FLAVA("Flava", "http://ref.atlasapi.org/channels/flava", "flava"),
    FX_HD("Fx HD", "http://ref.atlasapi.org/channels/fxhd", "fxhd"),
    NATIONAL_GEOGRAPHIC_HD_PAN_EUROPEAN("National Geographic HD Pan European", "http://ref.atlasapi.org/channels/nationalgeographichdpaneuropean", "nationalgeographichdpaneuropean"),
    MOVIES4MEN("MOVIES4MEN", "http://ref.atlasapi.org/channels/movies4men", "movies4men"),
    MOVIES4MEN_PLUS1("MOVIES4Men +1", "http://ref.atlasapi.org/channels/movies4menplus1", "movies4menplus1"),
    TRUE_MOVIES_2("True Movies 2", "http://ref.atlasapi.org/channels/truemovies2", "truemovies2"),
    MOVIES4MEN2("MOVIES4MEN2", "http://ref.atlasapi.org/channels/movies4men2", "movies4men2"),
    MOVIES4MEN2_PLUS1("MOVIES4MEN2 +1", "http://ref.atlasapi.org/channels/movies4men2plus1", "movies4men2plus1"),
    MILITARY_HISTORY("Military History", "http://ref.atlasapi.org/channels/militaryhistory", "militaryhistory"),
    THE_STYLE_NETWORK("The Style Network", "http://ref.atlasapi.org/channels/thestylenetwork", "thestylenetwork"),
    WATCH("Watch", "http://ref.atlasapi.org/channels/watch", "watch"),
    WATCH_PLUS1("Watch +1", "http://ref.atlasapi.org/channels/watchplus1", "watchplus1"),
    SKY_ARTS_2("Sky Arts 2", "http://ref.atlasapi.org/channels/skyarts2", "skyarts2"),
    WEDDING_TV("Wedding TV", "http://ref.atlasapi.org/channels/weddingtv", "weddingtv"),
    PROPELLER_TV("Propeller TV", "http://ref.atlasapi.org/channels/propellertv", "propellertv"),
    MTVN_HD("MTVn HD", "http://ref.atlasapi.org/channels/mtvnhd", "mtvnhd"),
    INVESTIGATION_DISCOVERY("Investigation Discovery", "http://ref.atlasapi.org/channels/investigationdiscovery", "investigationdiscovery"),
    AIT_INTERNATIONAL("Ait International", "http://ref.atlasapi.org/channels/aitinternational", "aitinternational"),
    CINEMOI("CINEMOI", "http://ref.atlasapi.org/channels/cinemoi", "cinemoi"),
    SKY_ARTS_2_HD("Sky Arts 2 HD", "http://ref.atlasapi.org/channels/skyarts2hd", "skyarts2hd"),
    BIO_HD("Bio HD", "http://ref.atlasapi.org/channels/biohd", "biohd"),
    CRIME_AND_INVESTIGATION_HD("Crime And Investigation HD", "http://ref.atlasapi.org/channels/crimeandinvestigationhd", "crimeandinvestigationhd"),
    NAT_GEO_WILD_HD("Nat Geo Wild HD", "http://ref.atlasapi.org/channels/natgeowildhd", "natgeowildhd"),
    QUEST("QUEST", "http://ref.atlasapi.org/channels/quest", "quest"),
    DISCOVERY_QUEST_PLUS1("Discovery Quest +1", "http://ref.atlasapi.org/channels/discoveryquestplus1", "discoveryquestplus1"),
    QUEST_FREEVIEW("Quest Freeview", "http://ref.atlasapi.org/channels/questfreeview", "questfreeview"),
    ESPN("ESPN", "http://ref.atlasapi.org/channels/espn", "espn"),
    ESPN_HD("ESPN HD", "http://ref.atlasapi.org/channels/espnhd", "espnhd"),
    DISNEY_XD("Disney Xd", "http://ref.atlasapi.org/channels/disneyxd", "disneyxd"),
    DISNEY_XD_PLUS1("Disney Xd +1", "http://ref.atlasapi.org/channels/disneyxdplus1", "disneyxdplus1"),
    DISNEY_XD_HD("Disney Xd HD", "http://ref.atlasapi.org/channels/disneyxdhd", "disneyxdhd"),
    CBS_REALITY("CBS Reality", "http://ref.atlasapi.org/channels/cbsreality", "cbsreality"),
    MGM("MGM", "http://ref.atlasapi.org/channels/mgm", "mgm"),
    CBS_DRAMA("CBS Drama", "http://ref.atlasapi.org/channels/cbsdrama", "cbsdrama"),
    CBS_ACTION("CBS Action", "http://ref.atlasapi.org/channels/cbsaction", "cbsaction"),
    VIVA("VIVA", "http://ref.atlasapi.org/channels/viva", "viva"),
    FOOD_NETWORK("Food Network", "http://ref.atlasapi.org/channels/foodnetwork", "foodnetwork"),
    FOOD_NETWORK_PLUS1("Food Network +1", "http://ref.atlasapi.org/channels/foodnetworkplus1", "foodnetworkplus1"),
    MGM_HD("Mgm HD", "http://ref.atlasapi.org/channels/mgmhd", "mgmhd"),
    MTV_SHOWS("MTV Shows", "http://ref.atlasapi.org/channels/mtvshows", "mtvshows"),
    NICK_JR_2("Nick Jr 2", "http://ref.atlasapi.org/channels/nickjr2", "nickjr2"),
    NHK_WORLD("NHK World", "http://ref.atlasapi.org/channels/nhkworld", "nhkworld"),
    TRUEENT("TRUEENT", "http://ref.atlasapi.org/channels/trueent", "trueent"),
    BODY_IN_BALANCE("Body In Balance", "http://ref.atlasapi.org/channels/bodyinbalance", "bodyinbalance"),
    THE_ACTIVE_CHANNEL("The Active Channel", "http://ref.atlasapi.org/channels/theactivechannel", "theactivechannel"),
    FITNESS_TV("Fitness TV", "http://ref.atlasapi.org/channels/fitnesstv", "fitnesstv"),
    RUSH_HD("Rush HD", "http://ref.atlasapi.org/channels/rushhd", "rushhd"),
    BBC_SPORT_INTERACTIVE_BBC_ONE("BBC Sport Interactive BBC One", "http://ref.atlasapi.org/channels/bbcsportinteractivebbcone", "bbcsportinteractivebbcone"),
    BBC_SPORT_INTERACTIVE_FREEVIEW("BBC Sport Interactive Freeview", "http://ref.atlasapi.org/channels/bbcsportinteractivefreeview", "bbcsportinteractivefreeview"),
    SKY_3D("Sky 3D", "http://ref.atlasapi.org/channels/sky3d", "sky3d"),
    SKY_SPORTS_4_HD("Sky Sports 4 HD", "http://ref.atlasapi.org/channels/skysports4hd", "skysports4hd");
    
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
