package org.atlasapi.media.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableList.Builder;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.model.SelfModelling;
import com.metabroadcast.common.model.SimpleModel;

public class Channel implements SelfModelling {

    public static final Channel BBC_IPLAYER = new Channel("iPlayer", "http://www.bbc.co.uk/iplayer", "iplayer");
    public static final Channel HULU = new Channel("Hulu", "http://www.hulu.com", "hulu");
    public static final Channel YOUTUBE = new Channel("YouTube", "http://www.youtube.com", "youtube");
    public static final Channel SEESAW = new Channel("Seesaw", "http://www.seesaw.com", "seesaw");
    public static final Channel C4_4OD = new Channel("4oD", "http://www.channel4.com/programmes/4od", "4od");
    public static final Channel BBC_ONE = new Channel("BBC One", "http://www.bbc.co.uk/services/bbcone/london", "bbcone");
    public static final Channel BBC_ONE_NORTHERN_IRELAND = new Channel("BBC One Northern Ireland", "http://www.bbc.co.uk/services/bbcone/ni", "bbcone-ni");
    public static final Channel BBC_ONE_CAMBRIDGE = new Channel("BBC One Cambridgeshire", "http://www.bbc.co.uk/services/bbcone/cambridge", "bbcone-cambridge");
    public static final Channel BBC_ONE_CHANNEL_ISLANDS = new Channel("BBC One Channel Islands", "http://www.bbc.co.uk/services/bbcone/channel_islands", "bbcone-channel_islands");
    public static final Channel BBC_ONE_EAST = new Channel("BBC One East", "http://www.bbc.co.uk/services/bbcone/east", "bbcone-east");
    public static final Channel BBC_ONE_EAST_MIDLANDS = new Channel("BBC One East Midlands", "http://www.bbc.co.uk/services/bbcone/east_midlands", "bbcone-east_midlands");
    public static final Channel BBC_ONE_HD = new Channel("BBC One HD", "http://www.bbc.co.uk/services/bbcone/hd", "bbcone-hd");
    public static final Channel BBC_ONE_NORTH_EAST = new Channel("BBC One North East & Cumbria", "http://www.bbc.co.uk/services/bbcone/north_east", "bbcone-north_east");
    public static final Channel BBC_ONE_NORTH_WEST = new Channel("BBC One North West", "http://www.bbc.co.uk/services/bbcone/north_west", "bbcone-north_west");
    public static final Channel BBC_ONE_OXFORD = new Channel("BBC One Oxfordshire", "http://www.bbc.co.uk/services/bbcone/oxford", "bbcone-oxford");
    public static final Channel BBC_ONE_SCOTLAND = new Channel("BBC One Scotland", "http://www.bbc.co.uk/services/bbcone/scotland", "bbcone-scotland");
    public static final Channel BBC_ONE_SOUTH = new Channel("BBC One South", "http://www.bbc.co.uk/services/bbcone/south", "bbcone-south");
    public static final Channel BBC_ONE_SOUTH_EAST = new Channel("BBC One South East", "http://www.bbc.co.uk/services/bbcone/south_east", "bbcone-south_east");
    public static final Channel BBC_ONE_WALES = new Channel("BBC One Wales", "http://www.bbc.co.uk/services/bbcone/wales", "bbcone-wales");
    public static final Channel BBC_ONE_SOUTH_WEST = new Channel("BBC One South West", "http://www.bbc.co.uk/services/bbcone/south_west", "bbcone-south_west");
    public static final Channel BBC_ONE_WEST = new Channel("BBC One West", "http://www.bbc.co.uk/services/bbcone/west", "bbcone-west");
    public static final Channel BBC_ONE_WEST_MIDLANDS = new Channel("BBC One West Midlands", "http://www.bbc.co.uk/services/bbcone/west_midlands", "bbcone-west_midlands");
    public static final Channel BBC_ONE_EAST_YORKSHIRE = new Channel("BBC One Yorks & Lincs", "http://www.bbc.co.uk/services/bbcone/east_yorkshire", "bbcone-east_yorkshire");
    public static final Channel BBC_ONE_YORKSHIRE = new Channel("BBC One Yorkshire", "http://www.bbc.co.uk/services/bbcone/yorkshire", "bbcone-yorkshire");
    public static final Channel BBC_TWO = new Channel("BBC Two", "http://www.bbc.co.uk/services/bbctwo/england", "bbctwo");

    public static final Channel BBC_TWO_NORTHERN_IRELAND = new Channel("BBC Two Northern Ireland", "http://www.bbc.co.uk/services/bbctwo/ni", "bbctwo-ni");
    public static final Channel BBC_TWO_NORTHERN_IRELAND_ALALOGUE = new Channel("BBC Two Northern Ireland (Analogue)", "http://www.bbc.co.uk/services/bbctwo/ni_analogue", "bbctwo-ni_analogue");
    public static final Channel BBC_TWO_SCOTLAND = new Channel("BBC Two Scotland", "http://www.bbc.co.uk/services/bbctwo/scotland", "bbctwo-scotland");
    public static final Channel BBC_TWO_WALES = new Channel("BBC Two Wales", "http://www.bbc.co.uk/services/bbctwo/wales", "bbctwo-wales");
    public static final Channel BBC_TWO_WALES_ANALOGUE = new Channel("BBC Two Wales (Analogue)", "http://www.bbc.co.uk/services/bbctwo/wales_analogue", "bbctwo-walesanalogue");

    public static final Channel BBC_THREE = new Channel("BBC Three", "http://www.bbc.co.uk/services/bbcthree", "bbcthree");
    public static final Channel BBC_FOUR = new Channel("BBC Four", "http://www.bbc.co.uk/services/bbcfour", "bbcfour");
    public static final Channel BBC_NEWS = new Channel("BBC News", "http://www.bbc.co.uk/services/bbcnews", "bbcnews");
    public static final Channel BBC_WORLD_NEWS = new Channel("BBC World News", "http://www.bbc.co.uk/services/bbcworldnews", "bbcworldnews");
    public static final Channel BBC_PARLIMENT = new Channel("BBC Parliment", "http://www.bbc.co.uk/services/parliament", "bbcparliment");
    public static final Channel BBC_HD = new Channel("BBC HD", "http://www.bbc.co.uk/services/bbchd", "bbchd");

    public static final Channel BBC_RADIO_LONDON = new Channel("BBC Radio London", "http://www.bbc.co.uk/services/london", "london");
    public static final Channel BBC_RADIO_BERKSHIRE = new Channel("BBC Radio Berkshire", "http://www.bbc.co.uk/services/berkshire", "berkshire");
    public static final Channel BBC_RADIO_BRISTOL = new Channel("BBC Radio Bristol", "http://www.bbc.co.uk/services/bristol", "bristol");
    public static final Channel BBC_RADIO_CAMBRIDGESHIRE = new Channel("BBC Radio Cambridgeshire", "http://www.bbc.co.uk/services/cambridgeshire", "cambridgeshire");
    public static final Channel BBC_RADIO_CORNWALL = new Channel("BBC Radio Cornwall", "http://www.bbc.co.uk/services/cornwall", "cornwall");
    public static final Channel BBC_RADIO_COVENTRY = new Channel("BBC Radio Coventry", "http://www.bbc.co.uk/services/coventry", "coventry");
    public static final Channel BBC_RADIO_CUMBRIA = new Channel("BBC Radio Cumbria", "http://www.bbc.co.uk/services/cumbria", "cumbria");
    public static final Channel BBC_RADIO_DERBY = new Channel("BBC Radio Derby", "http://www.bbc.co.uk/services/derby", "derby");
    public static final Channel BBC_RADIO_DEVON = new Channel("BBC Radio Devon", "http://www.bbc.co.uk/services/devon", "devon");
    public static final Channel BBC_RADIO_ESSEX = new Channel("BBC Radio Essex", "http://www.bbc.co.uk/services/essex", "essex");
    public static final Channel BBC_RADIO_GLOUCESTERSHIRE = new Channel("BBC Radio Gloucestershire", "http://www.bbc.co.uk/services/gloucestershire", "gloucestershire");
    public static final Channel BBC_RADIO_GUERNSEY = new Channel("BBC Radio Guernsey", "http://www.bbc.co.uk/services/guernsey", "guernsey");
    public static final Channel BBC_RADIO_HEREFORDANDWORCESTER = new Channel("BBC Radio Herefordandworcester", "http://www.bbc.co.uk/services/herefordandworcester", "herefordandworcester");
    public static final Channel BBC_RADIO_HUMBERSIDE = new Channel("BBC Radio Humberside", "http://www.bbc.co.uk/services/humberside", "humberside");
    public static final Channel BBC_RADIO_JERSEY = new Channel("BBC Radio Jersey", "http://www.bbc.co.uk/services/jersey", "jersey");
    public static final Channel BBC_RADIO_KENT = new Channel("BBC Radio Kent", "http://www.bbc.co.uk/services/kent", "kent");
    public static final Channel BBC_RADIO_LANCASHIRE = new Channel("BBC Radio Lancashire", "http://www.bbc.co.uk/services/lancashire", "lancashire");
    public static final Channel BBC_RADIO_LEEDS = new Channel("BBC Radio Leeds", "http://www.bbc.co.uk/services/leeds", "leeds");
    public static final Channel BBC_RADIO_LEICESTER = new Channel("BBC Radio Leicester", "http://www.bbc.co.uk/services/leicester", "leicester");
    public static final Channel BBC_RADIO_LINCOLNSHIRE = new Channel("BBC Radio Lincolnshire", "http://www.bbc.co.uk/services/lincolnshire", "lincolnshire");
    public static final Channel BBC_RADIO_MANCHESTER = new Channel("BBC Radio Manchester", "http://www.bbc.co.uk/services/manchester", "manchester");
    public static final Channel BBC_RADIO_MERSEYSIDE = new Channel("BBC Radio Merseyside", "http://www.bbc.co.uk/services/merseyside", "merseyside");
    public static final Channel BBC_RADIO_NEWCASTLE = new Channel("BBC Radio Newcastle", "http://www.bbc.co.uk/services/newcastle", "newcastle");
    public static final Channel BBC_RADIO_NORFOLK = new Channel("BBC Radio Norfolk", "http://www.bbc.co.uk/services/norfolk", "norfolk");
    public static final Channel BBC_RADIO_NORTHAMPTON = new Channel("BBC Radio Northampton", "http://www.bbc.co.uk/services/northampton", "northampton");
    public static final Channel BBC_RADIO_NOTTINGHAM = new Channel("BBC Radio Nottingham", "http://www.bbc.co.uk/services/nottingham", "nottingham");
    public static final Channel BBC_RADIO_OXFORD = new Channel("BBC Radio Oxford", "http://www.bbc.co.uk/services/oxford", "oxford");
    public static final Channel BBC_RADIO_SHEFFIELD = new Channel("BBC Radio Sheffield", "http://www.bbc.co.uk/services/sheffield", "sheffield");
    public static final Channel BBC_RADIO_SHROPSHIRE = new Channel("BBC Radio Shropshire", "http://www.bbc.co.uk/services/shropshire", "shropshire");
    public static final Channel BBC_RADIO_SOLENT = new Channel("BBC Radio Solent", "http://www.bbc.co.uk/services/solent", "solent");
    public static final Channel BBC_RADIO_SOMERSET = new Channel("BBC Radio Somerset", "http://www.bbc.co.uk/services/somerset", "somerset");
    public static final Channel BBC_RADIO_STOKE = new Channel("BBC Radio Stoke", "http://www.bbc.co.uk/services/stoke", "stoke");
    public static final Channel BBC_RADIO_SUFFOLK = new Channel("BBC Radio Suffolk", "http://www.bbc.co.uk/services/suffolk", "suffolk");
    public static final Channel BBC_RADIO_SURREY = new Channel("BBC Radio Surrey", "http://www.bbc.co.uk/services/surrey", "surrey");
    public static final Channel BBC_RADIO_SUSSEX = new Channel("BBC Radio Sussex", "http://www.bbc.co.uk/services/sussex", "sussex");
    public static final Channel BBC_RADIO_WILTSHIRE = new Channel("BBC Radio Wiltshire", "http://www.bbc.co.uk/services/wiltshire", "wiltshire");
    public static final Channel BBC_RADIO_YORK = new Channel("BBC Radio York", "http://www.bbc.co.uk/services/york", "york");
    public static final Channel BBC_RADIO_TEES = new Channel("BBC Radio Tees", "http://www.bbc.co.uk/services/tees", "tees");
    public static final Channel BBC_RADIO_THREECOUNTIES = new Channel("BBC Radio Threecounties", "http://www.bbc.co.uk/services/threecounties", "threecounties");
    public static final Channel BBC_RADIO_WM = new Channel("BBC Radio Wm", "http://www.bbc.co.uk/services/wm", "wm");
    public static final Channel BBC_RADIO_RADIO1 = new Channel("BBC Radio Radio1", "http://www.bbc.co.uk/services/radio1/england", "radio1");
    public static final Channel BBC_RADIO_1XTRA = new Channel("BBC Radio 1xtra", "http://www.bbc.co.uk/services/1xtra", "1xtra");
    public static final Channel BBC_RADIO_RADIO2 = new Channel("BBC Radio Radio2", "http://www.bbc.co.uk/services/radio2", "radio2");
    public static final Channel BBC_RADIO_RADIO3 = new Channel("BBC Radio Radio3", "http://www.bbc.co.uk/services/radio3", "radio3");
    public static final Channel BBC_RADIO_RADIO4 = new Channel("BBC Radio Radio4", "http://www.bbc.co.uk/services/radio4/fm", "radio4");
    public static final Channel BBC_RADIO_5LIVE = new Channel("BBC Radio 5live", "http://www.bbc.co.uk/services/5live", "5live");
    public static final Channel BBC_RADIO_5LIVESPORTSEXTRA = new Channel("BBC Radio 5livesportsextra", "http://www.bbc.co.uk/services/5livesportsextra", "5livesportsextra");
    public static final Channel BBC_RADIO_6MUSIC = new Channel("BBC Radio 6music", "http://www.bbc.co.uk/services/6music", "6music");
    public static final Channel BBC_RADIO_RADIO7 = new Channel("BBC Radio Radio7", "http://www.bbc.co.uk/services/radio7", "radio7");
    public static final Channel BBC_RADIO_ASIANNETWORK = new Channel("BBC Radio Asiannetwork", "http://www.bbc.co.uk/services/asiannetwork", "asiannetwork");
    public static final Channel BBC_RADIO_WORLDSERVICE = new Channel("BBC Radio Worldservice", "http://www.bbc.co.uk/services/worldservice", "worldservice");
    public static final Channel BBC_RADIO_RADIOSCOTLAND = new Channel("BBC Radio Radioscotland", "http://www.bbc.co.uk/services/radioscotland/fm", "radioscotland");
    public static final Channel BBC_RADIO_RADIONANGAIDHEAL = new Channel("BBC Radio Radionangaidheal", "http://www.bbc.co.uk/services/radionangaidheal", "radionangaidheal");
    public static final Channel BBC_RADIO_RADIOULSTER = new Channel("BBC Radio Radioulster", "http://www.bbc.co.uk/services/radioulster", "radioulster");
    public static final Channel BBC_RADIO_RADIOFOYLE = new Channel("BBC Radio Radiofoyle", "http://www.bbc.co.uk/services/radiofoyle", "radiofoyle");
    public static final Channel BBC_RADIO_RADIOWALES = new Channel("BBC Radio Radiowales", "http://www.bbc.co.uk/services/radiowales/fm", "radiowales");
    public static final Channel BBC_RADIO_RADIOCYMRU = new Channel("BBC Radio Radiocymru", "http://www.bbc.co.uk/services/radiocymru", "radiocymru");
    public static final Channel BBC_RADIO_RADIO4_LW = new Channel("BBC Radio Radio4 LW", "http://www.bbc.co.uk/services/radio4/lw", "radio4lw");

    public static final Channel CHANNEL_FOUR = new Channel("Channel 4", "http://www.channel4.com", "channel4");
    public static final Channel MORE_FOUR = new Channel("More 4", "http://www.channel4.com/more4", "more4");
    public static final Channel FILM_4 = new Channel("Film 4", "http://film4.com", "film4");
    public static final Channel E_FOUR = new Channel("E4", "http://www.e4.com", "e4");
    public static final Channel FOUR_MUSIC = new Channel("4 Music", "http://www.4music.com", "4music");

    public static final Channel FIVE = new Channel("Five", "http://www.five.tv", "five");
    public static final Channel FIVER = new Channel("Fiver", "http://www.five.tv/channels/fiver", "fiver");
    public static final Channel FIVE_USA = new Channel("Five USA", "http://www.five.tv/channels/five-usa", "fiveusa");

    public static final Channel ITV1_LONDON = new Channel("ITV1 London", "http://www.itv.com/channels/itv1/london", "itv1london");
    public static final Channel ITV1_GRANADA = new Channel("ITV1 Granada", "http://www.itv.com/channels/itv1/granada", "itv1granada");
    public static final Channel ITV1_TYNE_TEES = new Channel("ITV1 Tyne Tees", "http://www.itv.com/channels/itv1/tynetees", "itv1tynetees");
    public static final Channel ITV1_BORDER_SOUTH = new Channel("ITV1 Border South", "http://www.itv.com/channels/itv1/bordersouth", "itv1bordersouth");
    public static final Channel ITV1_MERIDIAN = new Channel("ITV1 Meridian", "http://www.itv.com/channels/itv1/meridian", "itv1meridian");
    public static final Channel ITV1_ANGLIA = new Channel("ITV1 Anglia", "http://www.itv.com/channels/itv1/anglia", "itv1anglia");
    public static final Channel ITV1_CHANNEL = new Channel("ITV1 Channel", "http://www.itv.com/channels/itv1/channel", "itv1channel");
    public static final Channel ITV1_WALES = new Channel("ITV1 Wales", "http://www.itv.com/channels/itv1/wales", "itv1wales");
    public static final Channel ITV1_WEST = new Channel("ITV1 West", "http://www.itv.com/channels/itv1/west", "itv1west");
    public static final Channel ITV1_CARLTON_CENTRAL = new Channel("ITV1 Carlton-Central", "http://www.itv.com/channels/itv1/carltoncentral", "itv1carltoncentral");
    public static final Channel ITV1_CARLTON_WESTCOUNTRY = new Channel("ITV1 Carlton-Westcountry", "http://www.itv.com/channels/itv1/carltonwestcountry", "itv1carltonwestcountry");
    public static final Channel ITV1_BORDER_NORTH = new Channel("ITV1 Border North", "http://www.itv.com/channels/itv1/bordernorth", "itv1bordernorth");
    public static final Channel ITV1_THAMES_VALLEY_NORTH = new Channel("ITV1 Thames Valley North", "http://www.itv.com/channels/itv1/thamesvalleynorth", "itv1thamesvalleynorth");
    public static final Channel ITV1_THAMES_VALLEY_SOUTH = new Channel("ITV1 Thames Valley South", "http://www.itv.com/channels/itv1/thamesvalleysouth", "itv1thamesvalleysouth");
    public static final Channel ITV1_HD = new Channel("ITV1 HD", "http://www.itv.com/channels/itv1/hd", "itv1hd");

    public static final Channel ITV2 = new Channel("ITV2", "http://www.itv.com/channels/itv2", "itv2");
    public static final Channel ITV2_HD = new Channel("ITV2 HD", "http://www.itv.com/channels/itv2/hd", "itv2hd");
    public static final Channel ITV3 = new Channel("ITV3", "http://www.itv.com/channels/itv3", "itv3");
    public static final Channel ITV3_HD = new Channel("ITV3 HD", "http://www.itv.com/channels/itv3/hd", "itv3hd");
    public static final Channel ITV4 = new Channel("ITV4", "http://www.itv.com/channels/itv4", "itv4");
    public static final Channel ITV4_HD = new Channel("ITV4 HD", "http://www.itv.com/channels/itv4/hd", "itv4hd");

    public static final Channel YTV = new Channel("Y TV", "http://ref.atlasapi.org/channels/ytv", "ytv");
    public static final Channel ULSTER = new Channel("Ulster", "http://ref.atlasapi.org/channels/ulster", "ulster");
    public static final Channel ULSTER_HD = new Channel("Ulster HD", "http://ref.atlasapi.org/channels/ulsterhd", "ulsterhd");
    public static final Channel STV_CENTRAL = new Channel("STV Central", "http://ref.atlasapi.org/channels/stvcentral", "stvcentral");
    public static final Channel STV_HD = new Channel("SVT HD", "http://ref.atlasapi.org/channels/stvhd", "stvhd");
    public static final Channel STV_NORTH = new Channel("STV NORTH", "http://ref.atlasapi.org/channels/stvnorth", "stvnorth");
    public static final Channel Channel_4_PLUS1 = new Channel("Channel 4 +1", "http://www.channel4.com/cchannel4plus1", "channel4plus1");
    public static final Channel Channel_4_HD = new Channel("Channel 4 HD", "http://ref.atlasapi.org/channels/channel4hd", "channel4hd");
    public static final Channel S4C = new Channel("S4C", "http://ref.atlasapi.org/channels/s4c", "s4c");
    public static final Channel S4C_CLIRLUN = new Channel("S4C Clirlun", "http://ref.atlasapi.org/channels/s4cclirlun", "s4cclirlun");
    public static final Channel RTE1 = new Channel("RTE 1", "http://ref.atlasapi.org/channels/rte1", "rte1");
    public static final Channel RTE2 = new Channel("RTE 2", "http://ref.atlasapi.org/channels/rte2", "rte2");
    public static final Channel TG4 = new Channel("TG 4", "http://ref.atlasapi.org/channels/tg4", "tg4");
    public static final Channel TV3 = new Channel("TV 3", "http://ref.atlasapi.org/channels/tv3", "tv3");
    public static final Channel GMTV_DIGITAL = new Channel("GMTV Digital", "http://ref.atlasapi.org/channels/gmtv", "gmtv");
    public static final Channel ITV2_PLUS1 = new Channel("ITV2 +1", "http://www.itv.com/channels/itv2#plus1", "itv2plus1");
    public static final Channel E4_PLUS1 = new Channel("E4 +1", "http://www.e4.com/plus1", "e4plus1");
    public static final Channel E4_HD = new Channel("E4 HD", "http://www.e4.com/hd", "e4hd");
    public static final Channel ITV3_PLUS1 = new Channel("ITV3 +1", "http://www.itv.com/channels/itv3#plus1", "itv3plus1");
    public static final Channel MORE4_PLUS1 = new Channel("More4 +1", "http://www.channel4.com/more4#plus1", "more4plus1");
    public static final Channel ITV4_PLUS1 = new Channel("ITV1 +1", "http://www.itv.com/channels/itv1#plus1", "itv1plus1");
    public static final Channel CITV = new Channel("Children's ITV", "http://www.itv.com/channels/citv", "citv");
    public static final Channel FIVER_PLUS1 = new Channel("Fiver +1", "http://www.five.tv/channels/fiver#plus1", "fiverplus1");
    public static final Channel FIVE_USA_PLUS1 = new Channel("Fiver USA +1", "http://www.five.tv/channels/fiver-usa#plus1", "fiverusaplus1");
    public static final Channel THE_ADULT_CHANNEL = new Channel("The Adult Channel", "http://ref.atlasapi.org/channels/theadultchannel", "theadultchannel");
    public static final Channel MTV_HITS = new Channel("MTV Hits", "http://ref.atlasapi.org/channels/mtvhits", "mtvhits");
    public static final Channel MTV_BASE = new Channel("MTV Base", "http://ref.atlasapi.org/channels/mtvbase", "mtvbase");
    public static final Channel MTV = new Channel("MTV", "http://ref.atlasapi.org/channels/mtv", "mtv");
    public static final Channel MTV_PLUS1 = new Channel("MTV +1", "http://ref.atlasapi.org/channels/mtvplus1", "mtvplus1");
    public static final Channel TRAVELCHANNEL = new Channel("TRAVELCHANNEL", "http://ref.atlasapi.org/channels/travelchannel", "travelchannel");
    public static final Channel TRAVELCHANNEL_PLUS1 = new Channel("Travelchannel +1", "http://ref.atlasapi.org/channels/travelchannelplus1", "travelchannelplus1");
    public static final Channel TV5 = new Channel("TV5", "http://ref.atlasapi.org/channels/tv5", "tv5");
    public static final Channel ZEE_TV = new Channel("Zee TV", "http://ref.atlasapi.org/channels/zeetv", "zeetv");
    public static final Channel PHOENIX_CNE = new Channel("Phoenix Cne", "http://ref.atlasapi.org/channels/phoenixcne", "phoenixcne");
    public static final Channel CHALLENGE = new Channel("Challenge", "http://ref.atlasapi.org/channels/challenge", "challenge");
    public static final Channel CHALLENGE_PLUS1 = new Channel("Challenge +1", "http://ref.atlasapi.org/channels/challengeplus1", "challengeplus1");
    public static final Channel HOME = new Channel("HOME", "http://ref.atlasapi.org/channels/home", "home");
    public static final Channel HOME_PLUS1 = new Channel("Home +1", "http://ref.atlasapi.org/channels/homeplus1", "homeplus1");
    public static final Channel SKY_BOX_OFFICE_DIGITAL = new Channel("Sky Box Office Digital", "http://ref.atlasapi.org/channels/skyboxofficedigital", "skyboxofficedigital");
    public static final Channel BLOOMBERG_TV = new Channel("Bloomberg TV", "http://ref.atlasapi.org/channels/bloombergtv", "bloombergtv");
    public static final Channel THE_BOX = new Channel("The Box", "http://ref.atlasapi.org/channels/thebox", "thebox");
    public static final Channel CNN = new Channel("CNN", "http://ref.atlasapi.org/channels/cnn", "cnn");
    public static final Channel CARTOON_NETWORK = new Channel("Cartoon Network", "http://ref.atlasapi.org/channels/cartoonnetwork", "cartoonnetwork");
    public static final Channel GOD_CHANNEL = new Channel("God Channel", "http://ref.atlasapi.org/channels/godchannel", "godchannel");
    public static final Channel PLAYBOY_TV = new Channel("Playboy TV", "http://ref.atlasapi.org/channels/playboytv", "playboytv");
    public static final Channel ANIMAL_PLANET = new Channel("Animal Planet", "http://ref.atlasapi.org/channels/animalplanet", "animalplanet");
    public static final Channel ANIMAL_PLANET_PLUS1 = new Channel("Animal Planet +1", "http://ref.atlasapi.org/channels/animalplanetplus1", "animalplanetplus1");
    public static final Channel SKY_NEWS = new Channel("Sky News", "http://ref.atlasapi.org/channels/skynews", "skynews");
    public static final Channel SKY_NEWS_HD = new Channel("Sky News HD", "http://ref.atlasapi.org/channels/skynewshd", "skynewshd");
    public static final Channel MTV_ROCKS = new Channel("MTV Rocks", "http://ref.atlasapi.org/channels/mtvrocks", "mtvrocks");
    public static final Channel LIVING = new Channel("LIVING", "http://ref.atlasapi.org/channels/living", "living");
    public static final Channel LIVING_PLUS1 = new Channel("Living +1", "http://ref.atlasapi.org/channels/livingplus1", "livingplus1");
    public static final Channel LIVING_HD = new Channel("Living HD", "http://ref.atlasapi.org/channels/livinghd", "livinghd");
    public static final Channel BRAVO = new Channel("Bravo", "http://ref.atlasapi.org/channels/bravo", "bravo");
    public static final Channel BRAVO_PLUS1 = new Channel("Bravo +1", "http://ref.atlasapi.org/channels/bravoplus1", "bravoplus1");
    public static final Channel DISCOVERY_HISTORY = new Channel("Discovery History", "http://ref.atlasapi.org/channels/discoveryhistory", "discoveryhistory");
    public static final Channel DISCOVERY_HISTORY_PLUS_1 = new Channel("Discovery History +1", "http://ref.atlasapi.org/channels/discoveryhistoryplus1", "discoveryhistoryplus1");
    public static final Channel DISCOVERY_SCIENCE = new Channel("Discovery Science", "http://ref.atlasapi.org/channels/discoveryscience", "discoveryscience");
    public static final Channel DISCOVERY_SCIENCE_PLUS1 = new Channel("Discovery Science +1", "http://ref.atlasapi.org/channels/discoveryscienceplus1", "discoveryscienceplus1");
    public static final Channel DISCOVERY_TRAVEL_AND_LIVING = new Channel("Discovery Travel And Living", "http://ref.atlasapi.org/channels/discoverytravelandliving", "discoverytravelandliving");
    public static final Channel DISCOVERY_TRAVEL_AND_LIVING_PLUS1 = new Channel("Discovery Travel And Living +1", "http://ref.atlasapi.org/channels/discoverytravelandlivingplus1",
            "discoverytravelandlivingplus1");
    public static final Channel HISTORY = new Channel("History", "http://ref.atlasapi.org/channels/history", "history");
    public static final Channel HISTORY_PLUS1 = new Channel("History +1", "http://ref.atlasapi.org/channels/historyplus1", "historyplus1");
    public static final Channel NATIONAL_GEOGRAPHIC = new Channel("National Geographic", "http://ref.atlasapi.org/channels/nationalgeographic", "nationalgeographic");
    public static final Channel NATIONAL_GEOGRAPHIC_PLUS1 = new Channel("National Geographic +1", "http://ref.atlasapi.org/channels/nationalgeographicplus1", "nationalgeographicplus1");
    public static final Channel GOLD = new Channel("Gold", "http://ref.atlasapi.org/channels/gold", "gold");
    public static final Channel GOLD_PLUS1 = new Channel("Gold +1", "http://ref.atlasapi.org/channels/goldplus1", "goldplus1");
    public static final Channel THE_DISNEY_CHANNEL = new Channel("The Disney Channel", "http://ref.atlasapi.org/channels/thedisneychannel", "thedisneychannel");
    public static final Channel THE_DISNEY_CHANNEL_PLUS1 = new Channel("The Disney Channel +1", "http://ref.atlasapi.org/channels/thedisneychannelplus1", "thedisneychannelplus1");
    public static final Channel TELEVISION_X = new Channel("Television X", "http://ref.atlasapi.org/channels/televisionx", "televisionx");
    public static final Channel VH1 = new Channel("VH1", "http://ref.atlasapi.org/channels/vh1", "vh1");
    public static final Channel MTV_CLASSIC = new Channel("MTV Classic", "http://ref.atlasapi.org/channels/mtvclassic", "mtvclassic");
    public static final Channel DISCOVERY = new Channel("DISCOVERY", "http://ref.atlasapi.org/channels/discovery", "discovery");
    public static final Channel DISCOVERY_PLUS1 = new Channel("Discovery +1", "http://ref.atlasapi.org/channels/discoveryplus1", "discoveryplus1");
    public static final Channel DISCOVERY_PLUS1_POINT5 = new Channel("Discovery +1 Point5", "http://ref.atlasapi.org/channels/discoveryplus1point5", "discoveryplus1point5");
    public static final Channel DISCOVERY_TURBO = new Channel("Discovery Turbo", "http://ref.atlasapi.org/channels/discoveryturbo", "discoveryturbo");
    public static final Channel ALIBI = new Channel("Alibi", "http://ref.atlasapi.org/channels/alibi", "alibi");
    public static final Channel ALIBI_PLUS1 = new Channel("Alibi +1", "http://ref.atlasapi.org/channels/alibiplus1", "alibiplus1");
    public static final Channel UNIVERSAL = new Channel("Universal", "http://ref.atlasapi.org/channels/universal", "universal");
    public static final Channel UNIVERSAL_HD = new Channel("Universal HD", "http://ref.atlasapi.org/channels/universalhd", "universalhd");
    public static final Channel UNIVERSAL_PLUS1 = new Channel("Universal +1", "http://ref.atlasapi.org/channels/universalplus1", "universalplus1");
    public static final Channel SYFY = new Channel("SyFy", "http://ref.atlasapi.org/channels/syfy", "syfy");
    public static final Channel SYFY_PLUS1 = new Channel("SyFy +1", "http://ref.atlasapi.org/channels/syfyplus1", "syfyplus1");
    public static final Channel SYFY_HD = new Channel("SyFy HD", "http://ref.atlasapi.org/channels/syfyhd", "syfyhd");
    public static final Channel COMEDY_CENTRAL = new Channel("Comedy Central", "http://ref.atlasapi.org/channels/comedycentral", "comedycentral");
    public static final Channel COMEDY_CENTRAL_PLUS1 = new Channel("Comedy Central +1", "http://ref.atlasapi.org/channels/comedycentralplus1", "comedycentralplus1");
    public static final Channel SONY_ENTERTAINMENT_TV_ASIA = new Channel("Sony Entertainment TV Asia", "http://ref.atlasapi.org/channels/sonyentertainmenttvasia", "sonyentertainmenttvasia");
    public static final Channel TCM = new Channel("TCM", "http://ref.atlasapi.org/channels/tcm", "tcm");
    public static final Channel NICKELODEON = new Channel("Nickelodeon", "http://ref.atlasapi.org/channels/nickelodeon", "nickelodeon");
    public static final Channel NICKELODEON_REPLAY = new Channel("Nickelodeon Replay", "http://ref.atlasapi.org/channels/nickelodeonreplay", "nickelodeonreplay");
    public static final Channel CNBC = new Channel("CNBC", "http://ref.atlasapi.org/channels/cnbc", "cnbc");
    public static final Channel NICK_JR = new Channel("Nick Jr", "http://ref.atlasapi.org/channels/nickjr", "nickjr");
    public static final Channel BOOMERANG = new Channel("Boomerang", "http://ref.atlasapi.org/channels/boomerang", "boomerang");
    public static final Channel BOOMERANG_PLUS1 = new Channel("Boomerang +1", "http://ref.atlasapi.org/channels/boomerangplus1", "boomerangplus1");
    public static final Channel QVC = new Channel("QVC", "http://ref.atlasapi.org/channels/qvc", "qvc");
    public static final Channel GBC = new Channel("GBC", "http://ref.atlasapi.org/channels/gbc", "gbc");
    public static final Channel BBC_ENTERTAINMENT = new Channel("BBC Entertainment", "http://ref.atlasapi.org/channels/bbcentertainment", "bbcentertainment");
    public static final Channel FILM4_HD = new Channel("FILM4 HD", "http://ref.atlasapi.org/channels/film4hd", "film4hd");
    public static final Channel FILM4_PLUS1 = new Channel("FILM4 +1", "http://ref.atlasapi.org/channels/film4plus1", "film4plus1");
    public static final Channel SKY1 = new Channel("SKY1", "http://ref.atlasapi.org/channels/sky1", "sky1");
    public static final Channel DISCOVERY_HOME_AND_HEALTH = new Channel("Discovery Home And Health", "http://ref.atlasapi.org/channels/discoveryhomeandhealth", "discoveryhomeandhealth");
    public static final Channel DISCOVERY_HOME_AND_HEALTH_PLUS1 = new Channel("Discovery Home And Health +1", "http://ref.atlasapi.org/channels/discoveryhomeandhealthplus1",
            "discoveryhomeandhealthplus1");
    public static final Channel MUTV = new Channel("MUTV", "http://ref.atlasapi.org/channels/mutv", "mutv");
    public static final Channel SKY_SPORTS_NEWS = new Channel("Sky Sports News", "http://ref.atlasapi.org/channels/skysportsnews", "skysportsnews");
    public static final Channel EUROSPORT = new Channel("EUROSPORT", "http://ref.atlasapi.org/channels/eurosport", "eurosport");
    public static final Channel EUROSPORT_HD = new Channel("Eurosport HD", "http://ref.atlasapi.org/channels/eurosporthd", "eurosporthd");
    public static final Channel KISS = new Channel("KISS", "http://ref.atlasapi.org/channels/kiss", "kiss");
    public static final Channel PLAYHOUSE_DISNEY = new Channel("Playhouse Disney", "http://ref.atlasapi.org/channels/playhousedisney", "playhousedisney");
    public static final Channel PLAYHOUSE_DISNEY_PLUS = new Channel("Playhouse Disney Plus", "http://ref.atlasapi.org/channels/playhousedisneyplus", "playhousedisneyplus");
    public static final Channel SKY_SPORTS_1 = new Channel("Sky Sports 1", "http://ref.atlasapi.org/channels/skysports1", "skysports1");
    public static final Channel SKY_SPORTS_2 = new Channel("Sky Sports 2", "http://ref.atlasapi.org/channels/skysports2", "skysports2");
    public static final Channel SKY_SPORTS_3 = new Channel("Sky Sports 3", "http://ref.atlasapi.org/channels/skysports3", "skysports3");
    public static final Channel SKY_SPORTS_4 = new Channel("Sky Sports 4", "http://ref.atlasapi.org/channels/skysports4", "skysports4");
    public static final Channel BIO = new Channel("BIO", "http://ref.atlasapi.org/channels/bio", "bio");
    public static final Channel BID_TV = new Channel("Bid TV", "http://ref.atlasapi.org/channels/bidtv", "bidtv");
    public static final Channel SKY_ARTS_1 = new Channel("Sky Arts 1", "http://ref.atlasapi.org/channels/skyarts1", "skyarts1");
    public static final Channel EURONEWS = new Channel("EURONEWS", "http://ref.atlasapi.org/channels/euronews", "euronews");
    public static final Channel B4U_MOVIES = new Channel("B4U Movies", "http://ref.atlasapi.org/channels/b4umovies", "b4umovies");
    public static final Channel RED_HOT_AMATEUR = new Channel("Red Hot Amateur", "http://ref.atlasapi.org/channels/redhotamateur", "redhotamateur");
    public static final Channel EXTREME_SPORTS = new Channel("Extreme Sports", "http://ref.atlasapi.org/channels/extremesports", "extremesports");
    public static final Channel MTV_DANCE = new Channel("MTV Dance", "http://ref.atlasapi.org/channels/mtvdance", "mtvdance");
    public static final Channel STAR_PLUS = new Channel("Star Plus", "http://ref.atlasapi.org/channels/starplus", "starplus");
    public static final Channel TELEG = new Channel("TELEG", "http://ref.atlasapi.org/channels/teleg", "teleg");
    public static final Channel CHANNEL_9 = new Channel("Channel 9", "http://ref.atlasapi.org/channels/channel9", "channel9");
    public static final Channel GOOD_FOOD = new Channel("Good Food", "http://ref.atlasapi.org/channels/goodfood", "goodfood");
    public static final Channel GOOD_FOOD_PLUS1 = new Channel("Good Food +1", "http://ref.atlasapi.org/channels/goodfoodplus1", "goodfoodplus1");
    public static final Channel ATTHERACES = new Channel("ATTHERACES", "http://ref.atlasapi.org/channels/attheraces", "attheraces");
    public static final Channel NICKTOONS_TV = new Channel("Nicktoons TV", "http://ref.atlasapi.org/channels/nicktoonstv", "nicktoonstv");
    public static final Channel NICKTOONS_REPLAY = new Channel("Nicktoons Replay", "http://ref.atlasapi.org/channels/nicktoonsreplay", "nicktoonsreplay");
    public static final Channel CHART_SHOW_TV = new Channel("Chart Show TV", "http://ref.atlasapi.org/channels/chartshowtv", "chartshowtv");
    public static final Channel YESTERDAY = new Channel("YESTERDAY", "http://ref.atlasapi.org/channels/yesterday", "yesterday");
    public static final Channel YESTERDAY_PLUS1 = new Channel("Yesterday +1", "http://ref.atlasapi.org/channels/yesterdayplus1", "yesterdayplus1");
    public static final Channel MAGIC = new Channel("MAGIC", "http://ref.atlasapi.org/channels/magic", "magic");
    public static final Channel SMASH_HITS = new Channel("Smash Hits", "http://ref.atlasapi.org/channels/smashhits", "smashhits");
    public static final Channel KERRANG = new Channel("KERRANG", "http://ref.atlasapi.org/channels/kerrang", "kerrang");
    public static final Channel THE_COMMUNITY_CHANNEL = new Channel("The Community Channel", "http://ref.atlasapi.org/channels/thecommunitychannel", "thecommunitychannel");
    public static final Channel SKY2 = new Channel("SKY2", "http://ref.atlasapi.org/channels/sky2", "sky2");
    public static final Channel E_EXLAMATION = new Channel("E Exlamation", "http://ref.atlasapi.org/channels/eexlamation", "eexlamation");
    public static final Channel FLAUNT = new Channel("FLAUNT", "http://ref.atlasapi.org/channels/flaunt", "flaunt");
    public static final Channel SCUZZ = new Channel("SCUZZ", "http://ref.atlasapi.org/channels/scuzz", "scuzz");
    public static final Channel BLISS = new Channel("BLISS", "http://ref.atlasapi.org/channels/bliss", "bliss");
    public static final Channel ESPN_AMERICA = new Channel("Espn America", "http://ref.atlasapi.org/channels/espnamerica", "espnamerica");
    public static final Channel ESPN_AMERICA_HD = new Channel("Espn America HD", "http://ref.atlasapi.org/channels/espnamericahd", "espnamericahd");
    public static final Channel RED_HOT_40 = new Channel("Red Hot 40", "http://ref.atlasapi.org/channels/redhot40", "redhot40");
    public static final Channel DAVE = new Channel("DAVE", "http://ref.atlasapi.org/channels/dave", "dave");
    public static final Channel DAVE_JA_VU = new Channel("Dave Ja Vu", "http://ref.atlasapi.org/channels/davejavu", "davejavu");
    public static final Channel PRICE_DROP_TV = new Channel("Price Drop TV", "http://ref.atlasapi.org/channels/pricedroptv", "pricedroptv");
    public static final Channel FX = new Channel("FX", "http://ref.atlasapi.org/channels/fx", "fx");
    public static final Channel FX_PLUS = new Channel("Fx Plus", "http://ref.atlasapi.org/channels/fxplus", "fxplus");
    public static final Channel EDEN = new Channel("EDEN", "http://ref.atlasapi.org/channels/eden", "eden");
    public static final Channel EDEN_PLUS1 = new Channel("Eden +1", "http://ref.atlasapi.org/channels/edenplus1", "edenplus1");
    public static final Channel EDEN_HD = new Channel("Eden HD", "http://ref.atlasapi.org/channels/edenhd", "edenhd");
    public static final Channel BLIGHTY = new Channel("BLIGHTY", "http://ref.atlasapi.org/channels/blighty", "blighty");
    public static final Channel THE_HORROR_CHANNEL = new Channel("The Horror Channel", "http://ref.atlasapi.org/channels/thehorrorchannel", "thehorrorchannel");
    public static final Channel THE_HORROR_CHANNEL_PLUS1 = new Channel("The Horror Channel +1", "http://ref.atlasapi.org/channels/thehorrorchannelplus1", "thehorrorchannelplus1");
    public static final Channel RACING_UK = new Channel("Racing Uk", "http://ref.atlasapi.org/channels/racinguk", "racinguk");
    public static final Channel CHELSEA_TV = new Channel("Chelsea TV", "http://ref.atlasapi.org/channels/chelseatv", "chelseatv");
    public static final Channel STAR_NEWS = new Channel("Star News", "http://ref.atlasapi.org/channels/starnews", "starnews");
    public static final Channel SETANTA_IRELAND = new Channel("Setanta Ireland", "http://ref.atlasapi.org/channels/setantaireland", "setantaireland");
    public static final Channel LIVINGIT = new Channel("LIVINGIT", "http://ref.atlasapi.org/channels/livingit", "livingit");
    public static final Channel LIVINGIT_PLUS1 = new Channel("Livingit +1", "http://ref.atlasapi.org/channels/livingitplus1", "livingitplus1");
    public static final Channel EUROSPORT_2 = new Channel("Eurosport 2", "http://ref.atlasapi.org/channels/eurosport2", "eurosport2");
    public static final Channel REALLY = new Channel("REALLY", "http://ref.atlasapi.org/channels/really", "really");
    public static final Channel RED_HOT_TV = new Channel("Red Hot TV", "http://ref.atlasapi.org/channels/redhottv", "redhottv");
    public static final Channel COMEDY_CENTRAL_EXTRA = new Channel("Comedy Central Extra", "http://ref.atlasapi.org/channels/comedycentralextra", "comedycentralextra");
    public static final Channel COMEDY_CENTRAL_EXTRA_PLUS1 = new Channel("Comedy Central Extra +1", "http://ref.atlasapi.org/channels/comedycentralextraplus1", "comedycentralextraplus1");
    public static final Channel COMEDY_CENTRAL_HD = new Channel("Comedy Central HD", "http://ref.atlasapi.org/channels/comedycentralhd", "comedycentralhd");
    public static final Channel M95_TV_MARBELLA = new Channel("M95 TV Marbella", "http://ref.atlasapi.org/channels/m95tvmarbella", "m95tvmarbella");
    public static final Channel TV3_SPANISH = new Channel("TV3 Spanish", "http://ref.atlasapi.org/channels/tv3spanish", "tv3spanish");
    public static final Channel DISCOVERY_REAL_TIME = new Channel("Discovery Real Time", "http://ref.atlasapi.org/channels/discoveryrealtime", "discoveryrealtime");
    public static final Channel DISCOVERY_REAL_TIME_PLUS1 = new Channel("Discovery Real Time +1", "http://ref.atlasapi.org/channels/discoveryrealtimeplus1", "discoveryrealtimeplus1");
    public static final Channel MOTORS_TV = new Channel("Motors TV", "http://ref.atlasapi.org/channels/motorstv", "motorstv");
    public static final Channel DISCOVERY_SHED = new Channel("Discovery Shed", "http://ref.atlasapi.org/channels/discoveryshed", "discoveryshed");
    public static final Channel TRUE_MOVIES = new Channel("True Movies", "http://ref.atlasapi.org/channels/truemovies", "truemovies");
    public static final Channel SKY3 = new Channel("SKY3", "http://ref.atlasapi.org/channels/sky3", "sky3");
    public static final Channel SKY_3_PLUS1 = new Channel("Sky 3 +1", "http://ref.atlasapi.org/channels/sky3plus1", "sky3plus1");
    public static final Channel DISNEY_CINEMAGIC = new Channel("Disney Cinemagic", "http://ref.atlasapi.org/channels/disneycinemagic", "disneycinemagic");
    public static final Channel DISNEY_CINEMAGIC_PLUS1 = new Channel("Disney Cinemagic +1", "http://ref.atlasapi.org/channels/disneycinemagicplus1", "disneycinemagicplus1");
    public static final Channel DISNEY_CINEMAGIC_HD = new Channel("Disney Cinemagic HD", "http://ref.atlasapi.org/channels/disneycinemagichd", "disneycinemagichd");
    public static final Channel ESPN_CLASSIC = new Channel("Espn Classic", "http://ref.atlasapi.org/channels/espnclassic", "espnclassic");
    public static final Channel THREE_E = new Channel("Three E", "http://ref.atlasapi.org/channels/threee", "threee");
    public static final Channel FILMFLEX = new Channel("FILMFLEX", "http://ref.atlasapi.org/channels/filmflex", "filmflex");
    public static final Channel TCM2 = new Channel("TCM2", "http://ref.atlasapi.org/channels/tcm2", "tcm2");
    public static final Channel CHRISTMAS24 = new Channel("CHRISTMAS24", "http://ref.atlasapi.org/channels/christmas24", "christmas24");
    public static final Channel CHRISTMAS24_PLUS = new Channel("CHRISTMAS24 Plus", "http://ref.atlasapi.org/channels/christmas24plus", "christmas24plus");
    public static final Channel DISCOVERY_HD = new Channel("Discovery HD", "http://ref.atlasapi.org/channels/discoveryhd", "discoveryhd");
    public static final Channel NATIONAL_GEOGRAPHIC_HD = new Channel("National Geographic HD", "http://ref.atlasapi.org/channels/nationalgeographichd", "nationalgeographichd");
    public static final Channel SKY_BOX_OFFICE_HD1 = new Channel("Sky Box Office HD1", "http://ref.atlasapi.org/channels/skyboxofficehd1", "skyboxofficehd1");
    public static final Channel SKY_BOX_OFFICE_HD2 = new Channel("Sky Box Office HD2", "http://ref.atlasapi.org/channels/skyboxofficehd2", "skyboxofficehd2");
    public static final Channel SKY_SPORTS_1_HD = new Channel("Sky Sports 1 HD", "http://ref.atlasapi.org/channels/skysports1hd", "skysports1hd");
    public static final Channel SKY_SPORTS_2_HD = new Channel("Sky Sports 2 HD", "http://ref.atlasapi.org/channels/skysports2hd", "skysports2hd");
    public static final Channel E_EUROPE = new Channel("E Europe", "http://ref.atlasapi.org/channels/eeurope", "eeurope");
    public static final Channel NAT_GEO_WILD = new Channel("Nat Geo Wild", "http://ref.atlasapi.org/channels/natgeowild", "natgeowild");
    public static final Channel CRIME_AND_INVESTIGATION = new Channel("Crime And Investigation", "http://ref.atlasapi.org/channels/crimeandinvestigation", "crimeandinvestigation");
    public static final Channel CRIME_AND_INVESTIGATION_PLUS1 = new Channel("Crime And Investigation +1", "http://ref.atlasapi.org/channels/crimeandinvestigationplus1", "crimeandinvestigationplus1");
    public static final Channel Channel = new Channel("Channel", "http://ref.atlasapi.org/channels/channel", "channel");
    public static final Channel Channel_HD = new Channel("Channel HD", "http://ref.atlasapi.org/channels/channelhd", "channelhd");
    public static final Channel SKY_MOVIES_CRIME_AND_THRILLER = new Channel("Sky Movies Crime And Thriller", "http://ref.atlasapi.org/channels/skymoviescrimeandthriller", "skymoviescrimeandthriller");
    public static final Channel SKY_MOVIES_CRIME_AND_THRILLER_HD = new Channel("Sky Movies Crime And Thriller HD", "http://ref.atlasapi.org/channels/skymoviescrimeandthrillerhd",
            "skymoviescrimeandthrillerhd");
    public static final Channel SKY_MOVIES_PREMIERE = new Channel("Sky Movies Premiere", "http://ref.atlasapi.org/channels/skymoviespremiere", "skymoviespremiere");
    public static final Channel SKY_MOVIES_PREMIERE_PLUS1 = new Channel("Sky Movies Premiere +1", "http://ref.atlasapi.org/channels/skymoviespremiereplus1", "skymoviespremiereplus1");
    public static final Channel SKY_MOVIES_PREMIERE_HD = new Channel("Sky Movies Premiere HD", "http://ref.atlasapi.org/channels/skymoviespremierehd", "skymoviespremierehd");
    public static final Channel SKY_MOVIES_COMEDY = new Channel("Sky Movies Comedy", "http://ref.atlasapi.org/channels/skymoviescomedy", "skymoviescomedy");
    public static final Channel SKY_MOVIES_COMEDY_HD = new Channel("Sky Movies Comedy HD", "http://ref.atlasapi.org/channels/skymoviescomedyhd", "skymoviescomedyhd");
    public static final Channel SKY_MOVIES_ACTION_AND_ADVENTURE = new Channel("Sky Movies Action And Adventure", "http://ref.atlasapi.org/channels/skymoviesactionandadventure",
            "skymoviesactionandadventure");
    public static final Channel SKY_MOVIES_ACTION_AND_ADVENTURE_HD = new Channel("Sky Movies Action And Adventure HD", "http://ref.atlasapi.org/channels/skymoviesactionandadventurehd",
            "skymoviesactionandadventurehd");
    public static final Channel SKY_MOVIES_FAMILY = new Channel("Sky Movies Family", "http://ref.atlasapi.org/channels/skymoviesfamily", "skymoviesfamily");
    public static final Channel SKY_MOVIES_FAMILY_HD = new Channel("Sky Movies Family HD", "http://ref.atlasapi.org/channels/skymoviesfamilyhd", "skymoviesfamilyhd");
    public static final Channel SKY_MOVIES_DRAMA_AND_ROMANCE = new Channel("Sky Movies Drama And Romance", "http://ref.atlasapi.org/channels/skymoviesdramaandromance", "skymoviesdramaandromance");
    public static final Channel SKY_MOVIES_DRAMA_AND_ROMANCE_HD = new Channel("Sky Movies Drama And Romance HD", "http://ref.atlasapi.org/channels/skymoviesdramaandromancehd",
            "skymoviesdramaandromancehd");
    public static final Channel SKY_MOVIES_SCIFI_HORROR = new Channel("Sky Movies Scifi Horror", "http://ref.atlasapi.org/channels/skymoviesscifihorror", "skymoviesscifihorror");
    public static final Channel SKY_MOVIES_SCIFI_HORROR_HD = new Channel("Sky Movies Scifi Horror HD", "http://ref.atlasapi.org/channels/skymoviesscifihorrorhd", "skymoviesscifihorrorhd");
    public static final Channel SKY_MOVIES_CLASSICS = new Channel("Sky Movies Classics", "http://ref.atlasapi.org/channels/skymoviesclassics", "skymoviesclassics");
    public static final Channel SKY_MOVIES_CLASSICS_HD = new Channel("Sky Movies Classics HD", "http://ref.atlasapi.org/channels/skymoviesclassicshd", "skymoviesclassicshd");
    public static final Channel SKY_MOVIES_MODERN_GREATS = new Channel("Sky Movies Modern Greats", "http://ref.atlasapi.org/channels/skymoviesmoderngreats", "skymoviesmoderngreats");
    public static final Channel SKY_MOVIES_MODERN_GREATS_HD = new Channel("Sky Movies Modern Greats HD", "http://ref.atlasapi.org/channels/skymoviesmoderngreatshd", "skymoviesmoderngreatshd");
    public static final Channel SKY_MOVIES_INDIE = new Channel("Sky Movies Indie", "http://ref.atlasapi.org/channels/skymoviesindie", "skymoviesindie");
    public static final Channel SKY_MOVIES_INDIE_HD = new Channel("Sky Movies Indie HD", "http://ref.atlasapi.org/channels/skymoviesindiehd", "skymoviesindiehd");
    public static final Channel AL_JAZEERA_ENGLISH = new Channel("Al Jazeera English", "http://ref.atlasapi.org/channels/aljazeeraenglish", "aljazeeraenglish");
    public static final Channel HISTORY_HD = new Channel("History HD", "http://ref.atlasapi.org/channels/historyhd", "historyhd");
    public static final Channel SKY1_HD = new Channel("SKY1 HD", "http://ref.atlasapi.org/channels/sky1hd", "sky1hd");
    public static final Channel SKY_ARTS_1_HD = new Channel("Sky Arts 1 HD", "http://ref.atlasapi.org/channels/skyarts1hd", "skyarts1hd");
    public static final Channel CARTOONITO = new Channel("CARTOONITO", "http://ref.atlasapi.org/channels/cartoonito", "cartoonito");
    public static final Channel BEST_DIRECT = new Channel("Best Direct", "http://ref.atlasapi.org/channels/bestdirect", "bestdirect");
    public static final Channel BRAVO_2 = new Channel("Bravo 2", "http://ref.atlasapi.org/channels/bravo2", "bravo2");
    public static final Channel DEUTSCHE_WELLE = new Channel("Deutsche Welle", "http://ref.atlasapi.org/channels/deutschewelle", "deutschewelle");
    public static final Channel GEMS_TV = new Channel("Gems TV", "http://ref.atlasapi.org/channels/gemstv", "gemstv");
    public static final Channel GEM_COLLECTOR = new Channel("Gem Collector", "http://ref.atlasapi.org/channels/gemcollector", "gemcollector");
    public static final Channel Channel_S = new Channel("Channel S", "http://ref.atlasapi.org/channels/channels", "channels");
    public static final Channel SETANTA_SPORTS_1_IRELAND = new Channel("Setanta Sports 1 Ireland", "http://ref.atlasapi.org/channels/setantasports1ireland", "setantasports1ireland");
    public static final Channel DIVA = new Channel("Diva", "http://ref.atlasapi.org/channels/diva", "diva");
    public static final Channel DIVA_PLUS1 = new Channel("Diva +1", "http://ref.atlasapi.org/channels/divaplus1", "divaplus1");
    public static final Channel Channel_ONE = new Channel("Channel One", "http://ref.atlasapi.org/channels/channelone", "channelone");
    public static final Channel Channel_ONE_PLUS1 = new Channel("Channel One +1", "http://ref.atlasapi.org/channels/channeloneplus1", "channeloneplus1");
    public static final Channel CN_TOO = new Channel("CN Too", "http://ref.atlasapi.org/channels/cntoo", "cntoo");
    public static final Channel POP = new Channel("POP", "http://ref.atlasapi.org/channels/pop", "pop");
    public static final Channel TINY_POP = new Channel("Tiny Pop", "http://ref.atlasapi.org/channels/tinypop", "tinypop");
    public static final Channel DMAX = new Channel("DMAX", "http://ref.atlasapi.org/channels/dmax", "dmax");
    public static final Channel DMAX_PLUS1 = new Channel("DMAX +1", "http://ref.atlasapi.org/channels/dmaxplus1", "dmaxplus1");
    public static final Channel DMAX_2 = new Channel("DMAX 2", "http://ref.atlasapi.org/channels/dmax2", "dmax2");
    public static final Channel HORSE_AND_COUNTRY = new Channel("Horse And Country", "http://ref.atlasapi.org/channels/horseandcountry", "horseandcountry");
    public static final Channel Channel_7 = new Channel("Channel 7", "http://ref.atlasapi.org/channels/channel7", "channel7");
    public static final Channel SKY_SPORTS_3_HD = new Channel("Sky Sports 3 HD", "http://ref.atlasapi.org/channels/skysports3hd", "skysports3hd");
    public static final Channel FLAVA = new Channel("Flava", "http://ref.atlasapi.org/channels/flava", "flava");
    public static final Channel FX_HD = new Channel("Fx HD", "http://ref.atlasapi.org/channels/fxhd", "fxhd");
    public static final Channel NATIONAL_GEOGRAPHIC_HD_PAN_EUROPEAN = new Channel("National Geographic HD Pan European", "http://ref.atlasapi.org/channels/nationalgeographichdpaneuropean",
            "nationalgeographichdpaneuropean");
    public static final Channel MOVIES4MEN = new Channel("MOVIES4MEN", "http://ref.atlasapi.org/channels/movies4men", "movies4men");
    public static final Channel MOVIES4MEN_PLUS1 = new Channel("MOVIES4Men +1", "http://ref.atlasapi.org/channels/movies4menplus1", "movies4menplus1");
    public static final Channel TRUE_MOVIES_2 = new Channel("True Movies 2", "http://ref.atlasapi.org/channels/truemovies2", "truemovies2");
    public static final Channel MOVIES4MEN2 = new Channel("MOVIES4MEN2", "http://ref.atlasapi.org/channels/movies4men2", "movies4men2");
    public static final Channel MOVIES4MEN2_PLUS1 = new Channel("MOVIES4MEN2 +1", "http://ref.atlasapi.org/channels/movies4men2plus1", "movies4men2plus1");
    public static final Channel MILITARY_HISTORY = new Channel("Military History", "http://ref.atlasapi.org/channels/militaryhistory", "militaryhistory");
    public static final Channel THE_STYLE_NETWORK = new Channel("The Style Network", "http://ref.atlasapi.org/channels/thestylenetwork", "thestylenetwork");
    public static final Channel WATCH = new Channel("Watch", "http://ref.atlasapi.org/channels/watch", "watch");
    public static final Channel WATCH_PLUS1 = new Channel("Watch +1", "http://ref.atlasapi.org/channels/watchplus1", "watchplus1");
    public static final Channel SKY_ARTS_2 = new Channel("Sky Arts 2", "http://ref.atlasapi.org/channels/skyarts2", "skyarts2");
    public static final Channel WEDDING_TV = new Channel("Wedding TV", "http://ref.atlasapi.org/channels/weddingtv", "weddingtv");
    public static final Channel PROPELLER_TV = new Channel("Propeller TV", "http://ref.atlasapi.org/channels/propellertv", "propellertv");
    public static final Channel MTVN_HD = new Channel("MTVn HD", "http://ref.atlasapi.org/channels/mtvnhd", "mtvnhd");
    public static final Channel INVESTIGATION_DISCOVERY = new Channel("Investigation Discovery", "http://ref.atlasapi.org/channels/investigationdiscovery", "investigationdiscovery");
    public static final Channel AIT_INTERNATIONAL = new Channel("Ait International", "http://ref.atlasapi.org/channels/aitinternational", "aitinternational");
    public static final Channel CINEMOI = new Channel("CINEMOI", "http://ref.atlasapi.org/channels/cinemoi", "cinemoi");
    public static final Channel SKY_ARTS_2_HD = new Channel("Sky Arts 2 HD", "http://ref.atlasapi.org/channels/skyarts2hd", "skyarts2hd");
    public static final Channel BIO_HD = new Channel("Bio HD", "http://ref.atlasapi.org/channels/biohd", "biohd");
    public static final Channel CRIME_AND_INVESTIGATION_HD = new Channel("Crime And Investigation HD", "http://ref.atlasapi.org/channels/crimeandinvestigationhd", "crimeandinvestigationhd");
    public static final Channel NAT_GEO_WILD_HD = new Channel("Nat Geo Wild HD", "http://ref.atlasapi.org/channels/natgeowildhd", "natgeowildhd");
    public static final Channel QUEST = new Channel("QUEST", "http://ref.atlasapi.org/channels/quest", "quest");
    public static final Channel DISCOVERY_QUEST_PLUS1 = new Channel("Discovery Quest +1", "http://ref.atlasapi.org/channels/discoveryquestplus1", "discoveryquestplus1");
    public static final Channel QUEST_FREEVIEW = new Channel("Quest Freeview", "http://ref.atlasapi.org/channels/questfreeview", "questfreeview");
    public static final Channel ESPN = new Channel("ESPN", "http://ref.atlasapi.org/channels/espn", "espn");
    public static final Channel ESPN_HD = new Channel("ESPN HD", "http://ref.atlasapi.org/channels/espnhd", "espnhd");
    public static final Channel DISNEY_XD = new Channel("Disney Xd", "http://ref.atlasapi.org/channels/disneyxd", "disneyxd");
    public static final Channel DISNEY_XD_PLUS1 = new Channel("Disney Xd +1", "http://ref.atlasapi.org/channels/disneyxdplus1", "disneyxdplus1");
    public static final Channel DISNEY_XD_HD = new Channel("Disney Xd HD", "http://ref.atlasapi.org/channels/disneyxdhd", "disneyxdhd");
    public static final Channel CBS_REALITY = new Channel("CBS Reality", "http://ref.atlasapi.org/channels/cbsreality", "cbsreality");
    public static final Channel MGM = new Channel("MGM", "http://ref.atlasapi.org/channels/mgm", "mgm");
    public static final Channel CBS_DRAMA = new Channel("CBS Drama", "http://ref.atlasapi.org/channels/cbsdrama", "cbsdrama");
    public static final Channel CBS_ACTION = new Channel("CBS Action", "http://ref.atlasapi.org/channels/cbsaction", "cbsaction");
    public static final Channel VIVA = new Channel("VIVA", "http://ref.atlasapi.org/channels/viva", "viva");
    public static final Channel FOOD_NETWORK = new Channel("Food Network", "http://ref.atlasapi.org/channels/foodnetwork", "foodnetwork");
    public static final Channel FOOD_NETWORK_PLUS1 = new Channel("Food Network +1", "http://ref.atlasapi.org/channels/foodnetworkplus1", "foodnetworkplus1");
    public static final Channel MGM_HD = new Channel("Mgm HD", "http://ref.atlasapi.org/channels/mgmhd", "mgmhd");
    public static final Channel MTV_SHOWS = new Channel("MTV Shows", "http://ref.atlasapi.org/channels/mtvshows", "mtvshows");
    public static final Channel NICK_JR_2 = new Channel("Nick Jr 2", "http://ref.atlasapi.org/channels/nickjr2", "nickjr2");
    public static final Channel NHK_WORLD = new Channel("NHK World", "http://ref.atlasapi.org/channels/nhkworld", "nhkworld");
    public static final Channel TRUEENT = new Channel("TRUEENT", "http://ref.atlasapi.org/channels/trueent", "trueent");
    public static final Channel BODY_IN_BALANCE = new Channel("Body In Balance", "http://ref.atlasapi.org/channels/bodyinbalance", "bodyinbalance");
    public static final Channel THE_ACTIVE_CHANNEL = new Channel("The Active Channel", "http://ref.atlasapi.org/channels/theactivechannel", "theactivechannel");
    public static final Channel FITNESS_TV = new Channel("Fitness TV", "http://ref.atlasapi.org/channels/fitnesstv", "fitnesstv");
    public static final Channel RUSH_HD = new Channel("Rush HD", "http://ref.atlasapi.org/channels/rushhd", "rushhd");
    public static final Channel BBC_SPORT_INTERACTIVE_BBC_ONE = new Channel("BBC Sport Interactive BBC One", "http://ref.atlasapi.org/channels/bbcsportinteractivebbcone", "bbcsportinteractivebbcone");
    public static final Channel BBC_SPORT_INTERACTIVE_FREEVIEW = new Channel("BBC Sport Interactive Freeview", "http://ref.atlasapi.org/channels/bbcsportinteractivefreeview",
            "bbcsportinteractivefreeview");
    public static final Channel SKY_3D = new Channel("Sky 3D", "http://ref.atlasapi.org/channels/sky3d", "sky3d");
    public static final Channel SKY_SPORTS_4_HD = new Channel("Sky Sports 4 HD", "http://ref.atlasapi.org/channels/skysports4hd", "skysports4hd");

    private final String uri;
    private final String title;

    private final static List<Channel> VOD_SERVICES = ImmutableList.of(BBC_IPLAYER, HULU, C4_4OD, YOUTUBE, SEESAW);
    private final String key;
    
    private final static Map<String, Channel> uriMap;
    private final static Map<String, Channel> keyMap;
    
    static {
        ImmutableMap.Builder<String, Channel> uriMapBuilder = ImmutableMap.builder();
        ImmutableMap.Builder<String, Channel> keyMapBuilder = ImmutableMap.builder();
        for (Field field: Channel.getClass().getFields()) {
            if (isPublicStaticFinal(field)) {
                try {
                    Object object = field.get(null);
                    if (object instanceof Channel) {
                        Channel channel = (Channel) object;
                        uriMapBuilder.put(channel.uri(), channel);
                        keyMapBuilder.put(channel.key(), channel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        uriMap = uriMapBuilder.build();
        keyMap = keyMapBuilder.build();
    }

    public Channel(String title, String uri, String key) {
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
        return Maybe.fromPossibleNullValue(uriMap.get(uri));
    }

    public static Maybe<Channel> fromKey(String key) {
        return Maybe.fromPossibleNullValue(keyMap.get(key));
    }

    public static List<Channel> fromKeys(Iterable<String> keys) {
        Builder<Channel> builder = ImmutableList.builder();

        for (String key : keys) {
            Maybe<Channel> channel = fromKey(key);
            if (channel.hasValue()) {
                builder.add(channel.requireValue());
            }
        }

        return builder.build();
    }

    public static List<String> toKeys(Iterable<Channel> channels) {
        Builder<String> keys = ImmutableList.builder();

        for (Channel channel : channels) {
            keys.add(channel.key());
        }

        return keys.build();
    }

    public static List<Map<String, ?>> mapList() {
        List<Map<String, ?>> channelList = Lists.newArrayList();
        for (Channel channel : keyMap.values()) {
            channelList.add(channel.toSimpleModel().asMap());
        }
        return channelList;
    }

    public static List<Map<String, ?>> mapListWithoutVodServices() {
        List<Map<String, ?>> channelList = Lists.newArrayList();
        for (Channel channel : keyMap.values()) {
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
        model.put("key", key);

        return model;
    }
    
    private static boolean isPublicStaticFinal(Field field) {
        int modifiers = field.getModifiers();
        return (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
    }
}
