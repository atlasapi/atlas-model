package org.atlasapi.application;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class ApplicationConfiguration {
	
	public static final ApplicationConfiguration DEFAULT_CONFIGURATION = defaultConfiguration();
	
	private static ApplicationConfiguration defaultConfiguration() {
		ApplicationConfiguration defaultApp = new ApplicationConfiguration();
		
		defaultApp.includedPublishers = ImmutableSet.copyOf(Iterables.filter(ImmutableSet.copyOf(Publisher.values()), new Predicate<Publisher>() {
			@Override
			public boolean apply(Publisher input) {
				//return true; // allow all until the admin app is finished
				return !input.equals(Publisher.C4);
			}
		}));

		return defaultApp;
	}

	private Set<Publisher> includedPublishers = ImmutableSet.of();
	
	public ApplicationConfiguration() {
		
	}
	
	public ApplicationConfiguration copyWithIncludedPublishers(Iterable<Publisher> publishers) {
		ApplicationConfiguration config = new ApplicationConfiguration();
		config.includedPublishers = ImmutableSet.copyOf(publishers);
		return config;
	}

	public Set<Publisher> getIncludedPublishers() {
		return includedPublishers;
	}

}
