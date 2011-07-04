package org.atlasapi.media.entity;

import org.joda.time.DateTime;

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
    
}
