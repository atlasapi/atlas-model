package org.atlasapi.media;

public enum TransportType {
	LINK,
	DOWNLOAD,
	STREAM,
	BITTORRENT, 
	EMBED,
	APPLICATION;
	
	public String toString() {
		return name().toLowerCase();
	}

	public static TransportType fromString(String s) {
		return valueOf(s.toUpperCase());
	};
}
