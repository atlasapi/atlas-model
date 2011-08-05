package org.atlasapi.media.entity;

import java.util.Comparator;

import org.joda.time.DateTime;

import com.google.common.base.Strings;

public enum SortKey {
    
    ADAPTER("10") {
        @Override
        protected String generateFrom(Item item) {
            if(item.sortKey() != null) {
                return prefix + item.sortKey();
            }
            return null;
        }
    },
    SERIES_EPISODE("20") {
        @Override
        protected String generateFrom(Item item) {
            if(item instanceof Episode) {
                Episode episode = (Episode) item;
                if(episode.getEpisodeNumber() != null && episode.getSeriesNumber() != null) {
                    return SortKey.SERIES_EPISODE.append(String.format("%s%s",episode.getSeriesNumber(),episode.getEpisodeNumber()));
                }
            }
            return null;
        }
    },
    
    BROADCAST("30") {
        @Override
        protected String generateFrom(Item item) {
            DateTime firstBroadcast = null;
            for ( Version version : item.getVersions()) {
                for (Broadcast broadcast : version.getBroadcasts()) {
                    if(firstBroadcast == null || broadcast.getTransmissionTime().isBefore(firstBroadcast)) {
                        firstBroadcast = broadcast.getTransmissionTime();
                    }
                }
            }
            if(firstBroadcast != null) {
                return BROADCAST.append(String.format("%s",firstBroadcast.getMillis()));
            }
            return null;
        }
    },
    
    DEFAULT("99") {

        @Override
        protected String generateFrom(Item item) {
            return this.prefix;
        }
        
    };
    
    protected String prefix;

    SortKey(String prefix) {
        this.prefix = prefix;
    }
    
    protected abstract String generateFrom(Item item);
    
    private String append(String key) {
        return prefix + key;
    }
    
    public static String keyFrom(Item item) {
        for (SortKey sortKey : SortKey.values()) {
            String key = sortKey.generateFrom(item);
            if(key != null) {
                return key;
            }
        }
        return "99";
    }
    
    
    public static class SortKeyOutputComparator implements Comparator<String> {

        @Override
        public int compare(String sk1, String sk2) {
            
            if(Strings.isNullOrEmpty(sk1)) {
                sk1 = DEFAULT.prefix;
            }
            if(Strings.isNullOrEmpty(sk2)) {
                sk2 = DEFAULT.prefix;
            }
            
            String prefix1 = sk1.substring(0, 2);
            String prefix2 = sk2.substring(0, 2);
            
            int compareType = prefix1.compareTo(prefix2);
            
            if(compareType != 0) {
                return compareType;
            }
            
            String key1 = sk1.substring(2);
            String key2 = sk2.substring(2);
            
            if(prefix1.equals("20") || prefix1.equals("30")) {
                return key2.compareTo(key1);
            }
            
            return key1.compareTo(key2);
        }
        
    }
    
}
