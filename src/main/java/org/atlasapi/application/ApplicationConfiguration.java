package org.atlasapi.application;

import java.util.List;
import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class ApplicationConfiguration {
	
	public static final ApplicationConfiguration DEFAULT_CONFIGURATION = defaultConfiguration();
	
	private final List<Publisher> precedence;
	private final Set<Publisher> includedPublishers;
	
	public ApplicationConfiguration(Set<Publisher> includedPublishers, List<Publisher> precedence) {
		this.includedPublishers = includedPublishers;
		this.precedence = precedence;
	}
	
	
	private static ApplicationConfiguration defaultConfiguration() {
	    final Set<Publisher> defaultDisabled = ImmutableSet.of(
	            Publisher.C4, 
	            Publisher.PA, 
	            Publisher.ITV,
	            Publisher.FIVE,
	            Publisher.PREVIEW_NETWORKS,
	            Publisher.RADIO_TIMES,
	            Publisher.WORLD_SERVICE);
		ImmutableSet<Publisher> defaultIncluded = ImmutableSet.copyOf(Iterables.filter(ImmutableSet.copyOf(Publisher.values()), new Predicate<Publisher>() {
			@Override
			public boolean apply(Publisher input) {
				return !defaultDisabled.contains(input);
			}
		}));
		return new ApplicationConfiguration(defaultIncluded, null);
	}

	
	public ApplicationConfiguration copyWithIncludedPublishers(Iterable<Publisher> publishers) {
		return new ApplicationConfiguration(ImmutableSet.copyOf(publishers), precedence);
	}
	
	public Set<Publisher> getIncludedPublishers() {
		return includedPublishers;
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
	
	public Ordering<Publisher> publisherPrecedenceOrdering() {
		return Ordering.explicit(precedence);
	}

	public ApplicationConfiguration copyWithPrecedence(List<Publisher> publishers) {
		return new ApplicationConfiguration(includedPublishers, ImmutableList.copyOf(publishers));
	}

	public List<Publisher> precedence() {
		return precedence;
	}
	
	public boolean precedenceEnabled() {
		return precedence != null;
	}

	public Iterable<Publisher> publishersInOrder() {
		if (!precedenceEnabled()) {
			return ImmutableList.copyOf(Publisher.values());
		}
		return appendMissingPublishersTo(precedence);
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
