/* Copyright 2009 British Broadcasting Corporation
 
Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.uriplay.media.reference.entity;

import com.google.common.base.Predicate;

public enum MimeType {

	WILD("*","*"), 
	APPLICATION_RDF_XML("application", "rdf+xml"),
	APPLICATION_XML("application", "xml"),
	APPLICATION_JSON("application", "json"),
	APPLICATION_RSS_XML("application", "rss+xml"),
	APPLICATION_ATOM_XML("application", "atom+xml"),
	
	APPLICATION_OEMBED_JSON("application", "oembed+json"), // for oEmbed
	APPLICATION_OEMBED_XML("application", "oembed+xml"), // for oEmbed
	APPLICATION_XSHOCKWAVEFLASH("application", "x-shockwave-flash"),

	IMAGE_JPG("image", "jpeg"),
	IMAGE_PNG("image", "png"),
	IMAGE_BMP("image", "bmp"),
	IMAGE_TIFF("image", "tiff"),
	IMAGE_GIF("image", "gif"),
	IMAGE_IEF("image", "ief"),
	
	AUDIO_3GPP("audio", "3gpp"),
	AUDIO_AC3("audio", "ac3"),
	AUDIO_AMR("audio", "AMR"),
	AUDIO_MP4("audio", "mp4"),
	AUDIO_MPEG("audio", "mpeg"),
	AUDIO_OGG("audio", "ogg"),
	AUDIO_VNDRNREALAUDIO("audio", "vnd.rn-realaudio"),
	AUDIO_VORBIS("audio", "vorbis"),
	AUDIO_XFLAC("audio", "x-flac"),
	AUDIO_XMATROSKA("audio", "x-matroska"),
	AUDIO_XMP2T("audio", "x-MP2T"),
	AUDIO_XMSWMA("audio", "x-ms-wma"),
	AUDIO_XPNREALAUDIO("audio", "x-pn-realaudio"),
	AUDIO_XRNREALAUDIO("audio", "x-rn-realaudio"),
	VIDEO_3GPP("video", "3gpp"),
	VIDEO_H263("video", "H263"),
	VIDEO_H264("video", "H264"),
	VIDEO_MP2T("video", "MP2T"),
	VIDEO_MP4("video", "mp4"),
	VIDEO_MPEG("video", "mpeg"),
	VIDEO_OGG("video", "ogg"),
	VIDEO_QUICKTIME("video", "quicktime"),
	VIDEO_XDIRAC("video", "x-dirac"),
	VIDEO_XDIVX("video", "x-divx"),
	VIDEO_XFLV("video", "x-flv"),
	VIDEO_XMATROSKA("video", "x-matroska"),
	VIDEO_XMSASF("video", "x-ms-asf"),
	VIDEO_XMSVIDEO("video", "x-msvideo"),
	VIDEO_XMSWMV("video", "x-ms-wmv"),
	VIDEO_XPNREALVIDEO("video", "x-pn-realvideo"),
	VIDEO_XSVQ("video", "x-svq"),
	VIDEO_XTHEORA("video", "x-theora"),
	VIDEO_XVP6("video", "x-vp6"),
	VIDEO_XXVID("video", "x-xvid"),
	TEXT_HTML("text", "html"), 
	TEXT_PLAIN("text", "plain"),
	TEXT_XML("text", "xml");
	
	public static final String SEPARATOR = "/";
	public static final String WILDCARD = "*";

	private String parentType;

	private String childType;

	public String getParentType() {
		return parentType;
	}
	
	public String getChildType() {
		return childType;
	}
	
	private MimeType(String parentType, String childType) {
		this.parentType = parentType;
		this.childType = childType;
	}
	
	public String toString() {
		return parentType + SEPARATOR + childType;
	}
	
	public static MimeType fromString(String stringMimeType) {
		for (MimeType type : MimeType.values()) {
			if (type.toString().equals(stringMimeType)) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid mime type: " + stringMimeType);
	}
	
	public static class WildcardPredicate implements Predicate<MimeType> {
		
		MimeType mimeType;
		
		public WildcardPredicate(MimeType mimeType) {
			this.mimeType = mimeType;
		}

		public boolean apply(MimeType mimeType) {
			return (this.mimeType.getParentType().equals(WILDCARD) 
					|| mimeType.getParentType().equals(this.mimeType.getParentType()))
					&& (this.mimeType.getChildType().equals(WILDCARD)
					|| mimeType.getChildType().equals(this.mimeType.getChildType()));
		}
		
		public boolean isWild() {
			return (this.mimeType.getParentType().equals(WILDCARD) || this.mimeType.getChildType().equals(WILDCARD));
		}
	}

}
