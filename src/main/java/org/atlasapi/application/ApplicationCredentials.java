package org.atlasapi.application;

import java.net.InetAddress;

public class ApplicationCredentials {

	private String apiKey;
	private InetAddress ipAddress;
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public InetAddress getIpAddress() {
		return ipAddress;
	}
	
}
