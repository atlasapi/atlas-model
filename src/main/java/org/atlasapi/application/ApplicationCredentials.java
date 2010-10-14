package org.atlasapi.application;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.net.IpRange;

public class ApplicationCredentials {

	private String apiKey;
	private Set<IpRange> ipAddresses = Collections.emptySet();
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setIpAddresses(Iterable<IpRange> ipAddresses) {
		this.ipAddresses = ImmutableSet.copyOf(ipAddresses);
	}
	
	public Set<IpRange> getIpAddressRanges() {
		return ipAddresses;
	}
	
}
