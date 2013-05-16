package org.atlasapi.media.content.schedule;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Set;

import org.atlasapi.media.entity.ChannelSchedule;
import org.atlasapi.media.util.ItemAndBroadcast;

class ScheduleUpdate {

    private final List<ChannelSchedule> updatedBlocks;
    private final Set<ItemAndBroadcast> staleEntries;

    public ScheduleUpdate(List<ChannelSchedule> updatedBlocks, Set<ItemAndBroadcast> staleEntries) {
        this.updatedBlocks = checkNotNull(updatedBlocks);
        this.staleEntries = checkNotNull(staleEntries);
    }
    
    public List<ChannelSchedule> getUpdatedBlocks() {
        return this.updatedBlocks;
    }
    
    public Set<ItemAndBroadcast> getStaleEntries() {
        return this.staleEntries;
    }
    
}
