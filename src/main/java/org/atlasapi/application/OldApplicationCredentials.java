package org.atlasapi.application;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.net.IpRange;

public class OldApplicationCredentials {

	private final String apiKey;
	private final Set<IpRange> ipAddresses ;
	
	public OldApplicationCredentials(String apiKey, Set<IpRange> ipAddresses) {
        this.apiKey = checkNotNull(apiKey);
        this.ipAddresses = ipAddresses != null ? ImmutableSet.copyOf(ipAddresses) : ImmutableSet.<IpRange>of();
    }
	
	public OldApplicationCredentials(String apiKey) {
	    this(apiKey, ImmutableSet.<IpRange>of());
    }
	
	public String getApiKey() {
		return apiKey;
	}
	
	public OldApplicationCredentials copyWithIpAddresses(Iterable<IpRange> ipAddresses) {
		return new OldApplicationCredentials(apiKey, ImmutableSet.copyOf(ipAddresses));
	}
	
	public Set<IpRange> getIpAddressRanges() {
		return ipAddresses;
	}
	
}
