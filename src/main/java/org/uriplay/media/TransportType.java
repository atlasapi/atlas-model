package org.uriplay.media;

public enum TransportType {
	LINK,
	DOWNLOAD,
	STREAM,
	BITTORRENT, 
	EMBED;
	
	public String toString() {
		return name().toLowerCase();
	}

	public static TransportType fromString(String s) {
		return valueOf(s.toUpperCase());
	};
}
