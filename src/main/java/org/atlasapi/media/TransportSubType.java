package org.atlasapi.media;

public enum TransportSubType {
	
	HTTP, 
	RTSP,
	ITUNES;
	
	public String toString() {
		return name().toLowerCase();
	}

	public static TransportSubType fromString(String s) {
		return valueOf(s.toUpperCase());
	};
}
