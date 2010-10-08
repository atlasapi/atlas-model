package org.atlasapi.application;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

public class ApplicationConfiguration {
	
	public static final ApplicationConfiguration DEFAULT_CONFIGURATION = defaultConfiguration();
	
	private static ApplicationConfiguration defaultConfiguration() {
		ApplicationConfiguration defaultApp = new ApplicationConfiguration();
		
		defaultApp.getIncludedPublishers().addAll(ImmutableList.of(Publisher.BBC));
		defaultApp.getExcludedPublishers().addAll(ImmutableList.of(Publisher.C4));
		
		return defaultApp;
	}

	private Set<Publisher> includedPublishers = Sets.newHashSet();
	private Set<Publisher> excludedPublishers = Sets.newHashSet();
	
	public ApplicationConfiguration() {
		
	}

	public void setIncludedPublishers(Set<Publisher> includedPublishers) {
		this.includedPublishers = Sets.newHashSet(includedPublishers);
	}

	public Set<Publisher> getIncludedPublishers() {
		return includedPublishers;
	}
	
	public void setExcludedPublishers(Set<Publisher> excludedPublishers) {
		this.excludedPublishers = Sets.newHashSet(excludedPublishers);
	}

	public Set<Publisher> getExcludedPublishers() {
		return excludedPublishers;
	}

}
