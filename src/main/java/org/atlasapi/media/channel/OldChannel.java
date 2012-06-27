package org.atlasapi.media.channel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.model.SelfModelling;
import com.metabroadcast.common.model.SimpleModel;

public class OldChannel extends Channel implements SelfModelling {
    // Change this and you have to rebuild the whole schedule index (if you still want to be able to do range queries
    public static final int MAX_KEY_LENGTH = 31;

    private static final String CHANNEL_URI_PREFIX = "http://ref.atlasapi.org/channels/";
    public static final OldChannel BBC_IPLAYER = new OldChannel("iPlayer", "http://www.bbc.co.uk/iplayer", "iplayer");
    public static final OldChannel HULU = new OldChannel("Hulu", "http://www.hulu.com", "hulu");
    public static final OldChannel YOUTUBE = new OldChannel("YouTube", "http://www.youtube.com", "youtube");
    public static final OldChannel SEESAW = new OldChannel("Seesaw", "http://www.seesaw.com", "seesaw");
    public static final OldChannel C4_4OD = new OldChannel("4oD", "http://www.channel4.com/programmes/4od", "4od");
    public static final OldChannel BBC_ONE = new OldChannel("BBC One", "http://www.bbc.co.uk/services/bbcone/london", "bbcone");
    public static final OldChannel BBC_ONE_NORTHERN_IRELAND = new OldChannel("BBC One Northern Ireland", "http://www.bbc.co.uk/services/bbcone/ni", "bbcone-ni");
    public static final OldChannel BBC_ONE_CAMBRIDGE = new OldChannel("BBC One Cambridgeshire", "http://www.bbc.co.uk/services/bbcone/cambridge", "bbcone-cambridge");
    public static final OldChannel BBC_ONE_CHANNEL_ISLANDS = new OldChannel("BBC One Channel Islands", "http://www.bbc.co.uk/services/bbcone/channel_islands", "bbcone-channel_islands");
    public static final OldChannel BBC_ONE_EAST = new OldChannel("BBC One East", "http://www.bbc.co.uk/services/bbcone/east", "bbcone-east");
    public static final OldChannel BBC_ONE_EAST_MIDLANDS = new OldChannel("BBC One East Midlands", "http://www.bbc.co.uk/services/bbcone/east_midlands", "bbcone-east_midlands");
    public static final OldChannel BBC_ONE_HD = new OldChannel("BBC One HD", "http://www.bbc.co.uk/services/bbcone/hd", "bbcone-hd");
    public static final OldChannel BBC_ONE_NORTH_EAST = new OldChannel("BBC One North East & Cumbria", "http://www.bbc.co.uk/services/bbcone/north_east", "bbcone-north_east");
    public static final OldChannel BBC_ONE_NORTH_WEST = new OldChannel("BBC One North West", "http://www.bbc.co.uk/services/bbcone/north_west", "bbcone-north_west");
    public static final OldChannel BBC_ONE_OXFORD = new OldChannel("BBC One Oxfordshire", "http://www.bbc.co.uk/services/bbcone/oxford", "bbcone-oxford");
    public static final OldChannel BBC_ONE_SCOTLAND = new OldChannel("BBC One Scotland", "http://www.bbc.co.uk/services/bbcone/scotland", "bbcone-scotland");
    public static final OldChannel BBC_ONE_SOUTH = new OldChannel("BBC One South", "http://www.bbc.co.uk/services/bbcone/south", "bbcone-south");
    public static final OldChannel BBC_ONE_SOUTH_EAST = new OldChannel("BBC One South East", "http://www.bbc.co.uk/services/bbcone/south_east", "bbcone-south_east");
    public static final OldChannel BBC_ONE_WALES = new OldChannel("BBC One Wales", "http://www.bbc.co.uk/services/bbcone/wales", "bbcone-wales");
    public static final OldChannel BBC_ONE_SOUTH_WEST = new OldChannel("BBC One South West", "http://www.bbc.co.uk/services/bbcone/south_west", "bbcone-south_west");
    public static final OldChannel BBC_ONE_WEST = new OldChannel("BBC One West", "http://www.bbc.co.uk/services/bbcone/west", "bbcone-west");
    public static final OldChannel BBC_ONE_WEST_MIDLANDS = new OldChannel("BBC One West Midlands", "http://www.bbc.co.uk/services/bbcone/west_midlands", "bbcone-west_midlands");
    public static final OldChannel BBC_ONE_EAST_YORKSHIRE = new OldChannel("BBC One Yorks & Lincs", "http://www.bbc.co.uk/services/bbcone/east_yorkshire", "bbcone-east_yorkshire");
    public static final OldChannel BBC_ONE_YORKSHIRE = new OldChannel("BBC One Yorkshire", "http://www.bbc.co.uk/services/bbcone/yorkshire", "bbcone-yorkshire");
    public static final OldChannel BBC_TWO = new OldChannel("BBC Two", "http://www.bbc.co.uk/services/bbctwo/england", "bbctwo");

    public static final OldChannel BBC_TWO_NORTHERN_IRELAND = new OldChannel("BBC Two Northern Ireland", "http://www.bbc.co.uk/services/bbctwo/ni", "bbctwo-ni");
    public static final OldChannel BBC_TWO_NORTHERN_IRELAND_ALALOGUE = new OldChannel("BBC Two Northern Ireland (Analogue)", "http://www.bbc.co.uk/services/bbctwo/ni_analogue", "bbctwo-ni_analogue");
    public static final OldChannel BBC_TWO_SCOTLAND = new OldChannel("BBC Two Scotland", "http://www.bbc.co.uk/services/bbctwo/scotland", "bbctwo-scotland");
    public static final OldChannel BBC_TWO_WALES = new OldChannel("BBC Two Wales", "http://www.bbc.co.uk/services/bbctwo/wales", "bbctwo-wales");
    public static final OldChannel BBC_TWO_WALES_ANALOGUE = new OldChannel("BBC Two Wales (Analogue)", "http://www.bbc.co.uk/services/bbctwo/wales_analogue", "bbctwo-walesanalogue");

    public static final OldChannel BBC_THREE = new OldChannel("BBC Three", "http://www.bbc.co.uk/services/bbcthree", "bbcthree");
    public static final OldChannel BBC_FOUR = new OldChannel("BBC Four", "http://www.bbc.co.uk/services/bbcfour", "bbcfour");
    public static final OldChannel BBC_NEWS = new OldChannel("BBC News", "http://www.bbc.co.uk/services/bbcnews", "bbcnews");
    public static final OldChannel BBC_WORLD_NEWS = new OldChannel("BBC World News", "http://www.bbc.co.uk/services/bbcworldnews", "bbcworldnews");
    public static final OldChannel BBC_PARLIMENT = new OldChannel("BBC Parliament", "http://www.bbc.co.uk/services/parliament", "bbcparliment");
    public static final OldChannel BBC_HD = new OldChannel("BBC HD", "http://www.bbc.co.uk/services/bbchd", "bbchd");
    public static final OldChannel CBBC = new OldChannel("CBBC", "http://www.bbc.co.uk/services/cbbc", "cbbc");
    public static final OldChannel CBEEBIES = new OldChannel("CBeebies", "http://www.bbc.co.uk/services/cbeebies", "cbeebies");
    
    public static final OldChannel BBC_RADIO_LONDON = new OldChannel("BBC Radio London", "http://www.bbc.co.uk/services/london", "london");
    public static final OldChannel BBC_RADIO_BERKSHIRE = new OldChannel("BBC Radio Berkshire", "http://www.bbc.co.uk/services/berkshire", "berkshire");
    public static final OldChannel BBC_RADIO_BRISTOL = new OldChannel("BBC Radio Bristol", "http://www.bbc.co.uk/services/bristol", "bristol");
    public static final OldChannel BBC_RADIO_CAMBRIDGESHIRE = new OldChannel("BBC Radio Cambridgeshire", "http://www.bbc.co.uk/services/cambridgeshire", "cambridgeshire");
    public static final OldChannel BBC_RADIO_CORNWALL = new OldChannel("BBC Radio Cornwall", "http://www.bbc.co.uk/services/cornwall", "cornwall");
    public static final OldChannel BBC_RADIO_COVENTRY = new OldChannel("BBC Radio Coventry", "http://www.bbc.co.uk/services/coventry", "coventry");
    public static final OldChannel BBC_RADIO_CUMBRIA = new OldChannel("BBC Radio Cumbria", "http://www.bbc.co.uk/services/cumbria", "cumbria");
    public static final OldChannel BBC_RADIO_DERBY = new OldChannel("BBC Radio Derby", "http://www.bbc.co.uk/services/derby", "derby");
    public static final OldChannel BBC_RADIO_DEVON = new OldChannel("BBC Radio Devon", "http://www.bbc.co.uk/services/devon", "devon");
    public static final OldChannel BBC_RADIO_ESSEX = new OldChannel("BBC Radio Essex", "http://www.bbc.co.uk/services/essex", "essex");
    public static final OldChannel BBC_RADIO_GLOUCESTERSHIRE = new OldChannel("BBC Radio Gloucestershire", "http://www.bbc.co.uk/services/gloucestershire", "gloucestershire");
    public static final OldChannel BBC_RADIO_GUERNSEY = new OldChannel("BBC Radio Guernsey", "http://www.bbc.co.uk/services/guernsey", "guernsey");
    public static final OldChannel BBC_RADIO_HEREFORDANDWORCESTER = new OldChannel("BBC Radio Herefordandworcester", "http://www.bbc.co.uk/services/herefordandworcester", "herefordandworcester");
    public static final OldChannel BBC_RADIO_HUMBERSIDE = new OldChannel("BBC Radio Humberside", "http://www.bbc.co.uk/services/humberside", "humberside");
    public static final OldChannel BBC_RADIO_JERSEY = new OldChannel("BBC Radio Jersey", "http://www.bbc.co.uk/services/jersey", "jersey");
    public static final OldChannel BBC_RADIO_KENT = new OldChannel("BBC Radio Kent", "http://www.bbc.co.uk/services/kent", "kent");
    public static final OldChannel BBC_RADIO_LANCASHIRE = new OldChannel("BBC Radio Lancashire", "http://www.bbc.co.uk/services/lancashire", "lancashire");
    public static final OldChannel BBC_RADIO_LEEDS = new OldChannel("BBC Radio Leeds", "http://www.bbc.co.uk/services/leeds", "leeds");
    public static final OldChannel BBC_RADIO_LEICESTER = new OldChannel("BBC Radio Leicester", "http://www.bbc.co.uk/services/leicester", "leicester");
    public static final OldChannel BBC_RADIO_LINCOLNSHIRE = new OldChannel("BBC Radio Lincolnshire", "http://www.bbc.co.uk/services/lincolnshire", "lincolnshire");
    public static final OldChannel BBC_RADIO_MANCHESTER = new OldChannel("BBC Radio Manchester", "http://www.bbc.co.uk/services/manchester", "manchester");
    public static final OldChannel BBC_RADIO_MERSEYSIDE = new OldChannel("BBC Radio Merseyside", "http://www.bbc.co.uk/services/merseyside", "merseyside");
    public static final OldChannel BBC_RADIO_NEWCASTLE = new OldChannel("BBC Radio Newcastle", "http://www.bbc.co.uk/services/newcastle", "newcastle");
    public static final OldChannel BBC_RADIO_NORFOLK = new OldChannel("BBC Radio Norfolk", "http://www.bbc.co.uk/services/norfolk", "norfolk");
    public static final OldChannel BBC_RADIO_NORTHAMPTON = new OldChannel("BBC Radio Northampton", "http://www.bbc.co.uk/services/northampton", "northampton");
    public static final OldChannel BBC_RADIO_NOTTINGHAM = new OldChannel("BBC Radio Nottingham", "http://www.bbc.co.uk/services/nottingham", "nottingham");
    public static final OldChannel BBC_RADIO_OXFORD = new OldChannel("BBC Radio Oxford", "http://www.bbc.co.uk/services/oxford", "oxford");
    public static final OldChannel BBC_RADIO_SHEFFIELD = new OldChannel("BBC Radio Sheffield", "http://www.bbc.co.uk/services/sheffield", "sheffield");
    public static final OldChannel BBC_RADIO_SHROPSHIRE = new OldChannel("BBC Radio Shropshire", "http://www.bbc.co.uk/services/shropshire", "shropshire");
    public static final OldChannel BBC_RADIO_SOLENT = new OldChannel("BBC Radio Solent", "http://www.bbc.co.uk/services/solent", "solent");
    public static final OldChannel BBC_RADIO_SOMERSET = new OldChannel("BBC Radio Somerset", "http://www.bbc.co.uk/services/somerset", "somerset");
    public static final OldChannel BBC_RADIO_STOKE = new OldChannel("BBC Radio Stoke", "http://www.bbc.co.uk/services/stoke", "stoke");
    public static final OldChannel BBC_RADIO_SUFFOLK = new OldChannel("BBC Radio Suffolk", "http://www.bbc.co.uk/services/suffolk", "suffolk");
    public static final OldChannel BBC_RADIO_SURREY = new OldChannel("BBC Radio Surrey", "http://www.bbc.co.uk/services/surrey", "surrey");
    public static final OldChannel BBC_RADIO_SUSSEX = new OldChannel("BBC Radio Sussex", "http://www.bbc.co.uk/services/sussex", "sussex");
    public static final OldChannel BBC_RADIO_WILTSHIRE = new OldChannel("BBC Radio Wiltshire", "http://www.bbc.co.uk/services/wiltshire", "wiltshire");
    public static final OldChannel BBC_RADIO_YORK = new OldChannel("BBC Radio York", "http://www.bbc.co.uk/services/york", "york");
    public static final OldChannel BBC_RADIO_TEES = new OldChannel("BBC Radio Tees", "http://www.bbc.co.uk/services/tees", "tees");
    public static final OldChannel BBC_RADIO_THREECOUNTIES = new OldChannel("BBC Radio Threecounties", "http://www.bbc.co.uk/services/threecounties", "threecounties");
    public static final OldChannel BBC_RADIO_WM = new OldChannel("BBC Radio Wm", "http://www.bbc.co.uk/services/wm", "wm");
    public static final OldChannel BBC_RADIO_RADIO1 = new OldChannel("BBC Radio Radio1", "http://www.bbc.co.uk/services/radio1/england", "radio1");
    public static final OldChannel BBC_RADIO_1XTRA = new OldChannel("BBC Radio 1xtra", "http://www.bbc.co.uk/services/1xtra", "1xtra");
    public static final OldChannel BBC_RADIO_RADIO2 = new OldChannel("BBC Radio Radio2", "http://www.bbc.co.uk/services/radio2", "radio2");
    public static final OldChannel BBC_RADIO_RADIO3 = new OldChannel("BBC Radio Radio3", "http://www.bbc.co.uk/services/radio3", "radio3");
    public static final OldChannel BBC_RADIO_RADIO4 = new OldChannel("BBC Radio Radio4", "http://www.bbc.co.uk/services/radio4/fm", "radio4");
    public static final OldChannel BBC_RADIO_5LIVE = new OldChannel("BBC Radio 5live", "http://www.bbc.co.uk/services/5live", "5live");
    public static final OldChannel BBC_RADIO_5LIVESPORTSEXTRA = new OldChannel("BBC Radio 5livesportsextra", "http://www.bbc.co.uk/services/5livesportsextra", "5livesportsextra");
    public static final OldChannel BBC_RADIO_6MUSIC = new OldChannel("BBC Radio 6music", "http://www.bbc.co.uk/services/6music", "6music");
    public static final OldChannel BBC_RADIO_RADIO7 = new OldChannel("BBC Radio Radio7", "http://www.bbc.co.uk/services/radio7", "radio7");
    public static final OldChannel BBC_RADIO_ASIANNETWORK = new OldChannel("BBC Radio Asiannetwork", "http://www.bbc.co.uk/services/asiannetwork", "asiannetwork");
    public static final OldChannel BBC_RADIO_WORLDSERVICE = new OldChannel("BBC Radio Worldservice", "http://www.bbc.co.uk/services/worldservice", "worldservice");
    public static final OldChannel BBC_RADIO_RADIOSCOTLAND = new OldChannel("BBC Radio Radioscotland", "http://www.bbc.co.uk/services/radioscotland/fm", "radioscotland");
    public static final OldChannel BBC_RADIO_RADIOSCOTLAND_MW = new OldChannel("BBC Radio Radioscotland MW", "http://www.bbc.co.uk/services/radioscotland/mw", "radioscotlandmw");
    public static final OldChannel BBC_RADIO_RADIONANGAIDHEAL = new OldChannel("BBC Radio Radionangaidheal", "http://www.bbc.co.uk/services/radionangaidheal", "radionangaidheal");
    public static final OldChannel BBC_RADIO_RADIOULSTER = new OldChannel("BBC Radio Radioulster", "http://www.bbc.co.uk/services/radioulster", "radioulster");
    public static final OldChannel BBC_RADIO_RADIOFOYLE = new OldChannel("BBC Radio Radiofoyle", "http://www.bbc.co.uk/services/radiofoyle", "radiofoyle");
    public static final OldChannel BBC_RADIO_RADIOWALES = new OldChannel("BBC Radio Radiowales", "http://www.bbc.co.uk/services/radiowales/fm", "radiowales");
    public static final OldChannel BBC_RADIO_RADIOORKNEY = new OldChannel("BBC Radio Radioorkney", "http://www.bbc.co.uk/services/radioorkney", "radioorkney");
    public static final OldChannel BBC_RADIO_RADIOSHETLAND = new OldChannel("BBC Radio Radioshetland", "http://www.bbc.co.uk/services/radioshetland", "radioshetland");
    public static final OldChannel BBC_RADIO_RADIOCYMRU = new OldChannel("BBC Radio Radiocymru", "http://www.bbc.co.uk/services/radiocymru", "radiocymru");
    public static final OldChannel BBC_RADIO_RADIO4_LW = new OldChannel("BBC Radio Radio4 LW", "http://www.bbc.co.uk/services/radio4/lw", "radio4lw");
    public static final OldChannel BBC_RADIO_RADIO4_EXTRA = new OldChannel("BBC Radio Radio4 Extra", "http://www.bbc.co.uk/services/radio4extra", "radio4extra");

    public static final OldChannel CHANNEL_FOUR = new OldChannel("Channel 4", "http://www.channel4.com", "channel4");
    public static final OldChannel MORE_FOUR = new OldChannel("More 4", "http://www.channel4.com/more4", "more4");
    public static final OldChannel FILM_4 = new OldChannel("Film 4", "http://film4.com", "film4");
    public static final OldChannel E_FOUR = new OldChannel("E4", "http://www.e4.com", "e4");
    public static final OldChannel FOUR_MUSIC = new OldChannel("4 Music", "http://www.4music.com", "4music");
    public static final OldChannel FOUR_SEVEN = new OldChannel("4Seven", "www.channel4.com/4seven", "4music");

    public static final OldChannel FIVE = new OldChannel("Five", "http://www.five.tv", "five");
    public static final OldChannel FIVE_PLUS1 = new OldChannel("Five +1", "http://www.five.tv/plus1", "fiveplus1");
    public static final OldChannel FIVE_HD = new OldChannel("Five HD", "http://www.five.tv/channels/five_hd", "fivehd");
    public static final OldChannel FIVER = new OldChannel("Fiver", "http://www.five.tv/channels/fiver", "fiver");
    public static final OldChannel FIVE_USA = new OldChannel("Five USA", "http://www.five.tv/channels/five-usa", "fiveusa");

    public static final OldChannel ITV1_LONDON = new OldChannel("ITV1 London", "http://www.itv.com/channels/itv1/london", "itv1london");
    public static final OldChannel ITV1_GRANADA = new OldChannel("ITV1 Granada", "http://www.itv.com/channels/itv1/granada", "itv1granada");
    public static final OldChannel ITV1_TYNE_TEES = new OldChannel("ITV1 Tyne Tees", "http://www.itv.com/channels/itv1/tynetees", "itv1tynetees");
    public static final OldChannel ITV1_BORDER_SOUTH = new OldChannel("ITV1 Border South", "http://www.itv.com/channels/itv1/bordersouth", "itv1bordersouth");
    public static final OldChannel ITV1_MERIDIAN = new OldChannel("ITV1 Meridian", "http://www.itv.com/channels/itv1/meridian", "itv1meridian");
    public static final OldChannel ITV1_ANGLIA = new OldChannel("ITV1 Anglia", "http://www.itv.com/channels/itv1/anglia", "itv1anglia");
    public static final OldChannel ITV1_CHANNEL = new OldChannel("ITV1 Channel", "http://www.itv.com/channels/itv1/channel", "itv1channel");
    public static final OldChannel ITV1_WALES = new OldChannel("ITV1 Wales", "http://www.itv.com/channels/itv1/wales", "itv1wales");
    public static final OldChannel ITV1_WEST = new OldChannel("ITV1 West", "http://www.itv.com/channels/itv1/west", "itv1west");
    public static final OldChannel ITV1_CARLTON_CENTRAL = new OldChannel("ITV1 Carlton-Central", "http://www.itv.com/channels/itv1/carltoncentral", "itv1carltoncentral");
    public static final OldChannel ITV1_CARLTON_WESTCOUNTRY = new OldChannel("ITV1 Carlton-Westcountry", "http://www.itv.com/channels/itv1/carltonwestcountry", "itv1carltonwestcountry");
    public static final OldChannel ITV1_BORDER_NORTH = new OldChannel("ITV1 Border North", "http://www.itv.com/channels/itv1/bordernorth", "itv1bordernorth");
    public static final OldChannel ITV1_THAMES_VALLEY_NORTH = new OldChannel("ITV1 Thames Valley North", "http://www.itv.com/channels/itv1/thamesvalleynorth", "itv1thamesvalleynorth");
    public static final OldChannel ITV1_THAMES_VALLEY_SOUTH = new OldChannel("ITV1 Thames Valley South", "http://www.itv.com/channels/itv1/thamesvalleysouth", "itv1thamesvalleysouth");
    public static final OldChannel ITV1_HD = new OldChannel("ITV1 HD", "http://www.itv.com/channels/itv1/hd", "itv1hd");
    public static final OldChannel ITV1_CENTRAL_PLUS1 = new OldChannel("ITV1 Central +1", "http://www.itv.com/channels/itv1/central#plus1", "itv1centralplus1");
    public static final OldChannel ITV1_GRANADA_PLUS1 = new OldChannel("ITV1 Granada +1", "http://www.itv.com/channels/itv1/granada#plus1", "itv1granadaplus1");
    public static final OldChannel ITV1_LONDON_PLUS1 = new OldChannel("ITV1 London +1", "http://www.itv.com/channels/itv1/london#plus1", "itv1londonplus1");
    public static final OldChannel ITV1_SOUTH_EAST_PLUS1 = new OldChannel("ITV1 South East +1", "http://www.itv.com/channels/itv1/southeast#plus1", "itv1southeastplus1");
    public static final OldChannel ITV1_UTV_PLUS1 = new OldChannel("ITV1 UTV +1", "http://www.itv.com/channels/itv1/utv#plus1", "itv1utvplus1");
    public static final OldChannel ITV1_WEST_PLUS1 = new OldChannel("ITV1 West +1", "http://www.itv.com/channels/itv1/west#plus1", "itv1westplus1");
    public static final OldChannel ITV1_YORKSHIRE_TYNE_TEES_PLUS1 = new OldChannel("ITV1 Yorkshire Tyne Tees +1", "http://www.itv.com/channels/itv1/yorkshiretynestees#plus1", "itv1yorkshiretyneteesplus1");
 
    public static final OldChannel ITV2 = new OldChannel("ITV2", "http://www.itv.com/channels/itv2", "itv2");
    public static final OldChannel ITV2_HD = new OldChannel("ITV2 HD", "http://www.itv.com/channels/itv2/hd", "itv2hd");
    public static final OldChannel ITV3 = new OldChannel("ITV3", "http://www.itv.com/channels/itv3", "itv3");
    public static final OldChannel ITV3_HD = new OldChannel("ITV3 HD", "http://www.itv.com/channels/itv3/hd", "itv3hd");
    public static final OldChannel ITV4 = new OldChannel("ITV4", "http://www.itv.com/channels/itv4", "itv4");
    public static final OldChannel ITV4_HD = new OldChannel("ITV4 HD", "http://www.itv.com/channels/itv4/hd", "itv4hd");

    public static final OldChannel YTV = new OldChannel("Y TV", CHANNEL_URI_PREFIX + "ytv", "ytv");
    public static final OldChannel ULSTER = new OldChannel("Ulster", CHANNEL_URI_PREFIX + "ulster", "ulster");
    public static final OldChannel ULSTER_HD = new OldChannel("Ulster HD", CHANNEL_URI_PREFIX + "ulsterhd", "ulsterhd");
    public static final OldChannel STV_CENTRAL = new OldChannel("STV Central", CHANNEL_URI_PREFIX + "stvcentral", "stvcentral");
    public static final OldChannel STV_HD = new OldChannel("SVT HD", CHANNEL_URI_PREFIX + "stvhd", "stvhd");
    public static final OldChannel STV_NORTH = new OldChannel("STV NORTH", CHANNEL_URI_PREFIX + "stvnorth", "stvnorth");
    public static final OldChannel Channel_4_PLUS1 = new OldChannel("Channel 4 +1", "http://www.channel4.com/cchannel4plus1", "channel4plus1");
    public static final OldChannel Channel_4_HD = new OldChannel("Channel 4 HD", CHANNEL_URI_PREFIX + "channel4hd", "channel4hd");
    public static final OldChannel S4C = new OldChannel("S4C", CHANNEL_URI_PREFIX + "s4c", "s4c");
    public static final OldChannel S4C_CLIRLUN = new OldChannel("S4C Clirlun", CHANNEL_URI_PREFIX + "s4cclirlun", "s4cclirlun");
    public static final OldChannel RTE1 = new OldChannel("RTE 1", CHANNEL_URI_PREFIX + "rte1", "rte1");
    public static final OldChannel RTE2 = new OldChannel("RTE 2", CHANNEL_URI_PREFIX + "rte2", "rte2");
    public static final OldChannel TG4 = new OldChannel("TG 4", CHANNEL_URI_PREFIX + "tg4", "tg4");
    public static final OldChannel TV3 = new OldChannel("TV 3", CHANNEL_URI_PREFIX + "tv3", "tv3");
    public static final OldChannel GMTV_DIGITAL = new OldChannel("GMTV Digital", CHANNEL_URI_PREFIX + "gmtv", "gmtv");
    public static final OldChannel ITV2_PLUS1 = new OldChannel("ITV2 +1", "http://www.itv.com/channels/itv2#plus1", "itv2plus1");
    public static final OldChannel E4_PLUS1 = new OldChannel("E4 +1", "http://www.e4.com/plus1", "e4plus1");
    public static final OldChannel E4_HD = new OldChannel("E4 HD", "http://www.e4.com/hd", "e4hd");
    public static final OldChannel ITV3_PLUS1 = new OldChannel("ITV3 +1", "http://www.itv.com/channels/itv3#plus1", "itv3plus1");
    public static final OldChannel MORE4_PLUS1 = new OldChannel("More4 +1", "http://www.channel4.com/more4#plus1", "more4plus1");
    public static final OldChannel ITV1_PLUS1 = new OldChannel("ITV1 +1", "http://www.itv.com/channels/itv1#plus1", "itv1plus1");
    public static final OldChannel ITV4_PLUS1 = new OldChannel("ITV4 +1", "http://www.itv.com/channels/itv4#plus1", "itv4plus1");
    public static final OldChannel CITV = new OldChannel("Children's ITV", "http://www.itv.com/channels/citv", "citv");
    public static final OldChannel FIVER_PLUS1 = new OldChannel("Fiver +1", "http://www.five.tv/channels/fiver#plus1", "fiverplus1");
    public static final OldChannel FIVER_USA_PLUS1 = new OldChannel("Fiver USA +1", "http://www.five.tv/channels/fiver-usa#plus1", "fiverusaplus1");
    public static final OldChannel THE_ADULT_CHANNEL = new OldChannel("The Adult Channel", CHANNEL_URI_PREFIX + "theadultchannel", "theadultchannel");
    public static final OldChannel MTV_HITS = new OldChannel("MTV Hits", CHANNEL_URI_PREFIX + "mtvhits", "mtvhits");
    public static final OldChannel MTV_BASE = new OldChannel("MTV Base", CHANNEL_URI_PREFIX + "mtvbase", "mtvbase");
    public static final OldChannel MTV = new OldChannel("MTV", CHANNEL_URI_PREFIX + "mtv", "mtv");
    public static final OldChannel MTV_PLUS1 = new OldChannel("MTV +1", CHANNEL_URI_PREFIX + "mtvplus1", "mtvplus1");
    public static final OldChannel TRAVELCHANNEL = new OldChannel("TRAVELCHANNEL", CHANNEL_URI_PREFIX + "travelchannel", "travelchannel");
    public static final OldChannel TRAVELCHANNEL_PLUS1 = new OldChannel("Travelchannel +1", CHANNEL_URI_PREFIX + "travelchannelplus1", "travelchannelplus1");
    public static final OldChannel TV5 = new OldChannel("TV5", CHANNEL_URI_PREFIX + "tv5", "tv5");
    public static final OldChannel ZEE_TV = new OldChannel("Zee TV", CHANNEL_URI_PREFIX + "zeetv", "zeetv");
    public static final OldChannel PHOENIX_CNE = new OldChannel("Phoenix Cne", CHANNEL_URI_PREFIX + "phoenixcne", "phoenixcne");
    public static final OldChannel CHALLENGE = new OldChannel("Challenge", CHANNEL_URI_PREFIX + "challenge", "challenge");
    public static final OldChannel CHALLENGE_PLUS1 = new OldChannel("Challenge +1", CHANNEL_URI_PREFIX + "challengeplus1", "challengeplus1");
    public static final OldChannel HOME = new OldChannel("HOME", CHANNEL_URI_PREFIX + "home", "home");
    public static final OldChannel HOME_PLUS1 = new OldChannel("Home +1", CHANNEL_URI_PREFIX + "homeplus1", "homeplus1");
    public static final OldChannel SKY_BOX_OFFICE_DIGITAL = new OldChannel("Sky Box Office Digital", CHANNEL_URI_PREFIX + "skyboxofficedigital", "skyboxofficedigital");
    public static final OldChannel BLOOMBERG_TV = new OldChannel("Bloomberg TV", CHANNEL_URI_PREFIX + "bloombergtv", "bloombergtv");
    public static final OldChannel THE_BOX = new OldChannel("The Box", CHANNEL_URI_PREFIX + "thebox", "thebox");
    public static final OldChannel CNN = new OldChannel("CNN", CHANNEL_URI_PREFIX + "cnn", "cnn");
    public static final OldChannel CARTOON_NETWORK = new OldChannel("Cartoon Network", CHANNEL_URI_PREFIX + "cartoonnetwork", "cartoonnetwork");
    public static final OldChannel GOD_CHANNEL = new OldChannel("God Channel", CHANNEL_URI_PREFIX + "godchannel", "godchannel");
    public static final OldChannel PLAYBOY_TV = new OldChannel("Playboy TV", CHANNEL_URI_PREFIX + "playboytv", "playboytv");
    public static final OldChannel ANIMAL_PLANET = new OldChannel("Animal Planet", CHANNEL_URI_PREFIX + "animalplanet", "animalplanet");
    public static final OldChannel ANIMAL_PLANET_PLUS1 = new OldChannel("Animal Planet +1", CHANNEL_URI_PREFIX + "animalplanetplus1", "animalplanetplus1");
    public static final OldChannel SKY_NEWS = new OldChannel("Sky News", CHANNEL_URI_PREFIX + "skynews", "skynews");
    public static final OldChannel SKY_NEWS_HD = new OldChannel("Sky News HD", CHANNEL_URI_PREFIX + "skynewshd", "skynewshd");
    public static final OldChannel MTV_ROCKS = new OldChannel("MTV Rocks", CHANNEL_URI_PREFIX + "mtvrocks", "mtvrocks");
    public static final OldChannel LIVING = new OldChannel("LIVING", CHANNEL_URI_PREFIX + "living", "living");
    public static final OldChannel LIVING_PLUS1 = new OldChannel("Living +1", CHANNEL_URI_PREFIX + "livingplus1", "livingplus1");
    public static final OldChannel LIVING_HD = new OldChannel("Living HD", CHANNEL_URI_PREFIX + "livinghd", "livinghd");
    public static final OldChannel BRAVO = new OldChannel("Bravo", CHANNEL_URI_PREFIX + "bravo", "bravo");
    public static final OldChannel BRAVO_PLUS1 = new OldChannel("Bravo +1", CHANNEL_URI_PREFIX + "bravoplus1", "bravoplus1");
    public static final OldChannel DISCOVERY_HISTORY = new OldChannel("Discovery History", CHANNEL_URI_PREFIX + "discoveryhistory", "discoveryhistory");
    public static final OldChannel DISCOVERY_HISTORY_PLUS_1 = new OldChannel("Discovery History +1", CHANNEL_URI_PREFIX + "discoveryhistoryplus1", "discoveryhistoryplus1");
    public static final OldChannel DISCOVERY_SCIENCE = new OldChannel("Discovery Science", CHANNEL_URI_PREFIX + "discoveryscience", "discoveryscience");
    public static final OldChannel DISCOVERY_SCIENCE_PLUS1 = new OldChannel("Discovery Science +1", CHANNEL_URI_PREFIX + "discoveryscienceplus1", "discoveryscienceplus1");
    public static final OldChannel DISCOVERY_TRAVEL_AND_LIVING = new OldChannel("Discovery Travel And Living", CHANNEL_URI_PREFIX + "discoverytravelandliving", "discoverytravelandliving");
    public static final OldChannel DISCOVERY_TRAVEL_AND_LIVING_PLUS1 = new OldChannel("Discovery Travel And Living +1", CHANNEL_URI_PREFIX + "discoverytravelandlivingplus1",
            "discoverytravelandlivingplus1");
    public static final OldChannel HISTORY = new OldChannel("History", CHANNEL_URI_PREFIX + "history", "history");
    public static final OldChannel HISTORY_PLUS1 = new OldChannel("History +1", CHANNEL_URI_PREFIX + "historyplus1", "historyplus1");
    public static final OldChannel NATIONAL_GEOGRAPHIC = new OldChannel("National Geographic", CHANNEL_URI_PREFIX + "nationalgeographic", "nationalgeographic");
    public static final OldChannel NATIONAL_GEOGRAPHIC_PLUS1 = new OldChannel("National Geographic +1", CHANNEL_URI_PREFIX + "nationalgeographicplus1", "nationalgeographicplus1");
    public static final OldChannel GOLD = new OldChannel("Gold", CHANNEL_URI_PREFIX + "gold", "gold");
    public static final OldChannel GOLD_PLUS1 = new OldChannel("Gold +1", CHANNEL_URI_PREFIX + "goldplus1", "goldplus1");
    public static final OldChannel THE_DISNEY_CHANNEL = new OldChannel("The Disney Channel", CHANNEL_URI_PREFIX + "thedisneychannel", "thedisneychannel");
    public static final OldChannel THE_DISNEY_CHANNEL_PLUS1 = new OldChannel("The Disney Channel +1", CHANNEL_URI_PREFIX + "thedisneychannelplus1", "thedisneychannelplus1");
    public static final OldChannel TELEVISION_X = new OldChannel("Television X", CHANNEL_URI_PREFIX + "televisionx", "televisionx");
    public static final OldChannel VH1 = new OldChannel("VH1", CHANNEL_URI_PREFIX + "vh1", "vh1");
    public static final OldChannel MTV_CLASSIC = new OldChannel("MTV Classic", CHANNEL_URI_PREFIX + "mtvclassic", "mtvclassic");
    public static final OldChannel DISCOVERY = new OldChannel("DISCOVERY", CHANNEL_URI_PREFIX + "discovery", "discovery");
    public static final OldChannel DISCOVERY_PLUS1 = new OldChannel("Discovery +1", CHANNEL_URI_PREFIX + "discoveryplus1", "discoveryplus1");
    public static final OldChannel DISCOVERY_PLUS1_POINT5 = new OldChannel("Discovery +1 Point5", CHANNEL_URI_PREFIX + "discoveryplus1point5", "discoveryplus1point5");
    public static final OldChannel DISCOVERY_TURBO = new OldChannel("Discovery Turbo", CHANNEL_URI_PREFIX + "discoveryturbo", "discoveryturbo");
    public static final OldChannel ALIBI = new OldChannel("Alibi", CHANNEL_URI_PREFIX + "alibi", "alibi");
    public static final OldChannel ALIBI_PLUS1 = new OldChannel("Alibi +1", CHANNEL_URI_PREFIX + "alibiplus1", "alibiplus1");
    public static final OldChannel UNIVERSAL = new OldChannel("Universal", CHANNEL_URI_PREFIX + "universal", "universal");
    public static final OldChannel UNIVERSAL_HD = new OldChannel("Universal HD", CHANNEL_URI_PREFIX + "universalhd", "universalhd");
    public static final OldChannel UNIVERSAL_PLUS1 = new OldChannel("Universal +1", CHANNEL_URI_PREFIX + "universalplus1", "universalplus1");
    public static final OldChannel SYFY = new OldChannel("SyFy", CHANNEL_URI_PREFIX + "syfy", "syfy");
    public static final OldChannel SYFY_PLUS1 = new OldChannel("SyFy +1", CHANNEL_URI_PREFIX + "syfyplus1", "syfyplus1");
    public static final OldChannel SYFY_HD = new OldChannel("SyFy HD", CHANNEL_URI_PREFIX + "syfyhd", "syfyhd");
    public static final OldChannel COMEDY_CENTRAL = new OldChannel("Comedy Central", CHANNEL_URI_PREFIX + "comedycentral", "comedycentral");
    public static final OldChannel COMEDY_CENTRAL_PLUS1 = new OldChannel("Comedy Central +1", CHANNEL_URI_PREFIX + "comedycentralplus1", "comedycentralplus1");
    public static final OldChannel SONY_ENTERTAINMENT_TV_ASIA = new OldChannel("Sony Entertainment TV Asia", CHANNEL_URI_PREFIX + "sonyentertainmenttvasia", "sonyentertainmenttvasia");
    public static final OldChannel TCM = new OldChannel("TCM", CHANNEL_URI_PREFIX + "tcm", "tcm");
    public static final OldChannel NICKELODEON = new OldChannel("Nickelodeon", CHANNEL_URI_PREFIX + "nickelodeon", "nickelodeon");
    public static final OldChannel NICKELODEON_REPLAY = new OldChannel("Nickelodeon Replay", CHANNEL_URI_PREFIX + "nickelodeonreplay", "nickelodeonreplay");
    public static final OldChannel CNBC = new OldChannel("CNBC", CHANNEL_URI_PREFIX + "cnbc", "cnbc");
    public static final OldChannel NICK_JR = new OldChannel("Nick Jr", CHANNEL_URI_PREFIX + "nickjr", "nickjr");
    public static final OldChannel BOOMERANG = new OldChannel("Boomerang", CHANNEL_URI_PREFIX + "boomerang", "boomerang");
    public static final OldChannel BOOMERANG_PLUS1 = new OldChannel("Boomerang +1", CHANNEL_URI_PREFIX + "boomerangplus1", "boomerangplus1");
    public static final OldChannel QVC = new OldChannel("QVC", CHANNEL_URI_PREFIX + "qvc", "qvc");
    public static final OldChannel GBC = new OldChannel("GBC", CHANNEL_URI_PREFIX + "gbc", "gbc");
    public static final OldChannel BBC_ENTERTAINMENT = new OldChannel("BBC Entertainment", CHANNEL_URI_PREFIX + "bbcentertainment", "bbcentertainment");
    public static final OldChannel FILM4_HD = new OldChannel("FILM4 HD", CHANNEL_URI_PREFIX + "film4hd", "film4hd");
    public static final OldChannel FILM4_PLUS1 = new OldChannel("FILM4 +1", CHANNEL_URI_PREFIX + "film4plus1", "film4plus1");
    public static final OldChannel SKY1 = new OldChannel("SKY1", CHANNEL_URI_PREFIX + "sky1", "sky1");
    public static final OldChannel DISCOVERY_HOME_AND_HEALTH = new OldChannel("Discovery Home And Health", CHANNEL_URI_PREFIX + "discoveryhomeandhealth", "discoveryhomeandhealth");
    public static final OldChannel DISCOVERY_HOME_AND_HEALTH_PLUS1 = new OldChannel("Discovery Home And Health +1", CHANNEL_URI_PREFIX + "discoveryhomeandhealthplus1",
            "discoveryhomeandhealthplus1");
    public static final OldChannel MUTV = new OldChannel("MUTV", CHANNEL_URI_PREFIX + "mutv", "mutv");
    public static final OldChannel SKY_SPORTS_NEWS = new OldChannel("Sky Sports News", CHANNEL_URI_PREFIX + "skysportsnews", "skysportsnews");
    public static final OldChannel EUROSPORT = new OldChannel("EUROSPORT", CHANNEL_URI_PREFIX + "eurosport", "eurosport");
    public static final OldChannel EUROSPORT_HD = new OldChannel("Eurosport HD", CHANNEL_URI_PREFIX + "eurosporthd", "eurosporthd");
    public static final OldChannel KISS = new OldChannel("KISS", CHANNEL_URI_PREFIX + "kiss", "kiss");
    public static final OldChannel PLAYHOUSE_DISNEY = new OldChannel("Playhouse Disney", CHANNEL_URI_PREFIX + "playhousedisney", "playhousedisney");
    public static final OldChannel PLAYHOUSE_DISNEY_PLUS = new OldChannel("Playhouse Disney Plus", CHANNEL_URI_PREFIX + "playhousedisneyplus", "playhousedisneyplus");
    public static final OldChannel SKY_SPORTS_1 = new OldChannel("Sky Sports 1", CHANNEL_URI_PREFIX + "skysports1", "skysports1");
    public static final OldChannel SKY_SPORTS_2 = new OldChannel("Sky Sports 2", CHANNEL_URI_PREFIX + "skysports2", "skysports2");
    public static final OldChannel SKY_SPORTS_3 = new OldChannel("Sky Sports 3", CHANNEL_URI_PREFIX + "skysports3", "skysports3");
    public static final OldChannel SKY_SPORTS_4 = new OldChannel("Sky Sports 4", CHANNEL_URI_PREFIX + "skysports4", "skysports4");
    public static final OldChannel BIO = new OldChannel("BIO", CHANNEL_URI_PREFIX + "bio", "bio");
    public static final OldChannel BID_TV = new OldChannel("Bid TV", CHANNEL_URI_PREFIX + "bidtv", "bidtv");
    public static final OldChannel SKY_ARTS_1 = new OldChannel("Sky Arts 1", CHANNEL_URI_PREFIX + "skyarts1", "skyarts1");
    public static final OldChannel EURONEWS = new OldChannel("EURONEWS", CHANNEL_URI_PREFIX + "euronews", "euronews");
    public static final OldChannel B4U_MOVIES = new OldChannel("B4U Movies", CHANNEL_URI_PREFIX + "b4umovies", "b4umovies");
    public static final OldChannel RED_HOT_AMATEUR = new OldChannel("Red Hot Amateur", CHANNEL_URI_PREFIX + "redhotamateur", "redhotamateur");
    public static final OldChannel EXTREME_SPORTS = new OldChannel("Extreme Sports", CHANNEL_URI_PREFIX + "extremesports", "extremesports");
    public static final OldChannel MTV_DANCE = new OldChannel("MTV Dance", CHANNEL_URI_PREFIX + "mtvdance", "mtvdance");
    public static final OldChannel STAR_PLUS = new OldChannel("Star Plus", CHANNEL_URI_PREFIX + "starplus", "starplus");
    public static final OldChannel TELEG = new OldChannel("TELEG", CHANNEL_URI_PREFIX + "teleg", "teleg");
    public static final OldChannel CHANNEL_9 = new OldChannel("Channel 9", CHANNEL_URI_PREFIX + "channel9", "channel9");
    public static final OldChannel GOOD_FOOD = new OldChannel("Good Food", CHANNEL_URI_PREFIX + "goodfood", "goodfood");
    public static final OldChannel GOOD_FOOD_PLUS1 = new OldChannel("Good Food +1", CHANNEL_URI_PREFIX + "goodfoodplus1", "goodfoodplus1");
    public static final OldChannel ATTHERACES = new OldChannel("ATTHERACES", CHANNEL_URI_PREFIX + "attheraces", "attheraces");
    public static final OldChannel NICKTOONS_TV = new OldChannel("Nicktoons TV", CHANNEL_URI_PREFIX + "nicktoonstv", "nicktoonstv");
    public static final OldChannel NICKTOONS_REPLAY = new OldChannel("Nicktoons Replay", CHANNEL_URI_PREFIX + "nicktoonsreplay", "nicktoonsreplay");
    public static final OldChannel CHART_SHOW_TV = new OldChannel("Chart Show TV", CHANNEL_URI_PREFIX + "chartshowtv", "chartshowtv");
    public static final OldChannel YESTERDAY = new OldChannel("YESTERDAY", CHANNEL_URI_PREFIX + "yesterday", "yesterday");
    public static final OldChannel YESTERDAY_PLUS1 = new OldChannel("Yesterday +1", CHANNEL_URI_PREFIX + "yesterdayplus1", "yesterdayplus1");
    public static final OldChannel MAGIC = new OldChannel("MAGIC", CHANNEL_URI_PREFIX + "magic", "magic");
    public static final OldChannel SMASH_HITS = new OldChannel("Smash Hits", CHANNEL_URI_PREFIX + "smashhits", "smashhits");
    public static final OldChannel KERRANG = new OldChannel("KERRANG", CHANNEL_URI_PREFIX + "kerrang", "kerrang");
    public static final OldChannel THE_COMMUNITY_CHANNEL = new OldChannel("The Community Channel", CHANNEL_URI_PREFIX + "thecommunitychannel", "thecommunitychannel");
    public static final OldChannel SKY2 = new OldChannel("SKY2", CHANNEL_URI_PREFIX + "sky2", "sky2");
    public static final OldChannel E_EXLAMATION = new OldChannel("E Exlamation", CHANNEL_URI_PREFIX + "eexlamation", "eexlamation");
    public static final OldChannel FLAUNT = new OldChannel("FLAUNT", CHANNEL_URI_PREFIX + "flaunt", "flaunt");
    public static final OldChannel SCUZZ = new OldChannel("SCUZZ", CHANNEL_URI_PREFIX + "scuzz", "scuzz");
    public static final OldChannel BLISS = new OldChannel("BLISS", CHANNEL_URI_PREFIX + "bliss", "bliss");
    public static final OldChannel ESPN_AMERICA = new OldChannel("Espn America", CHANNEL_URI_PREFIX + "espnamerica", "espnamerica");
    public static final OldChannel ESPN_AMERICA_HD = new OldChannel("Espn America HD", CHANNEL_URI_PREFIX + "espnamericahd", "espnamericahd");
    public static final OldChannel RED_HOT_40 = new OldChannel("Red Hot 40", CHANNEL_URI_PREFIX + "redhot40", "redhot40");
    public static final OldChannel DAVE = new OldChannel("DAVE", CHANNEL_URI_PREFIX + "dave", "dave");
    public static final OldChannel DAVE_JA_VU = new OldChannel("Dave Ja Vu", CHANNEL_URI_PREFIX + "davejavu", "davejavu");
    public static final OldChannel PRICE_DROP_TV = new OldChannel("Price Drop TV", CHANNEL_URI_PREFIX + "pricedroptv", "pricedroptv");
    public static final OldChannel FX = new OldChannel("FX", CHANNEL_URI_PREFIX + "fx", "fx");
    public static final OldChannel EDEN = new OldChannel("EDEN", CHANNEL_URI_PREFIX + "eden", "eden");
    public static final OldChannel EDEN_PLUS1 = new OldChannel("Eden +1", CHANNEL_URI_PREFIX + "edenplus1", "edenplus1");
    public static final OldChannel EDEN_HD = new OldChannel("Eden HD", CHANNEL_URI_PREFIX + "edenhd", "edenhd");
    public static final OldChannel BLIGHTY = new OldChannel("BLIGHTY", CHANNEL_URI_PREFIX + "blighty", "blighty");
    public static final OldChannel THE_HORROR_CHANNEL = new OldChannel("The Horror Channel", CHANNEL_URI_PREFIX + "thehorrorchannel", "thehorrorchannel");
    public static final OldChannel THE_HORROR_CHANNEL_PLUS1 = new OldChannel("The Horror Channel +1", CHANNEL_URI_PREFIX + "thehorrorchannelplus1", "thehorrorchannelplus1");
    public static final OldChannel RACING_UK = new OldChannel("Racing Uk", CHANNEL_URI_PREFIX + "racinguk", "racinguk");
    public static final OldChannel CHELSEA_TV = new OldChannel("Chelsea TV", CHANNEL_URI_PREFIX + "chelseatv", "chelseatv");
    public static final OldChannel STAR_NEWS = new OldChannel("Star News", CHANNEL_URI_PREFIX + "starnews", "starnews");
    public static final OldChannel SETANTA_IRELAND = new OldChannel("Setanta Ireland", CHANNEL_URI_PREFIX + "setantaireland", "setantaireland");
    public static final OldChannel LIVINGIT = new OldChannel("LIVINGIT", CHANNEL_URI_PREFIX + "livingit", "livingit");
    public static final OldChannel LIVINGIT_PLUS1 = new OldChannel("Livingit +1", CHANNEL_URI_PREFIX + "livingitplus1", "livingitplus1");
    public static final OldChannel EUROSPORT_2 = new OldChannel("Eurosport 2", CHANNEL_URI_PREFIX + "eurosport2", "eurosport2");
    public static final OldChannel REALLY = new OldChannel("REALLY", CHANNEL_URI_PREFIX + "really", "really");
    public static final OldChannel RED_HOT_TV = new OldChannel("Red Hot TV", CHANNEL_URI_PREFIX + "redhottv", "redhottv");
    public static final OldChannel COMEDY_CENTRAL_EXTRA = new OldChannel("Comedy Central Extra", CHANNEL_URI_PREFIX + "comedycentralextra", "comedycentralextra");
    public static final OldChannel COMEDY_CENTRAL_EXTRA_PLUS1 = new OldChannel("Comedy Central Extra +1", CHANNEL_URI_PREFIX + "comedycentralextraplus1", "comedycentralextraplus1");
    public static final OldChannel COMEDY_CENTRAL_HD = new OldChannel("Comedy Central HD", CHANNEL_URI_PREFIX + "comedycentralhd", "comedycentralhd");
    public static final OldChannel M95_TV_MARBELLA = new OldChannel("M95 TV Marbella", CHANNEL_URI_PREFIX + "m95tvmarbella", "m95tvmarbella");
    public static final OldChannel TV3_SPANISH = new OldChannel("TV3 Spanish", CHANNEL_URI_PREFIX + "tv3spanish", "tv3spanish");
    public static final OldChannel DISCOVERY_REAL_TIME = new OldChannel("Discovery Real Time", CHANNEL_URI_PREFIX + "discoveryrealtime", "discoveryrealtime");
    public static final OldChannel DISCOVERY_REAL_TIME_PLUS1 = new OldChannel("Discovery Real Time +1", CHANNEL_URI_PREFIX + "discoveryrealtimeplus1", "discoveryrealtimeplus1");
    public static final OldChannel MOTORS_TV = new OldChannel("Motors TV", CHANNEL_URI_PREFIX + "motorstv", "motorstv");
    public static final OldChannel DISCOVERY_SHED = new OldChannel("Discovery Shed", CHANNEL_URI_PREFIX + "discoveryshed", "discoveryshed");
    public static final OldChannel TRUE_MOVIES = new OldChannel("True Movies", CHANNEL_URI_PREFIX + "truemovies", "truemovies");
    public static final OldChannel SKY3 = new OldChannel("Pick TV", CHANNEL_URI_PREFIX + "sky3", "sky3");
    public static final OldChannel SKY_3_PLUS1 = new OldChannel("Pick TV +1", CHANNEL_URI_PREFIX + "sky3plus1", "sky3plus1");
    public static final OldChannel DISNEY_CINEMAGIC = new OldChannel("Disney Cinemagic", CHANNEL_URI_PREFIX + "disneycinemagic", "disneycinemagic");
    public static final OldChannel DISNEY_CINEMAGIC_PLUS1 = new OldChannel("Disney Cinemagic +1", CHANNEL_URI_PREFIX + "disneycinemagicplus1", "disneycinemagicplus1");
    public static final OldChannel DISNEY_CINEMAGIC_HD = new OldChannel("Disney Cinemagic HD", CHANNEL_URI_PREFIX + "disneycinemagichd", "disneycinemagichd");
    public static final OldChannel ESPN_CLASSIC = new OldChannel("Espn Classic", CHANNEL_URI_PREFIX + "espnclassic", "espnclassic");
    public static final OldChannel THREE_E = new OldChannel("Three E", CHANNEL_URI_PREFIX + "threee", "threee");
    public static final OldChannel FILMFLEX = new OldChannel("FILMFLEX", CHANNEL_URI_PREFIX + "filmflex", "filmflex");
    public static final OldChannel TCM2 = new OldChannel("TCM2", CHANNEL_URI_PREFIX + "tcm2", "tcm2");
    public static final OldChannel CHRISTMAS24 = new OldChannel("CHRISTMAS24", CHANNEL_URI_PREFIX + "christmas24", "christmas24");
    public static final OldChannel CHRISTMAS24_PLUS = new OldChannel("CHRISTMAS24 Plus", CHANNEL_URI_PREFIX + "christmas24plus", "christmas24plus");
    public static final OldChannel DISCOVERY_HD = new OldChannel("Discovery HD", CHANNEL_URI_PREFIX + "discoveryhd", "discoveryhd");
    public static final OldChannel NATIONAL_GEOGRAPHIC_HD = new OldChannel("National Geographic HD", CHANNEL_URI_PREFIX + "nationalgeographichd", "nationalgeographichd");
    public static final OldChannel SKY_BOX_OFFICE_HD1 = new OldChannel("Sky Box Office HD1", CHANNEL_URI_PREFIX + "skyboxofficehd1", "skyboxofficehd1");
    public static final OldChannel SKY_BOX_OFFICE_HD2 = new OldChannel("Sky Box Office HD2", CHANNEL_URI_PREFIX + "skyboxofficehd2", "skyboxofficehd2");
    public static final OldChannel SKY_SPORTS_1_HD = new OldChannel("Sky Sports 1 HD", CHANNEL_URI_PREFIX + "skysports1hd", "skysports1hd");
    public static final OldChannel SKY_SPORTS_2_HD = new OldChannel("Sky Sports 2 HD", CHANNEL_URI_PREFIX + "skysports2hd", "skysports2hd");
    public static final OldChannel E_EUROPE = new OldChannel("E Europe", CHANNEL_URI_PREFIX + "eeurope", "eeurope");
    public static final OldChannel NAT_GEO_WILD = new OldChannel("Nat Geo Wild", CHANNEL_URI_PREFIX + "natgeowild", "natgeowild");
    public static final OldChannel CRIME_AND_INVESTIGATION = new OldChannel("Crime And Investigation", CHANNEL_URI_PREFIX + "crimeandinvestigation", "crimeandinvestigation");
    public static final OldChannel CRIME_AND_INVESTIGATION_PLUS1 = new OldChannel("Crime And Investigation +1", CHANNEL_URI_PREFIX + "crimeandinvestigationplus1", "crimeandinvestigationplus1");
    public static final OldChannel SKY_MOVIES_CHRISTMAS_CHANNEL = new OldChannel("Sky Movies Christmas", CHANNEL_URI_PREFIX + "skymovieschristmas", "skymovieschristmas");
    public static final OldChannel SKY_MOVIES_CHRISTMAS_CHANNEL_HD = new OldChannel("Sky Movies Christmas HD", CHANNEL_URI_PREFIX + "skymovieschristmashd", "skymovieschristmashd");
    public static final OldChannel Channel = new OldChannel("Channel", CHANNEL_URI_PREFIX + "channel", "channel");
    public static final OldChannel Channel_HD = new OldChannel("Channel HD", CHANNEL_URI_PREFIX + "channelhd", "channelhd");
    public static final OldChannel SKY_MOVIES_CRIME_AND_THRILLER = new OldChannel("Sky Movies Crime And Thriller", CHANNEL_URI_PREFIX + "skymoviescrimeandthriller", "skymoviescrimeandthriller");
    public static final OldChannel SKY_MOVIES_CRIME_AND_THRILLER_HD = new OldChannel("Sky Movies Crime And Thriller HD", CHANNEL_URI_PREFIX + "skymoviescrimeandthrillerhd",
            "skymoviescrimeandthrillerhd");
    public static final OldChannel SKY_MOVIES_PREMIERE = new OldChannel("Sky Movies Premiere", CHANNEL_URI_PREFIX + "skymoviespremiere", "skymoviespremiere");
    public static final OldChannel SKY_MOVIES_PREMIERE_PLUS1 = new OldChannel("Sky Movies Premiere +1", CHANNEL_URI_PREFIX + "skymoviespremiereplus1", "skymoviespremiereplus1");
    public static final OldChannel SKY_MOVIES_PREMIERE_HD = new OldChannel("Sky Movies Premiere HD", CHANNEL_URI_PREFIX + "skymoviespremierehd", "skymoviespremierehd");
    public static final OldChannel SKY_MOVIES_COMEDY = new OldChannel("Sky Movies Comedy", CHANNEL_URI_PREFIX + "skymoviescomedy", "skymoviescomedy");
    public static final OldChannel SKY_MOVIES_COMEDY_HD = new OldChannel("Sky Movies Comedy HD", CHANNEL_URI_PREFIX + "skymoviescomedyhd", "skymoviescomedyhd");
    public static final OldChannel SKY_MOVIES_ACTION_AND_ADVENTURE = new OldChannel("Sky Movies Action And Adventure", CHANNEL_URI_PREFIX + "skymoviesactionandadventure",
            "skymoviesactionandadventure");
    public static final OldChannel SKY_MOVIES_ACTION_AND_ADVENTURE_HD = new OldChannel("Sky Movies Action And Adventure HD", CHANNEL_URI_PREFIX + "skymoviesactionandadventurehd",
            "skymoviesactionandadventurehd");
    public static final OldChannel SKY_MOVIES_FAMILY = new OldChannel("Sky Movies Family", CHANNEL_URI_PREFIX + "skymoviesfamily", "skymoviesfamily");
    public static final OldChannel SKY_MOVIES_FAMILY_HD = new OldChannel("Sky Movies Family HD", CHANNEL_URI_PREFIX + "skymoviesfamilyhd", "skymoviesfamilyhd");
    public static final OldChannel SKY_MOVIES_DRAMA_AND_ROMANCE = new OldChannel("Sky Movies Drama And Romance", CHANNEL_URI_PREFIX + "skymoviesdramaandromance", "skymoviesdramaandromance");
    public static final OldChannel SKY_MOVIES_DRAMA_AND_ROMANCE_HD = new OldChannel("Sky Movies Drama And Romance HD", CHANNEL_URI_PREFIX + "skymoviesdramaandromancehd",
            "skymoviesdramaandromancehd");
    public static final OldChannel SKY_MOVIES_SCIFI_HORROR = new OldChannel("Sky Movies Scifi Horror", CHANNEL_URI_PREFIX + "skymoviesscifihorror", "skymoviesscifihorror");
    public static final OldChannel SKY_MOVIES_SCIFI_HORROR_HD = new OldChannel("Sky Movies Scifi Horror HD", CHANNEL_URI_PREFIX + "skymoviesscifihorrorhd", "skymoviesscifihorrorhd");
    public static final OldChannel SKY_MOVIES_CLASSICS = new OldChannel("Sky Movies Classics", CHANNEL_URI_PREFIX + "skymoviesclassics", "skymoviesclassics");
    public static final OldChannel SKY_MOVIES_CLASSICS_HD = new OldChannel("Sky Movies Classics HD", CHANNEL_URI_PREFIX + "skymoviesclassicshd", "skymoviesclassicshd");
    public static final OldChannel SKY_MOVIES_MODERN_GREATS = new OldChannel("Sky Movies Modern Greats", CHANNEL_URI_PREFIX + "skymoviesmoderngreats", "skymoviesmoderngreats");
    public static final OldChannel SKY_MOVIES_MODERN_GREATS_HD = new OldChannel("Sky Movies Modern Greats HD", CHANNEL_URI_PREFIX + "skymoviesmoderngreatshd", "skymoviesmoderngreatshd");
    public static final OldChannel SKY_MOVIES_INDIE = new OldChannel("Sky Movies Indie", CHANNEL_URI_PREFIX + "skymoviesindie", "skymoviesindie");
    public static final OldChannel SKY_MOVIES_INDIE_HD = new OldChannel("Sky Movies Indie HD", CHANNEL_URI_PREFIX + "skymoviesindiehd", "skymoviesindiehd");
    public static final OldChannel AL_JAZEERA_ENGLISH = new OldChannel("Al Jazeera English", CHANNEL_URI_PREFIX + "aljazeeraenglish", "aljazeeraenglish");
    public static final OldChannel HISTORY_HD = new OldChannel("History HD", CHANNEL_URI_PREFIX + "historyhd", "historyhd");
    public static final OldChannel SKY1_HD = new OldChannel("SKY1 HD", CHANNEL_URI_PREFIX + "sky1hd", "sky1hd");
    public static final OldChannel SKY_ARTS_1_HD = new OldChannel("Sky Arts 1 HD", CHANNEL_URI_PREFIX + "skyarts1hd", "skyarts1hd");
    public static final OldChannel CARTOONITO = new OldChannel("CARTOONITO", CHANNEL_URI_PREFIX + "cartoonito", "cartoonito");
    public static final OldChannel BEST_DIRECT = new OldChannel("Best Direct", CHANNEL_URI_PREFIX + "bestdirect", "bestdirect");
    public static final OldChannel BRAVO_2 = new OldChannel("Bravo 2", CHANNEL_URI_PREFIX + "bravo2", "bravo2");
    public static final OldChannel DEUTSCHE_WELLE = new OldChannel("Deutsche Welle", CHANNEL_URI_PREFIX + "deutschewelle", "deutschewelle");
    public static final OldChannel GEMS_TV = new OldChannel("Gems TV", CHANNEL_URI_PREFIX + "gemstv", "gemstv");
    public static final OldChannel GEM_COLLECTOR = new OldChannel("Gem Collector", CHANNEL_URI_PREFIX + "gemcollector", "gemcollector");
    public static final OldChannel Channel_S = new OldChannel("Channel S", CHANNEL_URI_PREFIX + "channels", "channels");
    public static final OldChannel SETANTA_SPORTS_1_IRELAND = new OldChannel("Setanta Sports 1 Ireland", CHANNEL_URI_PREFIX + "setantasports1ireland", "setantasports1ireland");
    public static final OldChannel DIVA = new OldChannel("Diva", CHANNEL_URI_PREFIX + "diva", "diva");
    public static final OldChannel DIVA_PLUS1 = new OldChannel("Diva +1", CHANNEL_URI_PREFIX + "divaplus1", "divaplus1");
    public static final OldChannel Channel_ONE = new OldChannel("Channel One", CHANNEL_URI_PREFIX + "channelone", "channelone");
    public static final OldChannel Channel_ONE_PLUS1 = new OldChannel("Channel One +1", CHANNEL_URI_PREFIX + "channeloneplus1", "channeloneplus1");
    public static final OldChannel CN_TOO = new OldChannel("CN Too", CHANNEL_URI_PREFIX + "cntoo", "cntoo");
    public static final OldChannel POP = new OldChannel("POP", CHANNEL_URI_PREFIX + "pop", "pop");
    public static final OldChannel TINY_POP = new OldChannel("Tiny Pop", CHANNEL_URI_PREFIX + "tinypop", "tinypop");
    public static final OldChannel DMAX = new OldChannel("DMAX", CHANNEL_URI_PREFIX + "dmax", "dmax");
    public static final OldChannel DMAX_PLUS1 = new OldChannel("DMAX +1", CHANNEL_URI_PREFIX + "dmaxplus1", "dmaxplus1");
    public static final OldChannel DMAX_2 = new OldChannel("DMAX 2", CHANNEL_URI_PREFIX + "dmax2", "dmax2");
    public static final OldChannel HORSE_AND_COUNTRY = new OldChannel("Horse And Country", CHANNEL_URI_PREFIX + "horseandcountry", "horseandcountry");
    public static final OldChannel Channel_7 = new OldChannel("Channel 7", CHANNEL_URI_PREFIX + "channel7", "channel7");
    public static final OldChannel SKY_SPORTS_3_HD = new OldChannel("Sky Sports 3 HD", CHANNEL_URI_PREFIX + "skysports3hd", "skysports3hd");
    public static final OldChannel FLAVA = new OldChannel("Flava", CHANNEL_URI_PREFIX + "flava", "flava");
    public static final OldChannel FX_HD = new OldChannel("Fx HD", CHANNEL_URI_PREFIX + "fxhd", "fxhd");
    public static final OldChannel NATIONAL_GEOGRAPHIC_HD_PAN_EUROPEAN = new OldChannel("National Geographic HD Pan European", CHANNEL_URI_PREFIX + "nationalgeographichdpaneuropean",
            "nationalgeographichdpaneuropean");
    public static final OldChannel MOVIES4MEN = new OldChannel("MOVIES4MEN", CHANNEL_URI_PREFIX + "movies4men", "movies4men");
    public static final OldChannel MOVIES4MEN_PLUS1 = new OldChannel("MOVIES4Men +1", CHANNEL_URI_PREFIX + "movies4menplus1", "movies4menplus1");
    public static final OldChannel TRUE_MOVIES_2 = new OldChannel("True Movies 2", CHANNEL_URI_PREFIX + "truemovies2", "truemovies2");
    public static final OldChannel MOVIES4MEN2 = new OldChannel("MOVIES4MEN2", CHANNEL_URI_PREFIX + "movies4men2", "movies4men2");
    public static final OldChannel MOVIES4MEN2_PLUS1 = new OldChannel("MOVIES4MEN2 +1", CHANNEL_URI_PREFIX + "movies4men2plus1", "movies4men2plus1");
    public static final OldChannel MILITARY_HISTORY = new OldChannel("Military History", CHANNEL_URI_PREFIX + "militaryhistory", "militaryhistory");
    public static final OldChannel THE_STYLE_NETWORK = new OldChannel("The Style Network", CHANNEL_URI_PREFIX + "thestylenetwork", "thestylenetwork");
    public static final OldChannel WATCH = new OldChannel("Watch", CHANNEL_URI_PREFIX + "watch", "watch");
    public static final OldChannel WATCH_PLUS1 = new OldChannel("Watch +1", CHANNEL_URI_PREFIX + "watchplus1", "watchplus1");
    public static final OldChannel SKY_ARTS_2 = new OldChannel("Sky Arts 2", CHANNEL_URI_PREFIX + "skyarts2", "skyarts2");
    public static final OldChannel WEDDING_TV = new OldChannel("Wedding TV", CHANNEL_URI_PREFIX + "weddingtv", "weddingtv");
    public static final OldChannel PROPELLER_TV = new OldChannel("Propeller TV", CHANNEL_URI_PREFIX + "propellertv", "propellertv");
    public static final OldChannel MTVN_HD = new OldChannel("MTVn HD", CHANNEL_URI_PREFIX + "mtvnhd", "mtvnhd");
    public static final OldChannel INVESTIGATION_DISCOVERY = new OldChannel("Investigation Discovery", CHANNEL_URI_PREFIX + "investigationdiscovery", "investigationdiscovery");
    public static final OldChannel AIT_INTERNATIONAL = new OldChannel("Ait International", CHANNEL_URI_PREFIX + "aitinternational", "aitinternational");
    public static final OldChannel CINEMOI = new OldChannel("CINEMOI", CHANNEL_URI_PREFIX + "cinemoi", "cinemoi");
    public static final OldChannel SKY_ARTS_2_HD = new OldChannel("Sky Arts 2 HD", CHANNEL_URI_PREFIX + "skyarts2hd", "skyarts2hd");
    public static final OldChannel BIO_HD = new OldChannel("Bio HD", CHANNEL_URI_PREFIX + "biohd", "biohd");
    public static final OldChannel CRIME_AND_INVESTIGATION_HD = new OldChannel("Crime And Investigation HD", CHANNEL_URI_PREFIX + "crimeandinvestigationhd", "crimeandinvestigationhd");
    public static final OldChannel NAT_GEO_WILD_HD = new OldChannel("Nat Geo Wild HD", CHANNEL_URI_PREFIX + "natgeowildhd", "natgeowildhd");
    public static final OldChannel QUEST = new OldChannel("QUEST", CHANNEL_URI_PREFIX + "quest", "quest");
    public static final OldChannel DISCOVERY_QUEST_PLUS1 = new OldChannel("Discovery Quest +1", CHANNEL_URI_PREFIX + "discoveryquestplus1", "discoveryquestplus1");
    public static final OldChannel QUEST_FREEVIEW = new OldChannel("Quest Freeview", CHANNEL_URI_PREFIX + "questfreeview", "questfreeview");
    public static final OldChannel ESPN = new OldChannel("ESPN", CHANNEL_URI_PREFIX + "espn", "espn");
    public static final OldChannel ESPN_HD = new OldChannel("ESPN HD", CHANNEL_URI_PREFIX + "espnhd", "espnhd");
    public static final OldChannel DISNEY_XD = new OldChannel("Disney Xd", CHANNEL_URI_PREFIX + "disneyxd", "disneyxd");
    public static final OldChannel DISNEY_XD_PLUS1 = new OldChannel("Disney Xd +1", CHANNEL_URI_PREFIX + "disneyxdplus1", "disneyxdplus1");
    public static final OldChannel DISNEY_XD_HD = new OldChannel("Disney Xd HD", CHANNEL_URI_PREFIX + "disneyxdhd", "disneyxdhd");
    public static final OldChannel CBS_REALITY = new OldChannel("CBS Reality", CHANNEL_URI_PREFIX + "cbsreality", "cbsreality");
    public static final OldChannel MGM = new OldChannel("MGM", CHANNEL_URI_PREFIX + "mgm", "mgm");
    public static final OldChannel CBS_DRAMA = new OldChannel("CBS Drama", CHANNEL_URI_PREFIX + "cbsdrama", "cbsdrama");
    public static final OldChannel CBS_ACTION = new OldChannel("CBS Action", CHANNEL_URI_PREFIX + "cbsaction", "cbsaction");
    public static final OldChannel VIVA = new OldChannel("VIVA", CHANNEL_URI_PREFIX + "viva", "viva");
    public static final OldChannel FOOD_NETWORK = new OldChannel("Food Network", CHANNEL_URI_PREFIX + "foodnetwork", "foodnetwork");
    public static final OldChannel FOOD_NETWORK_PLUS1 = new OldChannel("Food Network +1", CHANNEL_URI_PREFIX + "foodnetworkplus1", "foodnetworkplus1");
    public static final OldChannel MGM_HD = new OldChannel("Mgm HD", CHANNEL_URI_PREFIX + "mgmhd", "mgmhd");
    public static final OldChannel MTV_SHOWS = new OldChannel("MTV Shows", CHANNEL_URI_PREFIX + "mtvshows", "mtvshows");
    public static final OldChannel NICK_JR_2 = new OldChannel("Nick Jr 2", CHANNEL_URI_PREFIX + "nickjr2", "nickjr2");
    public static final OldChannel NHK_WORLD = new OldChannel("NHK World", CHANNEL_URI_PREFIX + "nhkworld", "nhkworld");
    public static final OldChannel TRUEENT = new OldChannel("TRUEENT", CHANNEL_URI_PREFIX + "trueent", "trueent");
    public static final OldChannel BODY_IN_BALANCE = new OldChannel("Body In Balance", CHANNEL_URI_PREFIX + "bodyinbalance", "bodyinbalance");
    public static final OldChannel THE_ACTIVE_CHANNEL = new OldChannel("The Active Channel", CHANNEL_URI_PREFIX + "theactivechannel", "theactivechannel");
    public static final OldChannel FITNESS_TV = new OldChannel("Fitness TV", CHANNEL_URI_PREFIX + "fitnesstv", "fitnesstv");
    public static final OldChannel RUSH_HD = new OldChannel("Rush HD", CHANNEL_URI_PREFIX + "rushhd", "rushhd");
    public static final OldChannel BBC_SPORT_INTERACTIVE_BBC_ONE = new OldChannel("BBC Sport Interactive BBC One", CHANNEL_URI_PREFIX + "bbcsportinteractivebbcone", "bbcsportinteractivebbcone");
    public static final OldChannel BBC_SPORT_INTERACTIVE_BBC_TWO = new OldChannel("BBC Sport Interactive BBC Two", CHANNEL_URI_PREFIX + "bbcsportinteractivebbctwo", "bbcsportinteractivebbctwo");
    public static final OldChannel BBC_SPORT_INTERACTIVE_BBC_THREE = new OldChannel("BBC Sport Interactive BBC Three", CHANNEL_URI_PREFIX + "bbcsportinteractivebbcthree", "bbcsportinteractivebbcthree");
    public static final OldChannel BBC_SPORT_INTERACTIVE_FREEVIEW = new OldChannel("BBC Sport Interactive Freeview", CHANNEL_URI_PREFIX + "bbcsportinteractivefreeview",
            "bbcsportinteractivefreeview");
    public static final OldChannel SKY_3D = new OldChannel("Sky 3D", CHANNEL_URI_PREFIX + "sky3d", "sky3d");
    public static final OldChannel SKY_SPORTS_4_HD = new OldChannel("Sky Sports 4 HD", CHANNEL_URI_PREFIX + "skysports4hd", "skysports4hd");
    
    public static final OldChannel SKY_ATLANTIC = new OldChannel("Sky Atlantic", CHANNEL_URI_PREFIX+"skyatlantic", "skyatlantic");
    public static final OldChannel SKY_ATLANTIC_HD = new OldChannel("Sky Atlantic HD", CHANNEL_URI_PREFIX+"skyatlantichd", "skyatlantichd");
    public static final OldChannel SKY_MOVIES_SHOWCASE = new OldChannel("Sky Movies Showcase", CHANNEL_URI_PREFIX+"skymoviesshowcase", "skymoviesshowcase");
    public static final OldChannel SKY_MOVIES_SHOWCASE_HD = new OldChannel("Sky Movies Showcase HD", CHANNEL_URI_PREFIX+"skymoviesshowcasehd", "skymoviesshowcasehd");
    public static final OldChannel DISCOVERY_KNOWLEDGE = new OldChannel("Discovery Knowledge", CHANNEL_URI_PREFIX+"discoveryknowledge", "discoveryknowledge");
    public static final OldChannel SKY_LIVING = new OldChannel("Sky Living", CHANNEL_URI_PREFIX+"skyliving", "skyliving");
    public static final OldChannel SKY_LIVING_LOVES = new OldChannel("Sky Living Loves", CHANNEL_URI_PREFIX+"skylivingloves", "skylivingloves");
    public static final OldChannel LIVING_PLUS2 = new OldChannel("Living +2", CHANNEL_URI_PREFIX+"livingplus2", "livingplus2");
    public static final OldChannel FX_PLUS = new OldChannel("FX+", CHANNEL_URI_PREFIX+"fxplus", "fxplus");
    public static final OldChannel BBC_ALBA = new OldChannel("BBC Alba", CHANNEL_URI_PREFIX+"bbcalba", "bbcalba");
    public static final OldChannel SKY_LIVINGIT = new OldChannel("Sky Livingit", CHANNEL_URI_PREFIX+"skylivingit", "skylivingit");
    public static final OldChannel S4C2 = new OldChannel("S4C2", CHANNEL_URI_PREFIX+"sc42", "sc42");
    public static final OldChannel FIVE_USA_PLUS1 = new OldChannel("Five USA +1", CHANNEL_URI_PREFIX+"fiveusaplus1", "fiveusaplus1");
    public static final OldChannel MTV_MUSIC = new OldChannel("MTV Music", CHANNEL_URI_PREFIX+"mtvmusic", "mtvmusic");
    public static final OldChannel TVE_INTERNACIONAL = new OldChannel("TVE Internacional", CHANNEL_URI_PREFIX+"tveinternacional", "tveinternacional");
    public static final OldChannel SUPER_CASINO = new OldChannel("Super Casino", CHANNEL_URI_PREFIX+"supercasino", "supercasino");
    public static final OldChannel SIMPLY_SHOPPING = new OldChannel("Simply Shopping", CHANNEL_URI_PREFIX+"simplyshopping", "simplyshopping");
    public static final OldChannel Q = new OldChannel("Q", CHANNEL_URI_PREFIX+"q", "q");
    public static final OldChannel MOVIES_24 = new OldChannel("Movies24", CHANNEL_URI_PREFIX+"movies24", "movies24");
    public static final OldChannel MOVIES_24_PLUS = new OldChannel("Movies24+", CHANNEL_URI_PREFIX+"movies24plus", "movies24plus");
    public static final OldChannel BET_PLUS1 = new OldChannel("BET +1", CHANNEL_URI_PREFIX+"betplus1", "betplus1");
    public static final OldChannel BET_INTERNATIONAL = new OldChannel("BET International", CHANNEL_URI_PREFIX+"betinternational", "betinternational");
    public static final OldChannel PBS = new OldChannel("PBS", CHANNEL_URI_PREFIX + "pbs", "pbs");
    
    public static final OldChannel XFM_RADIO = new OldChannel("XFM", CHANNEL_URI_PREFIX + "xfmradio", "xfmradio");
    public static final OldChannel ABSOLUTE_RADIO = new OldChannel("Absolute Radio", CHANNEL_URI_PREFIX + "absoluteradio", "absoluteradio");
    public static final OldChannel ABSOLUTE_80S_RADIO = new OldChannel("Absolute 80s", CHANNEL_URI_PREFIX + "absolute80sradio", "absolute80sradio");
    public static final OldChannel ABSOLUTE_90S_RADIO = new OldChannel("Absolute 90s", CHANNEL_URI_PREFIX + "absolute90sradio", "absolute90sradio");
    public static final OldChannel ABSOLUTE_00S_RADIO = new OldChannel("Absolute 00s", CHANNEL_URI_PREFIX + "absolute00sradio", "absolute00sradio");
    public static final OldChannel ABSOLUTE_CLASSIC_ROCK_RADIO = new OldChannel("Absolute Classic Rock", CHANNEL_URI_PREFIX + "absoluteclassicrockradio", "absoluteclassicrockradio");
    public static final OldChannel ABSOLUTE_RADIO_EXTRA_RADIO = new OldChannel("Absolute Radio Extra", CHANNEL_URI_PREFIX + "absoluteradioextra", "absoluteradioextra");
    public static final OldChannel KISS_RADIO = new OldChannel("XFM", CHANNEL_URI_PREFIX + "kissradio", "kissradio");
    public static final OldChannel MAGIC_RADIO = new OldChannel("Magic", CHANNEL_URI_PREFIX + "magicradio", "magicradio");
    public static final OldChannel CLASSIC_FM_RADIO = new OldChannel("Classic FM", CHANNEL_URI_PREFIX + "classicfm", "classicfm");
    public static final OldChannel TALKSPORT_RADIO = new OldChannel("Talk Sport", CHANNEL_URI_PREFIX + "talksportradio", "talksportradio");
    public static final OldChannel SMASH_HITS_RADIO = new OldChannel("Smash Hits", CHANNEL_URI_PREFIX + "smashhitsradio", "smashhitsradio");
    public static final OldChannel KERRANG_RADIO = new OldChannel("Kerrang!", CHANNEL_URI_PREFIX + "kerrangradio", "kerrangradio");
    public static final OldChannel HEAT_RADIO = new OldChannel("Heat", CHANNEL_URI_PREFIX + "heatradio", "heatradio");
    public static final OldChannel CAPITAL_FM = new OldChannel("Capital FM", CHANNEL_URI_PREFIX + "capitalfm", "capitalfm");
    public static final OldChannel HEART_LONDON_RADIO = new OldChannel("Heart London", CHANNEL_URI_PREFIX + "heartlondonradio", "heartlondonradio");
    
    public final static OldChannel TVBLOB_7GOLD = new OldChannel("7 Gold", "http://tvblob.com/channel/7gold", "7gold");
    public final static OldChannel TVBLOB_ANTENNATRE = new OldChannel("Antenna Tre", "http://tvblob.com/channel/antennatre", "antennatre");
    public final static OldChannel TVBLOB_BOING = new OldChannel("Boing", "http://tvblob.com/channel/boing", "boing");
    public final static OldChannel TVBLOB_CANALEITALIA3 = new OldChannel("Canale Italia 3", "http://tvblob.com/channel/canaleitalia3", "canaleitalia3");
    public final static OldChannel TVBLOB_CANALEITALIA83 = new OldChannel("Canale Italia 83", "http://tvblob.com/channel/canaleitalia83", "canaleitalia83");
    public final static OldChannel TVBLOB_CANALEITALIA84 = new OldChannel("Canale Italia 84", "http://tvblob.com/channel/canaleitalia84", "canaleitalia84");
    public final static OldChannel TVBLOB_CANALEITALIAMUSICA = new OldChannel("Canale Italia Musica", "http://tvblob.com/channel/canaleitaliamusica", "canaleitaliamusica");
    public final static OldChannel TVBLOB_CANALEITALIAUNO = new OldChannel("Canale Italia Uno", "http://tvblob.com/channel/canaleitaliauno", "canaleitaliauno");
    public final static OldChannel TVBLOB_CANALE_5 = new OldChannel("Canale5", "http://tvblob.com/channel/canale_5", "canale_5");
    public final static OldChannel TVBLOB_CANALE5_PIU1 = new OldChannel("Canale5 +1", "http://tvblob.com/channel/canale5_piu1", "canale5_piu1");
    public final static OldChannel TVBLOB_CANALE5_HD = new OldChannel("Canale5 HD", "http://tvblob.com/channel/canale5_hd", "canale5_hd");
    public final static OldChannel TVBLOB_CANALE6 = new OldChannel("Canale6", "http://tvblob.com/channel/canale6", "canale6");
    public final static OldChannel TVBLOB_CAPRISTORE = new OldChannel("Capri Store", "http://tvblob.com/channel/capristore", "capristore");
    public final static OldChannel TVBLOB_CARTOONNETWORK = new OldChannel("Cartoon Network - Premium", "http://tvblob.com/channel/cartoonnetwork", "tvblobcartoonnetwork");
    public final static OldChannel TVBLOB_CHANNEL01 = new OldChannel("Channel 01", "http://tvblob.com/channel/channel01", "channel01");
    public final static OldChannel TVBLOB_CHANNEL02 = new OldChannel("Channel 02", "http://tvblob.com/channel/channel02", "channel02");
    public final static OldChannel TVBLOB_CHANNEL03 = new OldChannel("Channel 03", "http://tvblob.com/channel/channel03", "channel03");
    public final static OldChannel TVBLOB_CIELO = new OldChannel("Cielo", "http://tvblob.com/channel/cielo", "cielo");
    public final static OldChannel TVBLOB_CLASSHORSETV = new OldChannel("Class HorseTV", "http://tvblob.com/channel/classhorsetv", "classhorsetv");
    public final static OldChannel TVBLOB_CLASSNEWS = new OldChannel("ClassNews msnbc", "http://tvblob.com/channel/classnews", "classnews");
    public final static OldChannel TVBLOB_COMING_SOON = new OldChannel("Coming Soon Television", "http://tvblob.com/channel/coming_soon", "coming_soon");
    public final static OldChannel TVBLOB_CUBOVISION = new OldChannel("Cubo Vision", "http://tvblob.com/channel/cubovision", "cubovision");
    public final static OldChannel TVBLOB_DAHLIA = new OldChannel("dahlia", "http://tvblob.com/channel/dahlia", "dahlia");
    public final static OldChannel TVBLOB_DAHLIA1CALCIO = new OldChannel("dahlia 1 calcio", "http://tvblob.com/channel/dahlia1calcio", "dahlia1calcio");
    public final static OldChannel TVBLOB_DAHLIA2ADULT = new OldChannel("dahlia 2 adult", "http://tvblob.com/channel/dahlia2adult", "dahlia2adult");
    public final static OldChannel TVBLOB_DAHLIA2CALCIO = new OldChannel("dahlia 2 calcio", "http://tvblob.com/channel/dahlia2calcio", "dahlia2calcio");
    public final static OldChannel TVBLOB_DAHLIA2SPORT = new OldChannel("dahlia 2 sport", "http://tvblob.com/channel/dahlia2sport", "dahlia2sport");
    public final static OldChannel TVBLOB_DAHLIA3ADULT = new OldChannel("dahlia 3 adult", "http://tvblob.com/channel/dahlia3adult", "dahlia3adult");
    public final static OldChannel TVBLOB_DAHLIA3CALCIO = new OldChannel("dahlia 3 calcio", "http://tvblob.com/channel/dahlia3calcio", "dahlia3calcio");
    public final static OldChannel TVBLOB_DAHLIA4CALCIO = new OldChannel("dahlia 4 calcio", "http://tvblob.com/channel/dahlia4calcio", "dahlia4calcio");
    public final static OldChannel TVBLOB_DAHLIA5CALCIO = new OldChannel("dahlia 5 calcio", "http://tvblob.com/channel/dahlia5calcio", "dahlia5calcio");
    public final static OldChannel TVBLOB_DAHLIAADULT = new OldChannel("dahlia adult", "http://tvblob.com/channel/dahliaadult", "dahliaadult");
    public final static OldChannel TVBLOB_DAHLIAADULTGAY = new OldChannel("dahlia adult gay", "http://tvblob.com/channel/dahliaadultgay", "dahliaadultgay");
    public final static OldChannel TVBLOB_DAHLIAEXPLORER = new OldChannel("dahlia explorer", "http://tvblob.com/channel/dahliaexplorer", "dahliaexplorer");
    public final static OldChannel TVBLOB_DAHLIAEXTRA = new OldChannel("dahlia extra", "http://tvblob.com/channel/dahliaextra", "dahliaextra");
    public final static OldChannel TVBLOB_DAHLIASPORT = new OldChannel("dahlia sport", "http://tvblob.com/channel/dahliasport", "dahliasport");
    public final static OldChannel TVBLOB_DAHLIAXTREME = new OldChannel("dahlia xtreme", "http://tvblob.com/channel/dahliaxtreme", "dahliaxtreme");
    public final static OldChannel TVBLOB_DEEJAYTV = new OldChannel("Deejay TV", "http://tvblob.com/channel/deejaytv", "deejaytv");
    public final static OldChannel TVBLOB_DEEJAYTV_PIU2 = new OldChannel("Deejay TV +2", "http://tvblob.com/channel/deejaytv_piu2", "deejaytv_piu2");
    public final static OldChannel TVBLOB_DISNEYCHANNEL = new OldChannel("Disney Channel - Premium", "http://tvblob.com/channel/disneychannel", "disneychannel");
    public final static OldChannel TVBLOB_DISNEYCHANNELPIUUNO = new OldChannel("Disney Channel+1 - Premium", "http://tvblob.com/channel/disneychannelpiuuno", "disneychannelpiuuno");
    public final static OldChannel TVBLOB_E21NETWORK1 = new OldChannel("E21 NETWORK 1", "http://tvblob.com/channel/e21network1", "e21network1");
    public final static OldChannel TVBLOB_ELITESHOPPING = new OldChannel("Elite Shopping", "http://tvblob.com/channel/eliteshopping", "eliteshopping");
    public final static OldChannel TVBLOB_ESPANSIONETV = new OldChannel("Espansione TV", "http://tvblob.com/channel/espansionetv", "espansionetv");
    public final static OldChannel TVBLOB_EURONEWS = new OldChannel("Euronews", "http://tvblob.com/channel/euronews", "tvblobeuronews");
    public final static OldChannel TVBLOB_FDAUDITORIUM = new OldChannel("FD Auditorium", "http://tvblob.com/channel/fdauditorium", "fdauditorium");
    public final static OldChannel TVBLOB_FDLEGGERA = new OldChannel("FD Leggera", "http://tvblob.com/channel/fdleggera", "fdleggera");
    public final static OldChannel TVBLOB_FRANCE24 = new OldChannel("France 24", "http://tvblob.com/channel/france24", "france24");
    public final static OldChannel TVBLOB_FRISBEE = new OldChannel("Frisbee", "http://tvblob.com/channel/frisbee", "frisbee");
    public final static OldChannel TVBLOB_GLAMOURPLUS = new OldChannel("Glamour Plus VM18", "http://tvblob.com/channel/glamourplus", "glamourplus");
    public final static OldChannel TVBLOB_GOLDTV = new OldChannel("Gold TV", "http://tvblob.com/channel/goldtv", "goldtv");
    public final static OldChannel TVBLOB_HIRO = new OldChannel("Hiro - Premium", "http://tvblob.com/channel/hiro", "hiro");
    public final static OldChannel TVBLOB_HOLIDAY = new OldChannel("Holiday", "http://tvblob.com/channel/holiday", "holiday");
    public final static OldChannel TVBLOB_INTERTV = new OldChannel("Inter TV", "http://tvblob.com/channel/intertv", "intertv");
    public final static OldChannel TVBLOB_IRIS = new OldChannel("Iris", "http://tvblob.com/channel/iris", "iris");
    public final static OldChannel TVBLOB_ITALIA8AL = new OldChannel("Italia 8 AL", "http://tvblob.com/channel/italia8al", "italia8al");
    public final static OldChannel TVBLOB_ITALIA8MI = new OldChannel("Italia 8 MI", "http://tvblob.com/channel/italia8mi", "italia8mi");
    public final static OldChannel TVBLOB_ITALIA8PRESTIGE = new OldChannel("Italia 8 Prestige", "http://tvblob.com/channel/italia8prestige", "italia8prestige");
    public final static OldChannel TVBLOB_ITALIAMIA = new OldChannel("Italia Mia", "http://tvblob.com/channel/italiamia", "italiamia");
    public final static OldChannel TVBLOB_ITALIATV = new OldChannel("Italia TV", "http://tvblob.com/channel/italiatv", "italiatv");
    public final static OldChannel TVBLOB_ITALIA_1 = new OldChannel("Italia1", "http://tvblob.com/channel/italia_1", "italia_1");
    public final static OldChannel TVBLOB_ITALIA1_PIU1 = new OldChannel("Italia1 +1", "http://tvblob.com/channel/italia1_piu1", "italia1_piu1");
    public final static OldChannel TVBLOB_ITALIA1_HD = new OldChannel("Italia1 HD", "http://tvblob.com/channel/italia1_hd", "italia1_hd");
    public final static OldChannel TVBLOB_JOI = new OldChannel("Joi - Premium", "http://tvblob.com/channel/joi", "joi");
    public final static OldChannel TVBLOB_JOIPIUUNO = new OldChannel("Joi+1 - Premium", "http://tvblob.com/channel/joipiuuno", "joipiuuno");
    public final static OldChannel TVBLOB_K2 = new OldChannel("K2", "http://tvblob.com/channel/k2", "k2");
    public final static OldChannel TVBLOB_LA5 = new OldChannel("La5", "http://tvblob.com/channel/la5", "la5");
    public final static OldChannel TVBLOB_LA6 = new OldChannel("La6", "http://tvblob.com/channel/la6", "la6");
    public final static OldChannel TVBLOB_LA7 = new OldChannel("La7", "http://tvblob.com/channel/la7", "la7");
    public final static OldChannel TVBLOB_LA7_HD = new OldChannel("La7 HD", "http://tvblob.com/channel/LA7_HD", "LA7_HD");
    public final static OldChannel TVBLOB_LA7NEWSONDEMAND = new OldChannel("La7 News On Demand", "http://tvblob.com/channel/la7newsondemand", "la7newsondemand");
    public final static OldChannel TVBLOB_LA7ONDEMAND = new OldChannel("La7 On Demand", "http://tvblob.com/channel/la7ondemand", "la7ondemand");
    public final static OldChannel TVBLOB_LA7SERVIZIONDEMAND = new OldChannel("La7 Servizi On Demand", "http://tvblob.com/channel/la7serviziondemand", "la7serviziondemand");
    public final static OldChannel TVBLOB_LA7D = new OldChannel("La7D", "http://tvblob.com/channel/la7d", "la7d");
    public final static OldChannel TVBLOB_LA7DONDEMAND = new OldChannel("La7D On Demand", "http://tvblob.com/channel/la7dondemand", "la7dondemand");
    public final static OldChannel TVBLOB_LOMBARDIACHANNEL = new OldChannel("Lombardia Channel", "http://tvblob.com/channel/lombardiachannel", "lombardiachannel");
    public final static OldChannel TVBLOB_LOMBARDIADTT = new OldChannel("Lombardia DTT", "http://tvblob.com/channel/lombardiadtt", "lombardiadtt");
    public final static OldChannel TVBLOB_MEDIASHOPPING = new OldChannel("Media Shopping", "http://tvblob.com/channel/mediashopping", "mediashopping");
    public final static OldChannel TVBLOB_MEDIASET_EXTRA = new OldChannel("Mediaset Extra", "http://tvblob.com/channel/mediaset_extra", "mediaset_extra");
    public final static OldChannel TVBLOB_MIATV = new OldChannel("Mia TV", "http://tvblob.com/channel/miatv", "miatv");
    public final static OldChannel TVBLOB_MILANO2015 = new OldChannel("Milano 2015", "http://tvblob.com/channel/milano2015", "milano2015");
    public final static OldChannel TVBLOB_MILANOW = new OldChannel("MILANOW", "http://tvblob.com/channel/milanow", "milanow");
    public final static OldChannel TVBLOB_MOTORITV = new OldChannel("Motori TV", "http://tvblob.com/channel/motoritv", "motoritv");
    public final static OldChannel TVBLOB_MTV = new OldChannel("MTV", "http://tvblob.com/channel/mtv", "tvblobmtv");
    public final static OldChannel TVBLOB_MTV_HD = new OldChannel("MTV HD", "http://tvblob.com/channel/mtv_hd", "mtv_hd");
    public final static OldChannel TVBLOB_MTVMUSICONDEMAND = new OldChannel("MTV Music On Demand", "http://tvblob.com/channel/mtvmusicondemand", "mtvmusicondemand");
    public final static OldChannel TVBLOB_MTVNEWSONDEMAND = new OldChannel("MTV News On Demand", "http://tvblob.com/channel/mtvnewsondemand", "mtvnewsondemand");
    public final static OldChannel TVBLOB_MTVONDEMAND = new OldChannel("MTV On Demand", "http://tvblob.com/channel/mtvondemand", "mtvondemand");
    public final static OldChannel TVBLOB_MTVPLUS = new OldChannel("MTV+", "http://tvblob.com/channel/mtvplus", "mtvplus");
    public final static OldChannel TVBLOB_MTVPLUSONDEMAND = new OldChannel("MTV+ On Demand", "http://tvblob.com/channel/mtvplusondemand", "mtvplusondemand");
    public final static OldChannel TVBLOB_MYA = new OldChannel("Mya - Premium", "http://tvblob.com/channel/mya", "mya");
    public final static OldChannel TVBLOB_MYAPIUUNO = new OldChannel("Mya+1 - Premium", "http://tvblob.com/channel/myapiuuno", "myapiuuno");
    public final static OldChannel TVBLOB_NITEGATEATTIVAZIONE = new OldChannel("Nitegate Attivazione", "http://tvblob.com/channel/nitegateattivazione", "nitegateattivazione");
    public final static OldChannel TVBLOB_NOTTURNOITALIANO = new OldChannel("Notturno Italiano", "http://tvblob.com/channel/notturnoitaliano", "notturnoitaliano");
    public final static OldChannel TVBLOB_ODEON24 = new OldChannel("Odeon 24", "http://tvblob.com/channel/odeon24", "odeon24");
    public final static OldChannel TVBLOB_PALERMOCHANNEL = new OldChannel("Palermo Channel TV", "http://tvblob.com/channel/palermochannel", "palermochannel");
    public final static OldChannel TVBLOB_PIUBLULOMBARDIATELEMILANO = new OldChannel("Più Blu Lombardia TeleMilano", "http://tvblob.com/channel/piublulombardiatelemilano", "piublulombardiatelemilano");
    public final static OldChannel TVBLOB_PIUSERVIZI = new OldChannel("Più Servizi", "http://tvblob.com/channel/piuservizi", "piuservizi");
    public final static OldChannel TVBLOB_PLAYHOUSEDISNEY = new OldChannel("Playhouse Disney - Premium", "http://tvblob.com/channel/playhousedisney", "tvblobplayhousedisney");
    public final static OldChannel TVBLOB_PLAYME = new OldChannel("PlayMe", "http://tvblob.com/channel/playme", "playme");
    public final static OldChannel TVBLOB_POKERITALIA24 = new OldChannel("Poker Italia 24", "http://tvblob.com/channel/pokeritalia24", "pokeritalia24");
    public final static OldChannel TVBLOB_PORTALESERVIZITELECOM = new OldChannel("Portale Servizi Telecom", "http://tvblob.com/channel/portaleservizitelecom", "portaleservizitelecom");
    public final static OldChannel TVBLOB_PREMIUMCALCIO = new OldChannel("Premium Calcio", "http://tvblob.com/channel/premiumcalcio", "premiumcalcio");
    public final static OldChannel TVBLOB_PREMIUMCALCIO1 = new OldChannel("Premium Calcio 1", "http://tvblob.com/channel/premiumcalcio1", "premiumcalcio1");
    public final static OldChannel TVBLOB_PREMIUMCALCIO2 = new OldChannel("Premium Calcio 2", "http://tvblob.com/channel/premiumcalcio2", "premiumcalcio2");
    public final static OldChannel TVBLOB_PREMIUMCALCIO3 = new OldChannel("Premium Calcio 3", "http://tvblob.com/channel/premiumcalcio3", "premiumcalcio3");
    public final static OldChannel TVBLOB_PREMIUMCALCIO4 = new OldChannel("Premium Calcio 4", "http://tvblob.com/channel/premiumcalcio4", "premiumcalcio4");
    public final static OldChannel TVBLOB_PREMIUMCALCIO5 = new OldChannel("Premium Calcio 5", "http://tvblob.com/channel/premiumcalcio5", "premiumcalcio5");
    public final static OldChannel TVBLOB_PREMIUMCALCIO6 = new OldChannel("Premium Calcio 6", "http://tvblob.com/channel/premiumcalcio6", "premiumcalcio6");
    public final static OldChannel TVBLOB_PREMIUMCALCIOHD1 = new OldChannel("Premium Calcio HD 1", "http://tvblob.com/channel/premiumcalciohd1", "premiumcalciohd1");
    public final static OldChannel TVBLOB_PREMIUMCALCIOHD2 = new OldChannel("Premium Calcio HD 2", "http://tvblob.com/channel/premiumcalciohd2", "premiumcalciohd2");
    public final static OldChannel TVBLOB_PREMIUMCINEMA = new OldChannel("Premium Cinema", "http://tvblob.com/channel/premiumcinema", "premiumcinema");
    public final static OldChannel TVBLOB_PREMIUMCINEMAHD = new OldChannel("Premium Cinema HD", "http://tvblob.com/channel/premiumcinemahd", "premiumcinemahd");
    public final static OldChannel TVBLOB_PREMIUMEMOTION = new OldChannel("Premium Emotion", "http://tvblob.com/channel/premiumemotion", "premiumemotion");
    public final static OldChannel TVBLOB_PREMIUMENERGY = new OldChannel("Premium Energy", "http://tvblob.com/channel/premiumenergy", "premiumenergy");
    public final static OldChannel TVBLOB_PREMIUMEXTRA1 = new OldChannel("Premium Extra 1", "http://tvblob.com/channel/premiumextra1", "premiumextra1");
    public final static OldChannel TVBLOB_PREMIUMEXTRA2 = new OldChannel("Premium Extra 2", "http://tvblob.com/channel/premiumextra2", "premiumextra2");
    public final static OldChannel TVBLOB_PREMIUMMENU = new OldChannel("Premium Menu", "http://tvblob.com/channel/premiummenu", "premiummenu");
    public final static OldChannel TVBLOB_PREMIUMTEST = new OldChannel("Premium Test", "http://tvblob.com/channel/premiumtest", "premiumtest");
    public final static OldChannel TVBLOB_PRIMARETE = new OldChannel("Prima Rete", "http://tvblob.com/channel/primarete", "primarete");
    public final static OldChannel TVBLOB_PUNTOSAT = new OldChannel("PuntoSat", "http://tvblob.com/channel/puntosat", "puntosat");
    public final static OldChannel TVBLOB_QVC = new OldChannel("QVC", "http://tvblob.com/channel/qvc", "tvblobqvc");
    public final static OldChannel TVBLOB_RITALIASMI = new OldChannel("R Italia SMI", "http://tvblob.com/channel/ritaliasmi", "ritaliasmi");
    public final static OldChannel TVBLOB_RADIOALEX = new OldChannel("Radio Alex", "http://tvblob.com/channel/radioalex", "radioalex");
    public final static OldChannel TVBLOB_RADIOCAPITAL = new OldChannel("Radio Capital", "http://tvblob.com/channel/radiocapital", "radiocapital");
    public final static OldChannel TVBLOB_RADIOCAPRI = new OldChannel("Radio Capri", "http://tvblob.com/channel/radiocapri", "radiocapri");
    public final static OldChannel TVBLOB_RADIOCAPRITELEVISION = new OldChannel("Radio Capri TelevisiON", "http://tvblob.com/channel/radiocapritelevision", "radiocapritelevision");
    public final static OldChannel TVBLOB_RADIOCITY = new OldChannel("Radio City", "http://tvblob.com/channel/radiocity", "radiocity");
    public final static OldChannel TVBLOB_RADIODEEJAY = new OldChannel("Radio Deejay", "http://tvblob.com/channel/radiodeejay", "radiodeejay");
    public final static OldChannel TVBLOB_RADIODUE = new OldChannel("Radio Due", "http://tvblob.com/channel/radiodue", "radiodue");
    public final static OldChannel TVBLOB_RADIOM2O = new OldChannel("Radio m2o", "http://tvblob.com/channel/radiom2o", "radiom2o");
    public final static OldChannel TVBLOB_RADIOMARCONI = new OldChannel("Radio Marconi", "http://tvblob.com/channel/radiomarconi", "radiomarconi");
    public final static OldChannel TVBLOB_RADIOMARCONICLASSIC = new OldChannel("Radio Marconi Classic", "http://tvblob.com/channel/radiomarconiclassic", "radiomarconiclassic");
    public final static OldChannel TVBLOB_RADIOMARIA = new OldChannel("Radio Maria", "http://tvblob.com/channel/radiomaria", "radiomaria");
    public final static OldChannel TVBLOB_RADIOMATER = new OldChannel("Radio Mater", "http://tvblob.com/channel/radiomater", "radiomater");
    public final static OldChannel TVBLOB_RADIOMILANINTER = new OldChannel("Radio Milan Inter", "http://tvblob.com/channel/radiomilaninter", "radiomilaninter");
    public final static OldChannel TVBLOB_RADIOMILLENNIUM = new OldChannel("Radio Millennium", "http://tvblob.com/channel/radiomillennium", "radiomillennium");
    public final static OldChannel TVBLOB_RADIOSTAR = new OldChannel("Radio Star*", "http://tvblob.com/channel/radiostar", "radiostar");
    public final static OldChannel TVBLOB_RADIOTRE = new OldChannel("Radio Tre", "http://tvblob.com/channel/radiotre", "radiotre");
    public final static OldChannel TVBLOB_RADIOUNO = new OldChannel("Radio Uno", "http://tvblob.com/channel/radiouno", "radiouno");
    public final static OldChannel TVBLOB_RAIUNO = new OldChannel("Rai 1", "http://tvblob.com/channel/raiuno", "raiuno");
    public final static OldChannel TVBLOB_RAIDUE = new OldChannel("Rai 2", "http://tvblob.com/channel/raidue", "raidue");
    public final static OldChannel TVBLOB_RAITRE = new OldChannel("Rai 3", "http://tvblob.com/channel/raitre", "raitre");
    public final static OldChannel TVBLOB_RAI4 = new OldChannel("Rai 4", "http://tvblob.com/channel/rai4", "rai4");
    public final static OldChannel TVBLOB_RAI5 = new OldChannel("Rai 5", "http://tvblob.com/channel/rai5", "rai5");
    public final static OldChannel TVBLOB_RAISATEXTRA = new OldChannel("Rai Extra", "http://tvblob.com/channel/raisatextra", "raisatextra");
    public final static OldChannel TVBLOB_RAIGULP = new OldChannel("Rai Gulp", "http://tvblob.com/channel/raigulp", "raigulp");
    public final static OldChannel TVBLOB_RAIHD = new OldChannel("Rai HD", "http://tvblob.com/channel/raiHD", "raiHD");
    public final static OldChannel TVBLOB_RAI_MOVIE = new OldChannel("Rai Movie", "http://tvblob.com/channel/rai_movie", "rai_movie");
    public final static OldChannel TVBLOB_RAINEWS = new OldChannel("Rai News", "http://tvblob.com/channel/rainews", "rainews");
    public final static OldChannel TVBLOB_RAIPREMIUM = new OldChannel("Rai Premium", "http://tvblob.com/channel/raipremium", "raipremium");
    public final static OldChannel TVBLOB_RAISCUOLA = new OldChannel("Rai Scuola", "http://tvblob.com/channel/raiscuola", "raiscuola");
    public final static OldChannel TVBLOB_RAISPORTUNO = new OldChannel("Rai Sport 1", "http://tvblob.com/channel/raisportuno", "raisportuno");
    public final static OldChannel TVBLOB_RAISPORTDUE = new OldChannel("Rai Sport 2", "http://tvblob.com/channel/raisportdue", "raisportdue");
    public final static OldChannel TVBLOB_RAISTORIA = new OldChannel("Rai Storia", "http://tvblob.com/channel/raistoria", "raistoria");
    public final static OldChannel TVBLOB_RAIYOYO = new OldChannel("Rai Yoyo", "http://tvblob.com/channel/raiyoyo", "raiyoyo");
    public final static OldChannel TVBLOB_REALTIME = new OldChannel("Real Time", "http://tvblob.com/channel/realtime", "realtime");
    public final static OldChannel TVBLOB_REPUBBLICATV = new OldChannel("Repubblica TV", "http://tvblob.com/channel/repubblicatv", "repubblicatv");
    public final static OldChannel TVBLOB_RETE55 = new OldChannel("Rete 55", "http://tvblob.com/channel/rete55", "rete55");
    public final static OldChannel TVBLOB_RETECAPRI = new OldChannel("Rete Capri", "http://tvblob.com/channel/retecapri", "retecapri");
    public final static OldChannel TVBLOB_RETECAPRIPIUUNO = new OldChannel("Rete Capri +1", "http://tvblob.com/channel/retecapripiuuno", "retecapripiuuno");
    public final static OldChannel TVBLOB_RETE_4 = new OldChannel("Rete4", "http://tvblob.com/channel/rete_4", "rete_4");
    public final static OldChannel TVBLOB_RETE4_PIU1 = new OldChannel("Rete4 +1", "http://tvblob.com/channel/rete4_piu1", "rete4_piu1");
    public final static OldChannel TVBLOB_RETE4_HD = new OldChannel("Rete4 HD", "http://tvblob.com/channel/rete4_hd", "rete4_hd");
    public final static OldChannel TVBLOB_ROVI = new OldChannel("Rovi", "http://tvblob.com/channel/rovi", "rovi");
    public final static OldChannel TVBLOB_RSILA1 = new OldChannel("RSI LA 1", "http://tvblob.com/channel/rsila1", "rsila1");
    public final static OldChannel TVBLOB_RSILA2 = new OldChannel("RSI LA 2", "http://tvblob.com/channel/rsila2", "rsila2");
    public final static OldChannel TVBLOB_RTL1025 = new OldChannel("RTL 102.5", "http://tvblob.com/channel/rtl1025", "rtl1025");
    public final static OldChannel TVBLOB_SALUTEENATURA = new OldChannel("Salute e Natura", "http://tvblob.com/channel/saluteenatura", "saluteenatura");
    public final static OldChannel TVBLOB_SEXOAMATORIAL = new OldChannel("Sexo Amatorial", "http://tvblob.com/channel/sexoamatorial", "sexoamatorial");
    public final static OldChannel TVBLOB_SEXOEXCLUSIVE = new OldChannel("Sexo Exclusive", "http://tvblob.com/channel/sexoexclusive", "sexoexclusive");
    public final static OldChannel TVBLOB_SEXOEXOTICA = new OldChannel("Sexo Exotica", "http://tvblob.com/channel/sexoexotica", "sexoexotica");
    public final static OldChannel TVBLOB_SEXOTRANSGRESSION = new OldChannel("Sexo Transgression", "http://tvblob.com/channel/sexotransgression", "sexotransgression");
    public final static OldChannel TVBLOB_SF1 = new OldChannel("SF 1", "http://tvblob.com/channel/sf1", "sf1");
    public final static OldChannel TVBLOB_SMILETV = new OldChannel("Smile TV", "http://tvblob.com/channel/smiletv", "smiletv");
    public final static OldChannel TVBLOB_SPORTITALIA = new OldChannel("Sport Italia", "http://tvblob.com/channel/sportitalia", "sportitalia");
    public final static OldChannel TVBLOB_SPORTITALIA2 = new OldChannel("Sport Italia 2", "http://tvblob.com/channel/sportitalia2", "sportitalia2");
    public final static OldChannel TVBLOB_SPORTITALIA24 = new OldChannel("Sport Italia 24", "http://tvblob.com/channel/sportitalia24", "sportitalia24");
    public final static OldChannel TVBLOB_SRGSWISSPOP = new OldChannel("SRG - Swiss Pop", "http://tvblob.com/channel/srgswisspop", "srgswisspop");
    public final static OldChannel TVBLOB_STEEL = new OldChannel("Steel - Premium", "http://tvblob.com/channel/steel", "steel");
    public final static OldChannel TVBLOB_STEELPIUUNO = new OldChannel("Steel+1 - Premium", "http://tvblob.com/channel/steelpiuuno", "steelpiuuno");
    public final static OldChannel TVBLOB_STUDIO1 = new OldChannel("Studio 1", "http://tvblob.com/channel/studio1", "studio1");
    public final static OldChannel TVBLOB_STUDIO1HD = new OldChannel("Studio 1 HD", "http://tvblob.com/channel/studio1hd", "studio1hd");
    public final static OldChannel TVBLOB_STUDIO1TEST = new OldChannel("Studio 1 Test", "http://tvblob.com/channel/studio1test", "studio1test");
    public final static OldChannel TVBLOB_STUDIO7 = new OldChannel("Studio 7", "http://tvblob.com/channel/studio7", "studio7");
    public final static OldChannel TVBLOB_STUDIOSTORE = new OldChannel("Studio Store", "http://tvblob.com/channel/studiostore", "studiostore");
    public final static OldChannel TVBLOB_STUDIOUNIVERSAL = new OldChannel("Studio Universal - Premium", "http://tvblob.com/channel/studiouniversal", "studiouniversal");
    public final static OldChannel TVBLOB_STUDIONORDTV = new OldChannel("Studionord TV", "http://tvblob.com/channel/studionordtv", "studionordtv");
    public final static OldChannel TVBLOB_SUPERTENNIS = new OldChannel("Super tennis", "http://tvblob.com/channel/supertennis", "supertennis");
    public final static OldChannel TVBLOB_SUPERTV = new OldChannel("Super TV", "http://tvblob.com/channel/supertv", "supertv");
    public final static OldChannel TVBLOB_TBNE = new OldChannel("TBNE", "http://tvblob.com/channel/tbne", "tbne");
    public final static OldChannel TVBLOB_TELETV = new OldChannel("Tele TV", "http://tvblob.com/channel/teletv", "teletv");
    public final static OldChannel TVBLOB_TELECITY2MI = new OldChannel("Telecity 2 MI", "http://tvblob.com/channel/telecity2mi", "telecity2mi");
    public final static OldChannel TVBLOB_TELECITY7GOLD = new OldChannel("Telecity 7 Gold", "http://tvblob.com/channel/telecity7gold", "telecity7gold");
    public final static OldChannel TVBLOB_TELECITY7GOLDLOMBARDIA = new OldChannel("Telecity 7 Gold Lombardia", "http://tvblob.com/channel/telecity7goldlombardia", "telecity7goldlombardia");
    public final static OldChannel TVBLOB_TELECOLOR = new OldChannel("Telecolor", "http://tvblob.com/channel/telecolor", "telecolor");
    public final static OldChannel TVBLOB_TELELOMBARDIA = new OldChannel("Telelombardia", "http://tvblob.com/channel/telelombardia", "telelombardia");
    public final static OldChannel TVBLOB_TELEMARKET = new OldChannel("Telemarket", "http://tvblob.com/channel/telemarket", "telemarket");
    public final static OldChannel TVBLOB_TELEMARKET2 = new OldChannel("Telemarket 2", "http://tvblob.com/channel/telemarket2", "telemarket2");
    public final static OldChannel TVBLOB_TELEMILANOCITYPIUBLU = new OldChannel("TeleMilano City Più Blu", "http://tvblob.com/channel/telemilanocitypiublu", "telemilanocitypiublu");
    public final static OldChannel TVBLOB_TELENOVA = new OldChannel("Telenova", "http://tvblob.com/channel/telenova", "telenova");
    public final static OldChannel TVBLOB_TELENOVAPIU1 = new OldChannel("Telenova +1", "http://tvblob.com/channel/telenovapiu1", "telenovapiu1");
    public final static OldChannel TVBLOB_TELENOVA2 = new OldChannel("Telenova 2", "http://tvblob.com/channel/telenova2", "telenova2");
    public final static OldChannel TVBLOB_TELENOVA3 = new OldChannel("Telenova 3 Sport Action", "http://tvblob.com/channel/telenova3", "telenova3");
    public final static OldChannel TVBLOB_TELEPACE = new OldChannel("Telepace", "http://tvblob.com/channel/telepace", "telepace");
    public final static OldChannel TVBLOB_TELEREPORTER = new OldChannel("TeleReporter", "http://tvblob.com/channel/telereporter", "telereporter");
    public final static OldChannel TVBLOB_TELESTAR = new OldChannel("Telestar", "http://tvblob.com/channel/telestar", "telestar");
    public final static OldChannel TVBLOB_TELESTARMIPIUUNO = new OldChannel("Telestar +1 MI", "http://tvblob.com/channel/telestarmipiuuno", "telestarmipiuuno");
    public final static OldChannel TVBLOB_TELESTARMI = new OldChannel("Telestar MI", "http://tvblob.com/channel/telestarmi", "telestarmi");
    public final static OldChannel TVBLOB_TGMEDIASET = new OldChannel("TG Mediaset", "http://tvblob.com/channel/tgmediaset", "tgmediaset");
    public final static OldChannel TVBLOB_TGNORBA24 = new OldChannel("TG Norba 24", "http://tvblob.com/channel/tgnorba24", "tgnorba24");
    public final static OldChannel TVBLOB_TIVUITALIATEST4 = new OldChannel("Tivuitalia test 4", "http://tvblob.com/channel/tivuitaliatest4", "tivuitaliatest4");
    public final static OldChannel TVBLOB_TIVUITALIATEST5 = new OldChannel("Tivuitalia test 5", "http://tvblob.com/channel/tivuitaliatest5", "tivuitaliatest5");
    public final static OldChannel TVBLOB_TIVUITALIATEST6 = new OldChannel("Tivuitalia test 6", "http://tvblob.com/channel/tivuitaliatest6", "tivuitaliatest6");
    public final static OldChannel TVBLOB_TIVUITALIATEST7 = new OldChannel("Tivuitalia test 7", "http://tvblob.com/channel/tivuitaliatest7", "tivuitaliatest7");
    public final static OldChannel TVBLOB_TIVUITALIATEST8 = new OldChannel("Tivuitalia test 8", "http://tvblob.com/channel/tivuitaliatest8", "tivuitaliatest8");
    public final static OldChannel TVBLOB_TELECAMPIONE = new OldChannel("TLC Telecampione", "http://tvblob.com/channel/telecampione", "telecampione");
    public final static OldChannel TVBLOB_TOPCALCIO24 = new OldChannel("Top Calcio 24", "http://tvblob.com/channel/topcalcio24", "topcalcio24");
    public final static OldChannel TVBLOB_TOPMUSIC = new OldChannel("Top Music", "http://tvblob.com/channel/topmusic", "topmusic");
    public final static OldChannel TVBLOB_TOPTECH = new OldChannel("Top Tech", "http://tvblob.com/channel/toptech", "toptech");
    public final static OldChannel TVBLOB_TRS = new OldChannel("TRS", "http://tvblob.com/channel/trs", "trs");
    public final static OldChannel TVBLOB_TRSTV1 = new OldChannel("TRS TV 1", "http://tvblob.com/channel/trstv1", "trstv1");
    public final static OldChannel TVBLOB_TRSTV2 = new OldChannel("TRS TV 2", "http://tvblob.com/channel/trstv2", "trstv2");
    public final static OldChannel TVBLOB_TRSTVSERVIZIO = new OldChannel("TRS TV Servizio", "http://tvblob.com/channel/trstvservizio", "trstvservizio");
    public final static OldChannel TVBLOB_TRSTVTEST1 = new OldChannel("TRS TV test1", "http://tvblob.com/channel/trstvtest1", "trstvtest1");
    public final static OldChannel TVBLOB_TRSTVTEST2 = new OldChannel("TRS TV test2", "http://tvblob.com/channel/trstvtest2", "trstvtest2");
    public final static OldChannel TVBLOB_TSR1 = new OldChannel("TSR1", "http://tvblob.com/channel/tsr1", "tsr1");
    public final static OldChannel TVBLOB_TV2000 = new OldChannel("Tv 2000", "http://tvblob.com/channel/tv2000", "tv2000");
    public final static OldChannel TVBLOB_VERTIGOBLACK = new OldChannel("Vertigo Black", "http://tvblob.com/channel/vertigoblack", "vertigoblack");
    public final static OldChannel TVBLOB_VERTIGOTVIT = new OldChannel("VERTIGOTV.IT VM18", "http://tvblob.com/channel/vertigotvit", "vertigotvit");
    public final static OldChannel TVBLOB_VIAGGIANDOTV = new OldChannel("Viaggiando TV", "http://tvblob.com/channel/viaggiandotv", "viaggiandotv");
    public final static OldChannel TVBLOB_VIDEOSTAR = new OldChannel("Videostar", "http://tvblob.com/channel/videostar", "videostar");
    public final static OldChannel TVBLOB_VIRGINRADIOTV = new OldChannel("Virginradio TV", "http://tvblob.com/channel/virginradiotv", "virginradiotv");

    public final static OldChannel ONEHUNDREDPOINTFOUR_SMOOTH_RADIO = new OldChannel("100.4 Smooth Radio", CHANNEL_URI_PREFIX+"b", "b");
    public final static OldChannel ONEHUNDREDANDTWO_CAPITAL_FM = new OldChannel("102 Capital FM", CHANNEL_URI_PREFIX+"c", "c");
    public final static OldChannel ONEHUNDREDANDTWO_TOWN_FM = new OldChannel("102 Town FM", CHANNEL_URI_PREFIX+"d", "d");
    public final static OldChannel ONEHUNDREDANDTWOPOINTTWO_CAPITAL_FM = new OldChannel("102.2 Capital FM", CHANNEL_URI_PREFIX+"f", "f");
    public final static OldChannel ONEHUNDREDANDTWOPOINTTWO_SMOOTH_RADIO = new OldChannel("102.2 Smooth Radio", CHANNEL_URI_PREFIX+"g", "g");
    public final static OldChannel ONEHUNDREDANDTHREE_THE_EYE = new OldChannel("103 The Eye", CHANNEL_URI_PREFIX+"h", "h");
    public final static OldChannel ONEHUNDREDANDTHREE_POINT_TWO_CAPITAL_FM = new OldChannel("103.2 Capital FM", CHANNEL_URI_PREFIX+"j", "j");
    public final static OldChannel ONEHUNDREDANDFIVE_CAPITAL_FM = new OldChannel("105 Capital FM", CHANNEL_URI_PREFIX+"k", "k");
    public final static OldChannel ONEHUNDREDANDFIVE_TO_ONEHUNDREDANDSIX_CAPITAL_FM_SCOTLAND = new OldChannel("105-106 Capital FM Scotland", CHANNEL_URI_PREFIX+"m", "m");
    public final static OldChannel ONEHUNDREDANDFIVEPOINTTWO_SMOOTH_RADIO = new OldChannel("105.2 Smooth Radio", CHANNEL_URI_PREFIX+"n", "n");
    public final static OldChannel ONEHUNDREDANDFIVEPOINTSEVEN_SMOOTH_RADIO = new OldChannel("105.7 Smooth Radio", CHANNEL_URI_PREFIX+"p", "p");
    public final static OldChannel ONEHUNDREDANDFIVEORSIX_CAPITAL_FM = new OldChannel("105/106 Capital FM", CHANNEL_URI_PREFIX+"2", "2");
    public final static OldChannel ONEHUNDREDANDSIXPOINTONE_ROCK_RADIO = new OldChannel("106.1 Rock Radio", CHANNEL_URI_PREFIX+"r", "r");
    public final static OldChannel ONEHUNDREDANDSIXPOINTSIX_SMOOTH_RADIO = new OldChannel("106.6 Smooth Radio", CHANNEL_URI_PREFIX+"s", "s");
    public final static OldChannel ONEHUNDREDANDSEVENPOINTFOUR_THE_SEVERN = new OldChannel("107.4 The Severn", CHANNEL_URI_PREFIX+"t", "t");
    public final static OldChannel ONEHUNDREDANDSEVENPOINTSIX_JUICE_FM = new OldChannel("107.6 Juice FM", CHANNEL_URI_PREFIX+"v", "v");
    public final static OldChannel TWO_FM = new OldChannel("2 FM", CHANNEL_URI_PREFIX+"w", "w");
    public final static OldChannel TWOBR = new OldChannel("2BR", CHANNEL_URI_PREFIX+"x", "x");
    public final static OldChannel FOURFM = new OldChannel("4FM", CHANNEL_URI_PREFIX+"y", "y");
    public final static OldChannel NINETYSIXTOONEHUNDREDANDSIX_CAPITAL_FM = new OldChannel("96-106 Capital FM", CHANNEL_URI_PREFIX+"z", "z");
    public final static OldChannel NINETYSIXPOINTTHREE_ROCK_RADIO = new OldChannel("96.3 Rock Radio", CHANNEL_URI_PREFIX+"4", "4");
    public final static OldChannel NINETYSIXPOINTFIVE_RADIO_WAVE = new OldChannel("96.5 Radio Wave", CHANNEL_URI_PREFIX+"5", "5");
    public final static OldChannel NINETYSEVENPOINTFOUR_AND_103_2_CAPITAL_FM = new OldChannel("97.4/103.2 Capital FM", CHANNEL_URI_PREFIX+"6", "6");
    public final static OldChannel NINETYSEVENPOINTFIVE_KEMET_FM = new OldChannel("97.5 Kemet FM", CHANNEL_URI_PREFIX+"7", "7");
    public final static OldChannel NINETYSEVENPOINTFIVE_SMOOTH_RADIO = new OldChannel("97.5 Smooth Radio", CHANNEL_URI_PREFIX+"8", "8");
    public final static OldChannel NINETYEIGHTPOINTTHREE_FM_CORK_CAMPUS_RADIO = new OldChannel("98.3FM Cork Campus Radio", CHANNEL_URI_PREFIX+"9", "9");
    public final static OldChannel NINETYEIGHTFM = new OldChannel("98FM", CHANNEL_URI_PREFIX+"cb", "cb");
    public final static OldChannel ABSOLUTE_XTREME = new OldChannel("Absolute Xtreme", CHANNEL_URI_PREFIX+"cd", "cd");
    public final static OldChannel AMAZING_RADIO = new OldChannel("Amazing Radio", CHANNEL_URI_PREFIX+"cf", "cf");
    public final static OldChannel ARROW = new OldChannel("Arrow", CHANNEL_URI_PREFIX+"cg", "cg");
    public final static OldChannel ARROW_FM = new OldChannel("Arrow FM", CHANNEL_URI_PREFIX+"ch", "ch");
    public final static OldChannel BAY = new OldChannel("Bay", CHANNEL_URI_PREFIX+"cj", "cj");
    public final static OldChannel BCB_106_6FM = new OldChannel("BCB 106.6FM", CHANNEL_URI_PREFIX+"cp", "cp");
    public final static OldChannel BEACH_103_4FM = new OldChannel("Beach 103.4FM", CHANNEL_URI_PREFIX+"cq", "cq");
    public final static OldChannel BEACON = new OldChannel("Beacon", CHANNEL_URI_PREFIX+"cr", "cr");
    public final static OldChannel BLACK_DIAMOND = new OldChannel("Black Diamond", CHANNEL_URI_PREFIX+"cs", "cs");
    public final static OldChannel BLAST_FM = new OldChannel("Blast FM", CHANNEL_URI_PREFIX+"ct", "ct");
    public final static OldChannel BORDERS_RADIO = new OldChannel("Borders Radio", CHANNEL_URI_PREFIX+"cv", "cv");
    public final static OldChannel BRIGHT_106_4 = new OldChannel("Bright 106.4", CHANNEL_URI_PREFIX+"cw", "cw");
    public final static OldChannel BRMB_96_4 = new OldChannel("BRMB 96.4", CHANNEL_URI_PREFIX+"cx", "cx");
    public final static OldChannel BUZZ_ASIA = new OldChannel("Buzz Asia", CHANNEL_URI_PREFIX+"cy", "cy");
    public final static OldChannel C103_NORTH_AND_EAST_CORK = new OldChannel("C103 (North and East Cork)", CHANNEL_URI_PREFIX+"cz", "cz");
    public final static OldChannel C103_WEST_CORK = new OldChannel("C103 (West Cork)", CHANNEL_URI_PREFIX+"c2", "c2");
    public final static OldChannel CENTRAL_FM = new OldChannel("Central FM", CHANNEL_URI_PREFIX+"c5", "c5");
    public final static OldChannel CEREDIGION = new OldChannel("Ceredigion", CHANNEL_URI_PREFIX+"c6", "c6");
    public final static OldChannel CFM = new OldChannel("CFM", CHANNEL_URI_PREFIX+"c7", "c7");
    public final static OldChannel CHANNEL_103 = new OldChannel("Channel 103", CHANNEL_URI_PREFIX+"c8", "c8");
    public final static OldChannel CHELMSFORD_RADIO = new OldChannel("Chelmsford Radio", CHANNEL_URI_PREFIX+"c9", "c9");
    public final static OldChannel CHILL = new OldChannel("Chill", CHANNEL_URI_PREFIX+"db", "db");
    public final static OldChannel CHOICE = new OldChannel("Choice", CHANNEL_URI_PREFIX+"dc", "dc");
    public final static OldChannel CHOICE_FM = new OldChannel("Choice FM", CHANNEL_URI_PREFIX+"dd", "dd");
    public final static OldChannel CITY_BEAT_96_7_FM = new OldChannel("City Beat 96.7 FM", CHANNEL_URI_PREFIX+"df", "df");
    public final static OldChannel CITY_TALK_105_9 = new OldChannel("City Talk 105.9", CHANNEL_URI_PREFIX+"dg", "dg");
    public final static OldChannel CLARE_FM = new OldChannel("Clare FM", CHANNEL_URI_PREFIX+"dh", "dh");
    public final static OldChannel CLYDE_1 = new OldChannel("Clyde 1", CHANNEL_URI_PREFIX+"dj", "dj");
    public final static OldChannel CLYDE_2 = new OldChannel("Clyde 2", CHANNEL_URI_PREFIX+"dk", "dk");
    public final static OldChannel COAST_106 = new OldChannel("Coast 106", CHANNEL_URI_PREFIX+"dm", "dm");
    public final static OldChannel COASTLINE_RADIO = new OldChannel("Coastline Radio", CHANNEL_URI_PREFIX+"dn", "dn");
    public final static OldChannel COMPASS_FM = new OldChannel("Compass FM", CHANNEL_URI_PREFIX+"dp", "dp");
    public final static OldChannel CONNECT_FM = new OldChannel("Connect FM", CHANNEL_URI_PREFIX+"dq", "dq");
    public final static OldChannel CONNECT_FM_106_8 = new OldChannel("Connect FM 106.8", CHANNEL_URI_PREFIX+"dr", "dr");
    public final static OldChannel COOL_FM = new OldChannel("Cool FM", CHANNEL_URI_PREFIX+"ds", "ds");
    public final static OldChannel CORKS_96FM = new OldChannel("Cork's 96FM", CHANNEL_URI_PREFIX+"dt", "dt");
    public final static OldChannel COUNTY_SOUND_RADIO = new OldChannel("County Sound Radio", CHANNEL_URI_PREFIX+"dv", "dv");
    public final static OldChannel COVENTRY_AND_WARWICKSHIRE_FM = new OldChannel("Coventry and Warwickshire FM", CHANNEL_URI_PREFIX+"dw", "dw");
    public final static OldChannel CUH_FM_107_8 = new OldChannel("CUH FM 107.8", CHANNEL_URI_PREFIX+"dx", "dx");
    public final static OldChannel DERBYS_RAM_FM = new OldChannel("Derby's Ram FM", CHANNEL_URI_PREFIX+"dy", "dy");
    public final static OldChannel DEVON_MW = new OldChannel("Devon MW", CHANNEL_URI_PREFIX+"dz", "dz");
    public final static OldChannel DOWNTOWN_RADIO = new OldChannel("Downtown Radio", CHANNEL_URI_PREFIX+"d2", "d2");
    public final static OldChannel DREAM_100 = new OldChannel("Dream 100", CHANNEL_URI_PREFIX+"d4", "d4");
    public final static OldChannel DUBLIN_CITY_FM = new OldChannel("Dublin City FM", CHANNEL_URI_PREFIX+"d5", "d5");
    public final static OldChannel DUNE_FM = new OldChannel("Dune FM", CHANNEL_URI_PREFIX+"d6", "d6");
    public final static OldChannel EAGLE_96_4 = new OldChannel("Eagle 96.4", CHANNEL_URI_PREFIX+"d7", "d7");
    public final static OldChannel EAST_COAST_RADIO = new OldChannel("East Coast Radio", CHANNEL_URI_PREFIX+"d8", "d8");
    public final static OldChannel FIRE = new OldChannel("Fire", CHANNEL_URI_PREFIX+"d9", "d9");
    public final static OldChannel FM_104 = new OldChannel("FM 104", CHANNEL_URI_PREFIX+"fb", "fb");
    public final static OldChannel FOREST_FM = new OldChannel("Forest FM", CHANNEL_URI_PREFIX+"fc", "fc");
    public final static OldChannel FORTH_2 = new OldChannel("Forth 2", CHANNEL_URI_PREFIX+"fd", "fd");
    public final static OldChannel FORTH_ONE = new OldChannel("Forth One", CHANNEL_URI_PREFIX+"ff", "ff");
    public final static OldChannel FRESH_AM = new OldChannel("Fresh AM", CHANNEL_URI_PREFIX+"fg", "fg");
    public final static OldChannel FUN_KIDS = new OldChannel("Fun Kids", CHANNEL_URI_PREFIX+"fh", "fh");
    public final static OldChannel FUTURE_RADIO = new OldChannel("future radio", CHANNEL_URI_PREFIX+"fj", "fj");
    public final static OldChannel GALWAY_BAY_FM_95_8 = new OldChannel("Galway Bay FM 95.8", CHANNEL_URI_PREFIX+"fk", "fk");
    public final static OldChannel GEM_106 = new OldChannel("Gem 106", CHANNEL_URI_PREFIX+"fm", "fm");
    public final static OldChannel GLIDE_FM = new OldChannel("Glide FM", CHANNEL_URI_PREFIX+"fn", "fn");
    public final static OldChannel GOLD_RADIO = new OldChannel("Gold", CHANNEL_URI_PREFIX+"fp", "fp");
    public final static OldChannel HALLAM_FM = new OldChannel("Hallam FM", CHANNEL_URI_PREFIX+"fq", "fq");
    public final static OldChannel HEART_100_5_101_9 = new OldChannel("Heart 100.5/101.9", CHANNEL_URI_PREFIX+"fr", "fr");
    public final static OldChannel HEART_102_3 = new OldChannel("Heart 102.3", CHANNEL_URI_PREFIX+"fs", "fs");
    public final static OldChannel HEART_102_4 = new OldChannel("Heart 102.4", CHANNEL_URI_PREFIX+"ft", "ft");
    public final static OldChannel HEART_102_6_97_4 = new OldChannel("Heart 102.6/97.4", CHANNEL_URI_PREFIX+"fv", "fv");
    public final static OldChannel HEART_102_7 = new OldChannel("Heart 102.7", CHANNEL_URI_PREFIX+"fw", "fw");
    public final static OldChannel HEART_103 = new OldChannel("Heart 103", CHANNEL_URI_PREFIX+"f6", "f6");
    public final static OldChannel HEART_103_4 = new OldChannel("Heart 103.4", CHANNEL_URI_PREFIX+"fy", "fy");
    public final static OldChannel HEART_103_97_4 = new OldChannel("Heart 103/97.4", CHANNEL_URI_PREFIX+"fz", "fz");
    public final static OldChannel HEART_96_1 = new OldChannel("Heart 96.1", CHANNEL_URI_PREFIX+"f2", "f2");
    public final static OldChannel HEART_96_2_97_3 = new OldChannel("Heart 96.2/97.3", CHANNEL_URI_PREFIX+"f4", "f4");
    public final static OldChannel HEART_96_3 = new OldChannel("Heart 96.3", CHANNEL_URI_PREFIX+"f5", "f5");
    public final static OldChannel HEART_96_3_102_6 = new OldChannel("Heart 96.3/102.6", CHANNEL_URI_PREFIX+"f7", "f7");
    public final static OldChannel HEART_96_4FM = new OldChannel("Heart 96.4FM", CHANNEL_URI_PREFIX+"f8", "f8");
    public final static OldChannel HEART_96_5_97_1_102_6 = new OldChannel("Heart 96.5/97.1/102.6", CHANNEL_URI_PREFIX+"f9", "f9");
    public final static OldChannel HEART_96_6_97FM = new OldChannel("Heart 96.6/97FM", CHANNEL_URI_PREFIX+"gb", "gb");
    public final static OldChannel HEART_96_7_97_5 = new OldChannel("Heart 96.7/97.5", CHANNEL_URI_PREFIX+"gc", "gc");
    public final static OldChannel HEART_97_1 = new OldChannel("Heart 97.1", CHANNEL_URI_PREFIX+"gd", "gd");
    public final static OldChannel HEART_97_1_96_4 = new OldChannel("Heart 97.1/96.4", CHANNEL_URI_PREFIX+"gf", "gf");
    public final static OldChannel HEART_97_103FM = new OldChannel("Heart 97/103FM", CHANNEL_URI_PREFIX+"gg", "gg");
    public final static OldChannel HEART_ANGLESEY_AND_GWYNEDD = new OldChannel("Heart Anglesey and Gwynedd", CHANNEL_URI_PREFIX+"gh", "gh");
    public final static OldChannel HEART_BERKSHIRE_AND_NORTH_HAMPSHIRE = new OldChannel("Heart Berkshire and North Hampshire", CHANNEL_URI_PREFIX+"gj", "gj");
    public final static OldChannel HEART_GLOUCESTERSHIRE = new OldChannel("Heart Gloucestershire", CHANNEL_URI_PREFIX+"gk", "gk");
    public final static OldChannel HEART_HOME_COUNTIES = new OldChannel("Heart Home Counties", CHANNEL_URI_PREFIX+"gm", "gm");
    public final static OldChannel HEART_KENT = new OldChannel("Heart Kent", CHANNEL_URI_PREFIX+"gn", "gn");
    public final static OldChannel HEART_SUSSEX = new OldChannel("Heart Sussex", CHANNEL_URI_PREFIX+"gp", "gp");
    public final static OldChannel HEART_WEST_MIDLANDS = new OldChannel("Heart West Midlands", CHANNEL_URI_PREFIX+"gr", "gr");
    public final static OldChannel HEART_WILTSHIRE = new OldChannel("Heart Wiltshire", CHANNEL_URI_PREFIX+"gs", "gs");
    public final static OldChannel HEARTLAND = new OldChannel("Heartland", CHANNEL_URI_PREFIX+"gt", "gt");
    public final static OldChannel HOPE_FM = new OldChannel("Hope FM", CHANNEL_URI_PREFIX+"gw", "gw");
    public final static OldChannel IMAGINE_FM = new OldChannel("Imagine FM", CHANNEL_URI_PREFIX+"gx", "gx");
    public final static OldChannel IPSWICH_COMMUNITY_RADIO = new OldChannel("Ipswich Community Radio", CHANNEL_URI_PREFIX+"gy", "gy");
    public final static OldChannel ISLAND_FM = new OldChannel("Island FM", CHANNEL_URI_PREFIX+"gz", "gz");
    public final static OldChannel ISLE_OF_WIGHT_RADIO = new OldChannel("Isle of Wight Radio", CHANNEL_URI_PREFIX+"g2", "g2");
    public final static OldChannel ISLES_FM = new OldChannel("Isles FM", CHANNEL_URI_PREFIX+"g4", "g4");
    public final static OldChannel JACK_FM = new OldChannel("Jack FM", CHANNEL_URI_PREFIX+"g5", "g5");
    public final static OldChannel JAZZ_FM = new OldChannel("Jazz FM", CHANNEL_URI_PREFIX+"g6", "g6");
    public final static OldChannel JUICE_107_2 = new OldChannel("Juice 107.2", CHANNEL_URI_PREFIX+"g7", "g7");
    public final static OldChannel KCFM = new OldChannel("KCFM", CHANNEL_URI_PREFIX+"g8", "g8");
    public final static OldChannel KEY_103 = new OldChannel("Key 103", CHANNEL_URI_PREFIX+"g9", "g9");
    public final static OldChannel KFM = new OldChannel("KFM", CHANNEL_URI_PREFIX+"hb", "hb");
    public final static OldChannel KINGDOM_FM = new OldChannel("Kingdom FM", CHANNEL_URI_PREFIX+"hc", "hc");
    public final static OldChannel KINGSTOWN = new OldChannel("Kingstown", CHANNEL_URI_PREFIX+"hd", "hd");
    public final static OldChannel KISS_101 = new OldChannel("Kiss 101", CHANNEL_URI_PREFIX+"hf", "hf");
    public final static OldChannel KISS_105_108 = new OldChannel("Kiss 105-108", CHANNEL_URI_PREFIX+"hg", "hg");
    public final static OldChannel KLFM = new OldChannel("KLFM", CHANNEL_URI_PREFIX+"hh", "hh");
    public final static OldChannel KMFM_105_6 = new OldChannel("KMFM 105.6", CHANNEL_URI_PREFIX+"hj", "hj");
    public final static OldChannel KMFM_106 = new OldChannel("KMFM 106", CHANNEL_URI_PREFIX+"hk", "hk");
    public final static OldChannel KMFM_107_2 = new OldChannel("KMFM 107.2", CHANNEL_URI_PREFIX+"hm", "hm");
    public final static OldChannel KMFM_107_6 = new OldChannel("KMFM 107.6", CHANNEL_URI_PREFIX+"hn", "hn");
    public final static OldChannel KMFM_107_9_100_4 = new OldChannel("KMFM 107.9/100.4", CHANNEL_URI_PREFIX+"hp", "hp");
    public final static OldChannel KMFM_96_2_101_6 = new OldChannel("KMFM 96.2/101.6", CHANNEL_URI_PREFIX+"hq", "hq");
    public final static OldChannel KMFM_96_4_106_8 = new OldChannel("KMFM 96.4/106.8", CHANNEL_URI_PREFIX+"hr", "hr");
    public final static OldChannel LBC_97_3 = new OldChannel("LBC 97.3", CHANNEL_URI_PREFIX+"hs", "hs");
    public final static OldChannel LBC_NEWS_1152 = new OldChannel("LBC News 1152", CHANNEL_URI_PREFIX+"ht", "ht");
    public final static OldChannel LEITH = new OldChannel("Leith", CHANNEL_URI_PREFIX+"hv", "hv");
    public final static OldChannel LIFE_FM_93_1 = new OldChannel("Life FM 93.1", CHANNEL_URI_PREFIX+"hw", "hw");
    public final static OldChannel LINCS_FM = new OldChannel("Lincs FM", CHANNEL_URI_PREFIX+"hx", "hx");
    public final static OldChannel LIVE_95 = new OldChannel("Live 95", CHANNEL_URI_PREFIX+"hy", "hy");
    public final static OldChannel LM_FM_RADIO = new OldChannel("LM FM Radio", CHANNEL_URI_PREFIX+"hz", "hz");
    public final static OldChannel LOCHBROOM_FM = new OldChannel("Lochbroom FM", CHANNEL_URI_PREFIX+"h2", "h2");
    public final static OldChannel MAGIC_105_4 = new OldChannel("Magic 105.4", CHANNEL_URI_PREFIX+"h4", "h4");
    public final static OldChannel MAGIC_1152_MANCHESTER = new OldChannel("Magic 1152 (Manchester)", CHANNEL_URI_PREFIX+"h5", "h5");
    public final static OldChannel MAGIC_1152_AM = new OldChannel("Magic 1152 AM", CHANNEL_URI_PREFIX+"h6", "h6");
    public final static OldChannel MAGIC_1170 = new OldChannel("Magic 1170", CHANNEL_URI_PREFIX+"h7", "h7");
    public final static OldChannel MAGIC_1548 = new OldChannel("Magic 1548", CHANNEL_URI_PREFIX+"h8", "h8");
    public final static OldChannel MAGIC_828 = new OldChannel("Magic 828", CHANNEL_URI_PREFIX+"h9", "h9");
    public final static OldChannel MAGIC_999 = new OldChannel("Magic 999", CHANNEL_URI_PREFIX+"jb", "jb");
    public final static OldChannel MAGIC_AM = new OldChannel("Magic AM", CHANNEL_URI_PREFIX+"jc", "jc");
    public final static OldChannel MAGIC_DIGITAL = new OldChannel("Magic Digital", CHANNEL_URI_PREFIX+"jd", "jd");
    public final static OldChannel MANSFIELD_103_2 = new OldChannel("Mansfield 103.2", CHANNEL_URI_PREFIX+"jf", "jf");
    public final static OldChannel MERCIA_FM = new OldChannel("Mercia FM", CHANNEL_URI_PREFIX+"jg", "jg");
    public final static OldChannel METRO = new OldChannel("Metro", CHANNEL_URI_PREFIX+"jh", "jh");
    public final static OldChannel MIDWEST_RADIO = new OldChannel("Midwest Radio", CHANNEL_URI_PREFIX+"jj", "jj");
    public final static OldChannel MINSTER_FM = new OldChannel("Minster FM", CHANNEL_URI_PREFIX+"jk", "jk");
    public final static OldChannel MORAY_FIRTH_RADIO_FM = new OldChannel("Moray Firth Radio FM", CHANNEL_URI_PREFIX+"jm", "jm");
    public final static OldChannel MORAY_FIRTH_RADIO_MW = new OldChannel("Moray Firth Radio MW", CHANNEL_URI_PREFIX+"jn", "jn");
    public final static OldChannel NECR_102_1FM = new OldChannel("NECR 102.1FM", CHANNEL_URI_PREFIX+"jp", "jp");
    public final static OldChannel NENE_VALLEY = new OldChannel("Nene Valley", CHANNEL_URI_PREFIX+"jq", "jq");
    public final static OldChannel NEVIS_RADIO = new OldChannel("Nevis Radio", CHANNEL_URI_PREFIX+"jr", "jr");
    public final static OldChannel NEWPORT_CITY_RADIO = new OldChannel("Newport City Radio", CHANNEL_URI_PREFIX+"js", "js");
    public final static OldChannel NEWSTALK_106_108_FM = new OldChannel("Newstalk 106-108 FM", CHANNEL_URI_PREFIX+"jt", "jt");
    public final static OldChannel NME_RADIO = new OldChannel("NME Radio", CHANNEL_URI_PREFIX+"jv", "jv");
    public final static OldChannel NORTH_NORFOLK_RADIO = new OldChannel("North Norfolk Radio", CHANNEL_URI_PREFIX+"jw", "jw");
    public final static OldChannel NORTHSOUND_ONE = new OldChannel("Northsound One", CHANNEL_URI_PREFIX+"jx", "jx");
    public final static OldChannel NORTHSOUND_TWO = new OldChannel("Northsound Two", CHANNEL_URI_PREFIX+"jy", "jy");
    public final static OldChannel ONDA_CERO_MARBELLA = new OldChannel("Onda Cero Marbella", CHANNEL_URI_PREFIX+"jz", "jz");
    public final static OldChannel PEAK_107FM = new OldChannel("Peak 107FM", CHANNEL_URI_PREFIX+"j2", "j2");
    public final static OldChannel PIRATE_FM = new OldChannel("Pirate FM", CHANNEL_URI_PREFIX+"j4", "j4");
    public final static OldChannel PLANET_ROCK = new OldChannel("Planet Rock", CHANNEL_URI_PREFIX+"j5", "j5");
    public final static OldChannel PREMIER_CHRISTIAN_RADIO = new OldChannel("Premier Christian Radio", CHANNEL_URI_PREFIX+"j6", "j6");
    public final static OldChannel PULSE = new OldChannel("Pulse", CHANNEL_URI_PREFIX+"j7", "j7");
    public final static OldChannel PULSE_2 = new OldChannel("Pulse 2", CHANNEL_URI_PREFIX+"j8", "j8");
    public final static OldChannel Q102 = new OldChannel("Q102", CHANNEL_URI_PREFIX+"j9", "j9");
    public final static OldChannel Q102_9 = new OldChannel("Q102.9", CHANNEL_URI_PREFIX+"kb", "kb");
    public final static OldChannel RADIO_AIRE = new OldChannel("Radio Aire", CHANNEL_URI_PREFIX+"kc", "kc");
    public final static OldChannel RADIO_ALTEA = new OldChannel("Radio Altea", CHANNEL_URI_PREFIX+"kd", "kd");
    public final static OldChannel RADIO_CAVELL = new OldChannel("Radio Cavell", CHANNEL_URI_PREFIX+"kf", "kf");
    public final static OldChannel RADIO_CITY_96_7 = new OldChannel("Radio City 96.7", CHANNEL_URI_PREFIX+"kg", "kg");
    public final static OldChannel RADIO_FIREBIRD = new OldChannel("Radio Firebird", CHANNEL_URI_PREFIX+"kh", "kh");
    public final static OldChannel RADIO_GRAPEVINE = new OldChannel("Radio Grapevine", CHANNEL_URI_PREFIX+"kj", "kj");
    public final static OldChannel RADIO_GWENDOLEN = new OldChannel("Radio Gwendolen", CHANNEL_URI_PREFIX+"kk", "kk");
    public final static OldChannel RADIO_KERRY = new OldChannel("Radio Kerry", CHANNEL_URI_PREFIX+"km", "km");
    public final static OldChannel RADIO_LINK = new OldChannel("Radio Link", CHANNEL_URI_PREFIX+"kn", "kn");
    public final static OldChannel RADIO_MALDWYN = new OldChannel("Radio Maldwyn", CHANNEL_URI_PREFIX+"kp", "kp");
    public final static OldChannel RADIO_NORWICH = new OldChannel("Radio Norwich", CHANNEL_URI_PREFIX+"kq", "kq");
    public final static OldChannel RADIO_ORKNEY_MW = new OldChannel("Radio Orkney MW", CHANNEL_URI_PREFIX+"kr", "kr");
    public final static OldChannel RADIO_PEMBROKESHIRE = new OldChannel("Radio Pembrokeshire", CHANNEL_URI_PREFIX+"ks", "ks");
    public final static OldChannel RADIO_SALOBRENA = new OldChannel("Radio Salobrena", CHANNEL_URI_PREFIX+"kt", "kt");
    public final static OldChannel RADIO_SHETLAND_MW = new OldChannel("Radio Shetland MW", CHANNEL_URI_PREFIX+"kv", "kv");
    public final static OldChannel RADIO_WAVE = new OldChannel("Radio Wave", CHANNEL_URI_PREFIX+"kw", "kw");
    public final static OldChannel RADIO_XL = new OldChannel("Radio XL", CHANNEL_URI_PREFIX+"kx", "kx");
    public final static OldChannel REAL_RADIO_NORTH_EAST = new OldChannel("Real Radio (North East)", CHANNEL_URI_PREFIX+"ky", "ky");
    public final static OldChannel REAL_RADIO_NORTH_WEST = new OldChannel("Real Radio (North West)", CHANNEL_URI_PREFIX+"kz", "kz");
    public final static OldChannel REAL_RADIO_SCOTLAND = new OldChannel("Real Radio (Scotland)", CHANNEL_URI_PREFIX+"gq", "gq");
    public final static OldChannel REAL_RADIO_WALES = new OldChannel("Real Radio (Wales)", CHANNEL_URI_PREFIX+"k4", "k4");
    public final static OldChannel REAL_RADIO_YORKSHIRE = new OldChannel("Real Radio (Yorkshire)", CHANNEL_URI_PREFIX+"k5", "k5");
    public final static OldChannel RED_DOT_RADIO = new OldChannel("Red Dot Radio", CHANNEL_URI_PREFIX+"k6", "k6");
    public final static OldChannel RED_FM = new OldChannel("Red FM", CHANNEL_URI_PREFIX+"k7", "k7");
    public final static OldChannel REVOLUTION = new OldChannel("Revolution", CHANNEL_URI_PREFIX+"k8", "k8");
    public final static OldChannel RIVIERA_RADIO = new OldChannel("Riviera Radio", CHANNEL_URI_PREFIX+"k9", "k9");
    public final static OldChannel ROCK_FM = new OldChannel("Rock FM", CHANNEL_URI_PREFIX+"mb", "mb");
    public final static OldChannel RTE_LYRIC_FM = new OldChannel("RTE lyric fm", CHANNEL_URI_PREFIX+"mc", "mc");
    public final static OldChannel RTE_RADIO_1_FM = new OldChannel("RTE Radio 1 FM", CHANNEL_URI_PREFIX+"md", "md");
    public final static OldChannel RTE_RADIO_1_LW = new OldChannel("RTE Radio 1 LW", CHANNEL_URI_PREFIX+"mf", "mf");
    public final static OldChannel RTE_RAIDIO_NA_GAELTACHTA = new OldChannel("RTE Raidió na Gaeltachta", CHANNEL_URI_PREFIX+"mg", "mg");
    public final static OldChannel RUGBY_FM = new OldChannel("Rugby FM", CHANNEL_URI_PREFIX+"mh", "mh");
    public final static OldChannel SABRAS = new OldChannel("Sabras", CHANNEL_URI_PREFIX+"mj", "mj");
    public final static OldChannel SIGNAL_ONE_102_6 = new OldChannel("Signal One 102.6", CHANNEL_URI_PREFIX+"mk", "mk");
    public final static OldChannel SIGNAL_ONE_FOR_STAFFORD_96_9FM = new OldChannel("Signal One for Stafford 96.9FM", CHANNEL_URI_PREFIX+"mm", "mm");
    public final static OldChannel SIGNAL_TWO = new OldChannel("Signal Two", CHANNEL_URI_PREFIX+"mn", "mn");
    public final static OldChannel SOUTH_EAST_RADIO = new OldChannel("South East Radio", CHANNEL_URI_PREFIX+"mq", "mq");
    public final static OldChannel SOVEREIGN = new OldChannel("Sovereign", CHANNEL_URI_PREFIX+"mr", "mr");
    public final static OldChannel SPECTRUM_558_AM_RADIO = new OldChannel("Spectrum 558 AM Radio", CHANNEL_URI_PREFIX+"ms", "ms");
    public final static OldChannel SPECTRUM_FM = new OldChannel("Spectrum FM", CHANNEL_URI_PREFIX+"mt", "mt");
    public final static OldChannel SPIN_1038 = new OldChannel("Spin 1038", CHANNEL_URI_PREFIX+"mv", "mv");
    public final static OldChannel SPIRE_FM_RADIO = new OldChannel("Spire FM Radio", CHANNEL_URI_PREFIX+"mw", "mw");
    public final static OldChannel SPIRIT_FM_96_6_AND_102_3 = new OldChannel("Spirit FM 96.6 and 102.3", CHANNEL_URI_PREFIX+"mx", "mx");
    public final static OldChannel SPLASH_FM = new OldChannel("Splash FM", CHANNEL_URI_PREFIX+"my", "my");
    public final static OldChannel STAR_107_CAMBRIDGE = new OldChannel("Star 107 (Cambridge)", CHANNEL_URI_PREFIX+"mz", "mz");
    public final static OldChannel STAR_107_2 = new OldChannel("Star 107.2", CHANNEL_URI_PREFIX+"m2", "m2");
    public final static OldChannel STAR_107_7 = new OldChannel("Star 107.7", CHANNEL_URI_PREFIX+"m4", "m4");
    public final static OldChannel STAR_RADIO = new OldChannel("Star Radio", CHANNEL_URI_PREFIX+"m5", "m5");
    public final static OldChannel STRAY = new OldChannel("Stray", CHANNEL_URI_PREFIX+"m6", "m6");
    public final static OldChannel SUN_FM_103_4 = new OldChannel("Sun FM 103.4", CHANNEL_URI_PREFIX+"m7", "m7");
    public final static OldChannel SUNRISE_RADIO = new OldChannel("Sunrise Radio", CHANNEL_URI_PREFIX+"m8", "m8");
    public final static OldChannel SUNRISE_RADIO_103_2FM = new OldChannel("Sunrise Radio 103.2FM", CHANNEL_URI_PREFIX+"m9", "m9");
    public final static OldChannel SUNSHINE_855 = new OldChannel("Sunshine 855", CHANNEL_URI_PREFIX+"nb", "nb");
    public final static OldChannel SWANSEA = new OldChannel("Swansea", CHANNEL_URI_PREFIX+"nc", "nc");
    public final static OldChannel TAY_AM = new OldChannel("Tay AM", CHANNEL_URI_PREFIX+"nd", "nd");
    public final static OldChannel TAY_FM = new OldChannel("Tay FM", CHANNEL_URI_PREFIX+"nf", "nf");
    public final static OldChannel TEMPO_FM = new OldChannel("Tempo FM", CHANNEL_URI_PREFIX+"ng", "ng");
    public final static OldChannel TFM_RADIO = new OldChannel("TFM Radio", CHANNEL_URI_PREFIX+"nh", "nh");
    public final static OldChannel THE_BEE = new OldChannel("The Bee", CHANNEL_URI_PREFIX+"nj", "nj");
    public final static OldChannel THE_HITS = new OldChannel("The Hits", CHANNEL_URI_PREFIX+"nk", "nk");
    public final static OldChannel THE_SEVERN = new OldChannel("The Severn", CHANNEL_URI_PREFIX+"nm", "nm");
    public final static OldChannel TIPP_FM = new OldChannel("Tipp FM", CHANNEL_URI_PREFIX+"nn", "nn");
    public final static OldChannel TIPPERARY_MID_WEST_FM_RADIO = new OldChannel("Tipperary Mid West FM Radio", CHANNEL_URI_PREFIX+"np", "np");
    public final static OldChannel TODAY_FM = new OldChannel("Today FM", CHANNEL_URI_PREFIX+"nq", "nq");
    public final static OldChannel TOTAL_STAR_107_5 = new OldChannel("Total Star 107.5", CHANNEL_URI_PREFIX+"nr", "nr");
    public final static OldChannel TOUCH_RADIO_101_6_102_4 = new OldChannel("Touch Radio 101.6/102.4", CHANNEL_URI_PREFIX+"ns", "ns");
    public final static OldChannel TOUCH_RADIO_102 = new OldChannel("Touch Radio 102", CHANNEL_URI_PREFIX+"nt", "nt");
    public final static OldChannel TOUCH_RADIO_96_2 = new OldChannel("Touch Radio 96.2", CHANNEL_URI_PREFIX+"nv", "nv");
    public final static OldChannel TOWER_107_4FM = new OldChannel("Tower 107.4FM", CHANNEL_URI_PREFIX+"nw", "nw");
    public final static OldChannel TRAX_FM = new OldChannel("Trax FM", CHANNEL_URI_PREFIX+"nx", "nx");
    public final static OldChannel U105_8 = new OldChannel("U105.8", CHANNEL_URI_PREFIX+"ny", "ny");
    public final static OldChannel UTD_CHRISTIAN_BROADCASTING = new OldChannel("Utd Christian Broadcasting", CHANNEL_URI_PREFIX+"nz", "nz");
    public final static OldChannel VIKING_FM = new OldChannel("Viking FM", CHANNEL_URI_PREFIX+"n2", "n2");
    public final static OldChannel WATERFORD = new OldChannel("Waterford", CHANNEL_URI_PREFIX+"n4", "n4");
    public final static OldChannel WAVE_102FM = new OldChannel("Wave 102FM", CHANNEL_URI_PREFIX+"n5", "n5");
    public final static OldChannel WAVE_105_2FM = new OldChannel("Wave 105.2FM", CHANNEL_URI_PREFIX+"n6", "n6");
    public final static OldChannel WAVE_96_4FM = new OldChannel("Wave 96.4FM", CHANNEL_URI_PREFIX+"n7", "n7");
    public final static OldChannel WAVES_101_2 = new OldChannel("Waves 101.2", CHANNEL_URI_PREFIX+"n8", "n8");
    public final static OldChannel WCR_FM = new OldChannel("WCR FM", CHANNEL_URI_PREFIX+"n9", "n9");
    public final static OldChannel WESSEX_FM = new OldChannel("Wessex FM", CHANNEL_URI_PREFIX+"pb", "pb");
    public final static OldChannel WEST_FM = new OldChannel("West FM", CHANNEL_URI_PREFIX+"pc", "pc");
    public final static OldChannel WEST_SOUND_FM = new OldChannel("West Sound FM", CHANNEL_URI_PREFIX+"pd", "pd");
    public final static OldChannel WESTSOUND_AYRSHIRE_1035AM = new OldChannel("Westsound Ayrshire 1035AM", CHANNEL_URI_PREFIX+"pf", "pf");
    public final static OldChannel WISH_FM = new OldChannel("Wish FM", CHANNEL_URI_PREFIX+"pg", "pg");
    public final static OldChannel WOLF = new OldChannel("Wolf", CHANNEL_URI_PREFIX+"ph", "ph");
    public final static OldChannel WORLD_RADIO_NETWORK = new OldChannel("World Radio Network", CHANNEL_URI_PREFIX+"pj", "pj");
    public final static OldChannel WYRE_107_2 = new OldChannel("Wyre 107.2", CHANNEL_URI_PREFIX+"pk", "pk");
    public final static OldChannel WYVERN_FM = new OldChannel("Wyvern FM", CHANNEL_URI_PREFIX+"pm", "pm");
    public final static OldChannel XFM_MANCHESTER = new OldChannel("Xfm Manchester", CHANNEL_URI_PREFIX+"pn", "pn");
    public final static OldChannel YORKSHIRE_COAST_BRID_102_4FM = new OldChannel("Yorkshire Coast (Brid) 102.4FM", CHANNEL_URI_PREFIX+"pp", "pp");
    public final static OldChannel YORKSHIRE_COAST_96_2FM = new OldChannel("Yorkshire Coast 96.2FM", CHANNEL_URI_PREFIX+"pq", "pq");

    private final String uri;
    private final String title;

    private final static List<OldChannel> VOD_SERVICES = ImmutableList.of(BBC_IPLAYER, HULU, C4_4OD, YOUTUBE, SEESAW);
    private final String key;
    
    private final static Map<String, OldChannel> uriMap;
    private final static Map<String, OldChannel> keyMap;
    
    private final static Pattern uriPattern = Pattern.compile("^.*\\/(.+?)\\/?$");
    
    static {
        ImmutableMap.Builder<String, OldChannel> uriMapBuilder = ImmutableMap.builder();
        ImmutableMap.Builder<String, OldChannel> keyMapBuilder = ImmutableMap.builder();
        for (Field field: Channel.getClass().getFields()) {
            if (isPublicStaticFinal(field)) {
                try {
                    Object object = field.get(null);
                    if (object instanceof OldChannel) {
                        OldChannel channel = (OldChannel) object;
                        
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

    public OldChannel(String title, String uri, String key) {
        this.title = title;
        this.uri = uri;
        Preconditions.checkArgument(key.length() <= MAX_KEY_LENGTH);
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

    public static Maybe<OldChannel> fromUri(String uri) {
        OldChannel channel = uriMap.get(uri);
        if (channel == null) {
            Matcher matcher = uriPattern.matcher(uri);
            if (matcher.matches()) {
                channel = new OldChannel(matcher.group(1), uri, matcher.group(1));
            }
        }
        return Maybe.fromPossibleNullValue(channel);
    }

    public static Maybe<OldChannel> fromKey(String key) {
        OldChannel channel = keyMap.get(key);
        if (channel == null) {
            channel = new OldChannel(key, CHANNEL_URI_PREFIX+key, key);
        }
        return Maybe.fromPossibleNullValue(channel);
    }

    public static List<OldChannel> fromKeys(Iterable<String> keys) {
        Builder<OldChannel> builder = ImmutableList.builder();

        for (String key : keys) {
            Maybe<OldChannel> channel = fromKey(key);
            if (channel.hasValue()) {
                builder.add(channel.requireValue());
            }
        }

        return builder.build();
    }

    public static List<String> toKeys(Iterable<OldChannel> channels) {
        Builder<String> keys = ImmutableList.builder();

        for (OldChannel channel : channels) {
            keys.add(channel.key());
        }

        return keys.build();
    }
    
    public static Collection<OldChannel> all() {
    	return keyMap.values();
	}

    public static List<Map<String, ?>> mapList() {
        List<Map<String, ?>> channelList = Lists.newArrayList();
        for (OldChannel channel : keyMap.values()) {
            channelList.add(channel.toSimpleModel().asMap());
        }
        return channelList;
    }

    public static List<Map<String, ?>> mapListWithoutVodServices() {
        List<Map<String, ?>> channelList = Lists.newArrayList();
        for (OldChannel channel : keyMap.values()) {
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
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OldChannel) {
            OldChannel target = (OldChannel) obj;
            return Objects.equal(key, target.key) && Objects.equal(uri, target.uri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(key).addValue(uri).toString();
    }
}
