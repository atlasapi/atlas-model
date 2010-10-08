package org.atlasapi.application;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class ApplicationConfiguration {
	
	public static final ApplicationConfiguration DEFAULT_CONFIGURATION = defaultConfiguration();
	
	public static ApplicationConfiguration defaultConfiguration() {
		ApplicationConfiguration defaultApp = new ApplicationConfiguration();
		
		defaultApp.setIncludedPublishers(Iterables.filter(ImmutableSet.copyOf(Publisher.values()), new Predicate<Publisher>() {

			@Override
			public boolean apply(Publisher input) {
				return !input.equals(Publisher.C4);
			}
		}));
		
		return defaultApp;
	}

	private Set<Publisher> includedPublishers = Sets.newHashSet();
	
	public ApplicationConfiguration() {
		
	}

	public void setIncludedPublishers(Iterable<Publisher> includedPublishers) {
		this.includedPublishers = Sets.newHashSet(includedPublishers);
	}

	public Set<Publisher> getIncludedPublishers() {
		return includedPublishers;
	}

}
