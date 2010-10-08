package org.atlasapi.application;

import java.net.InetAddress;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class ApplicationCredentials {

	private String apiKey;
	private List<InetAddress> ipAddresses = Collections.emptyList();
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setIpAddresses(Iterable<InetAddress> ipAddresses) {
		this.ipAddresses = ImmutableList.copyOf(ipAddresses);
	}
	
	public List<InetAddress> getIpAddresses() {
		return ipAddresses;
	}
	
}
