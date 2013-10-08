package org.atlasapi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

public class OldApplicationConfiguration {
	
    @Deprecated
    /**
     * Use defaultConfiguration
     */
	public static final OldApplicationConfiguration DEFAULT_CONFIGURATION = new OldApplicationConfiguration(ImmutableMap.<Publisher, SourceStatus>of(), null);
	
    public static final OldApplicationConfiguration defaultConfiguration() {
        return DEFAULT_CONFIGURATION;
    }
	
	private final Map<Publisher, SourceStatus> sourceStatuses;
	private final ImmutableSet<Publisher> enabledSources;
	private final List<Publisher> precedence;
	private final ImmutableSet<Publisher> writableSources;
	
	private OldApplicationConfiguration(Map<Publisher, SourceStatus> sourceStatuses, Set<Publisher> enabledSources, List<Publisher> precedence, Iterable<Publisher> writable) {
        this.sourceStatuses = ImmutableMap.copyOf(sourceStatuses);
        this.enabledSources = ImmutableSet.copyOf(enabledSources);
        if(precedence == null) {
            this.precedence = null;
        } else {
            this.precedence = appendMissingPublishersTo(precedence);
        }
        this.writableSources = ImmutableSet.copyOf(writable);
	}
	
	OldApplicationConfiguration(Map<Publisher, SourceStatus> sourceStatuses, List<Publisher> precedence) {
	    this(sourceStatuses, precedence, ImmutableSet.<Publisher>of());
	}

	public OldApplicationConfiguration(Map<Publisher, SourceStatus> sourceStatuses, List<Publisher> precedence, Iterable<Publisher> writableSources) {
	    this(sourceStatuses, enabledPublishers(sourceStatuses), precedence, writableSources);
	}

    private static Set<Publisher> enabledPublishers(Map<Publisher, SourceStatus> sourceStatuses) {
	    return ImmutableSet.copyOf(Maps.filterValues(allSourcesStatuses(sourceStatuses), SourceStatus.IS_ENABLED).keySet());
    }

	private static Map<Publisher, SourceStatus> allSourcesStatuses(Map<Publisher, SourceStatus> configSpecificStatuses) {
	    Builder<Publisher, SourceStatus> statuses = ImmutableMap.builder();
	    for (Publisher source : ImmutableSet.copyOf(Publisher.values())) {
            statuses.put(source, Optional.fromNullable(configSpecificStatuses.get(source)).or(source.getDefaultSourceStatus()));
        }
	    return statuses.build();
	}
	
	public boolean isEnabled(Publisher source) {
	    return enabledSources.contains(source);
	}
	
    public ImmutableSet<Publisher> getEnabledSources() {
        return enabledSources;
    }
    
    public SourceStatus statusOf(Publisher source) {
        return Optional.fromNullable(sourceStatuses.get(source)).or(source.getDefaultSourceStatus());
    }
    
    public Map<Publisher, SourceStatus> sourceStatuses() {
        return allSourcesStatuses(sourceStatuses);
    }
 
    
    public OldApplicationConfiguration withSource(Publisher source, SourceStatus status) {
        HashMap<Publisher,SourceStatus> mutableSources = Maps.newHashMap(sourceStatuses);
        mutableSources.put(source, status);
        
        Set<Publisher> mutablePublishers = Sets.newHashSet(enabledSources);
        if (status.isEnabled()) {
            mutablePublishers.add(source);
        } else {
            mutablePublishers.remove(source);
        }
        
        return new OldApplicationConfiguration(mutableSources, mutablePublishers, precedence, writableSources);
    }
    
    public OldApplicationConfiguration withSources(Map<Publisher, SourceStatus> statuses) {
        HashMap<Publisher,SourceStatus> mutableSources = Maps.newHashMap(sourceStatuses);
        mutableSources.putAll(statuses);
        
        return new OldApplicationConfiguration(mutableSources, precedence);
    }
	
    public OldApplicationConfiguration copyWithPrecedence(List<Publisher> publishers) {
        return new OldApplicationConfiguration(sourceStatuses, ImmutableList.copyOf(publishers));
    }
    
    public boolean canWrite(Publisher source) {
        return writableSources.contains(source);
    }
    
    public ImmutableSet<Publisher> writableSources() {
        return writableSources;
    }
	
	public Ordering<Publisher> publisherPrecedenceOrdering() {
		return Ordering.explicit(precedence);
	}
	
	public List<Publisher> precedence() {
		return precedence;
	}
	
	public boolean precedenceEnabled() {
		return precedence != null;
	}

	public List<Publisher> orderdPublishers() {
		if (!precedenceEnabled()) {
			return Publisher.all().asList();
		}
		return precedence;
	}
	
    /**
     * Temporary: these should be persisted and not hardcoded
     */
//    private ImmutableList<Publisher> imagePrecedence() {
//        return ImmutableList.of(Publisher.PA, Publisher.BBC, Publisher.C4);
//    }
//    
//    public boolean imagePrecedenceEnabled() {
//        return imagePrecedence() != null;
//    }
//    
//    public Ordering<Publisher> imagePrecedenceOrdering() {
//   //        return Ordering.explicit(appendMissingPublishersTo(imagePrecedence()));
//        return publisherPrecedenceOrdering();
//    }
//
    private List<Publisher> appendMissingPublishersTo(Iterable<Publisher> selected) {
        List<Publisher> publishers = Lists.newArrayList(selected);
        for (Publisher publisher : Publisher.values()) {
            if (!publishers.contains(publisher)) {
                publishers.add(publisher);
            }
        }
        return publishers;
    }
    
//    private ImmutableList<Publisher> peoplePrecedence() {
//        return ImmutableList.of(Publisher.RADIO_TIMES, Publisher.PA, Publisher.BBC, Publisher.C4, Publisher.ITV);
//    }
//    
//    public boolean peoplePrecedenceEnabled() {
//        return peoplePrecedence() != null;
//    }
//    
//    public Ordering<Publisher> peoplePrecedenceOrdering() {
//        return Ordering.explicit(appendMissingPublishersTo(peoplePrecedence()));
//   }
}
