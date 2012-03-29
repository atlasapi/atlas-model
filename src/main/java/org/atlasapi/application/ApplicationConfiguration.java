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

public class ApplicationConfiguration {
	
	public static final ApplicationConfiguration DEFAULT_CONFIGURATION = defaultConfiguration();
	
    private static final ApplicationConfiguration defaultConfiguration() {
        return new ApplicationConfiguration(ImmutableMap.<Publisher, SourceStatus>of(), null);
    }
	
	private final Map<Publisher, SourceStatus> sourceStatuses;
	private final Set<Publisher> enabledSources;
	private final List<Publisher> precedence;
	
	private ApplicationConfiguration(Map<Publisher, SourceStatus> sourceStatuses, Set<Publisher> enabledPublishers, List<Publisher> precedence) {
        this.sourceStatuses = ImmutableMap.copyOf(sourceStatuses);
        this.enabledSources = enabledPublishers;
		this.precedence = precedence;
	}
	
	ApplicationConfiguration(Map<Publisher, SourceStatus> sourceStatuses, List<Publisher> precedence) {
	    this(sourceStatuses, enabledPublishers(sourceStatuses), precedence);
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
	
    public Set<Publisher> getEnabledSources() {
        return enabledSources;
    }
    
    public SourceStatus statusOf(Publisher source) {
        return Optional.fromNullable(sourceStatuses.get(source)).or(source.getDefaultSourceStatus());
    }
    
    public Map<Publisher, SourceStatus> sourceStatuses() {
        return allSourcesStatuses(sourceStatuses);
    }
    
    public ApplicationConfiguration enable(Publisher source) {
        return withSource(source, statusOf(source).enable());
    }
    
    public ApplicationConfiguration disable(Publisher source) {
        return withSource(source, statusOf(source).disable());
    }
    
    public ApplicationConfiguration request(Publisher source) {
        return withSource(source, statusOf(source).request());
    }
    
    public ApplicationConfiguration revoke(Publisher source) {
        return withSource(source, statusOf(source).revoke());
    }
    
    public ApplicationConfiguration approve(Publisher source) {
        return withSource(source, statusOf(source).approve());
    }
    
    public ApplicationConfiguration withSource(Publisher source, SourceStatus status) {
        HashMap<Publisher,SourceStatus> mutableSources = Maps.newHashMap(sourceStatuses);
        mutableSources.put(source, status);
        
        Set<Publisher> mutablePublishers = Sets.newHashSet(enabledSources);
        if (status.isEnabled()) {
            mutablePublishers.add(source);
        } else {
            mutablePublishers.remove(source);
        }
        
        return new ApplicationConfiguration(mutableSources, mutablePublishers, precedence);
    }
    
    public ApplicationConfiguration withSources(Map<Publisher, SourceStatus> statuses) {
        HashMap<Publisher,SourceStatus> mutableSources = Maps.newHashMap(sourceStatuses);
        mutableSources.putAll(statuses);
        
        return new ApplicationConfiguration(mutableSources, precedence);
    }
	
    public ApplicationConfiguration copyWithPrecedence(List<Publisher> publishers) {
        return new ApplicationConfiguration(sourceStatuses, ImmutableList.copyOf(publishers));
    }
    
    public ApplicationConfiguration copyWithNullPrecedence() {
        return new ApplicationConfiguration(sourceStatuses, null);
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

	public Iterable<Publisher> orderdPublishers() {
		if (!precedenceEnabled()) {
			return ImmutableList.copyOf(Publisher.values());
		}
		return appendMissingPublishersTo(precedence);
	}
	
    /**
     * Temporary: these should be persisted and not hardcoded
     */
    private ImmutableList<Publisher> imagePrecedence() {
        return ImmutableList.of(Publisher.BBC, Publisher.C4, Publisher.PA);
    }
    
    public boolean imagePrecedenceEnabled() {
        return imagePrecedence() != null;
    }
    
    public Ordering<Publisher> imagePrecedenceOrdering() {
        return Ordering.explicit(appendMissingPublishersTo(imagePrecedence()));
    }

    private List<Publisher> appendMissingPublishersTo(Iterable<Publisher> selected) {
        List<Publisher> publishers = Lists.newArrayList(selected);
        for (Publisher publisher : Publisher.values()) {
            if (!publishers.contains(publisher)) {
                publishers.add(publisher);
            }
        }
        return publishers;
    }
}
