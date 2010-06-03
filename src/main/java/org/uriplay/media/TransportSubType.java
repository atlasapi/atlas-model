package org.uriplay.media;

public enum TransportSubType {
	
	HTTP, 
	RTSP;
	
	public String toString() {
		return name().toLowerCase();
	}

	public static TransportSubType fromString(String s) {
		return valueOf(s.toUpperCase());
	};
}
