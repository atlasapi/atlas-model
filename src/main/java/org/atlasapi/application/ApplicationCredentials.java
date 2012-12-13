package org.atlasapi.application;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.net.IpRange;

public class ApplicationCredentials {

	private final String apiKey;
	private final Set<IpRange> ipAddresses ;
	private final boolean enabled;
	
	public ApplicationCredentials(String apiKey, Set<IpRange> ipAddresses, boolean enabled) {
        this.apiKey = checkNotNull(apiKey);
        this.ipAddresses = ipAddresses != null ? ImmutableSet.copyOf(ipAddresses) : ImmutableSet.<IpRange>of();
        this.enabled = enabled;
	}
	
	public ApplicationCredentials(String apiKey, boolean enabled) {
	    this(apiKey, ImmutableSet.<IpRange>of(), enabled);
    }
	
	public String getApiKey() {
		return apiKey;
	}
	
	public ApplicationCredentials copyWithIpAddresses(Iterable<IpRange> ipAddresses) {
		return new ApplicationCredentials(apiKey, ImmutableSet.copyOf(ipAddresses), enabled);
	}
	
	public ApplicationCredentials copyEnabled() {
		return new ApplicationCredentials(apiKey, ImmutableSet.copyOf(ipAddresses), true);
	}
	
	public ApplicationCredentials copyDisabled() {
		return new ApplicationCredentials(apiKey, ImmutableSet.copyOf(ipAddresses), false);
	}
	
	public Set<IpRange> getIpAddressRanges() {
		return ipAddresses;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
}
