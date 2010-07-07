/* Copyright 2009 British Broadcasting Corporation
   Copyright 2009 Meta Broadcast Ltd
 
Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.atlasapi.media.reference.entity;

import java.util.Map;

import com.google.common.collect.Maps;
import com.metabroadcast.common.media.MimeType;

public class ContainerFormat {
    private static Map<String, MimeType> alternatives = Maps.newHashMap();

	static {
		alternatives.put("application/x-shockwave-flash", MimeType.APPLICATION_XSHOCKWAVEFLASH);
		alternatives.put("audio/3gpp", MimeType.AUDIO_3GPP);
		alternatives.put("audio/3gpp2", MimeType.AUDIO_3GPP);
		alternatives.put("video/3gpp", MimeType.VIDEO_3GPP);
		alternatives.put("video/3gpp2", MimeType.VIDEO_3GPP);
		alternatives.put("audio/mpeg", MimeType.AUDIO_MPEG);
		alternatives.put("audio/mp1", MimeType.AUDIO_MPEG);
		alternatives.put("audio/mp2", MimeType.AUDIO_MPEG);
		alternatives.put("audio/mp3", MimeType.AUDIO_MPEG);
		alternatives.put("audio/mpeg2", MimeType.AUDIO_MPEG);
		alternatives.put("audio/MPA", MimeType.AUDIO_MPEG);
		alternatives.put("video/mpeg", MimeType.VIDEO_MPEG);
		alternatives.put("video/MPV", MimeType.VIDEO_MPEG);
		alternatives.put("audio/x-MP2T", MimeType.AUDIO_XMP2T);
		alternatives.put("video/MP2T", MimeType.VIDEO_MP2T);
		alternatives.put("audio/mp4", MimeType.AUDIO_MP4);
		alternatives.put("audio/m4a", MimeType.AUDIO_MP4);
		alternatives.put("audio/x-m4a", MimeType.AUDIO_MP4);
		alternatives.put("video/mp4", MimeType.VIDEO_MP4);
		alternatives.put("video/m4v", MimeType.VIDEO_MP4);
		alternatives.put("audio/ogg", MimeType.AUDIO_OGG);
		alternatives.put("video/ogg", MimeType.VIDEO_OGG);
		alternatives.put("video/quicktime", MimeType.VIDEO_QUICKTIME);
		alternatives.put("video/x-flv", MimeType.VIDEO_XFLV);
		alternatives.put("video/x-flash-video", MimeType.VIDEO_XFLV);
		alternatives.put("audio/x-matroska", MimeType.AUDIO_XMATROSKA);
		alternatives.put("video/x-matroska", MimeType.VIDEO_XMATROSKA);
		alternatives.put("video/x-wmv", MimeType.VIDEO_XMSASF);
		alternatives.put("video/x-ms-asf", MimeType.VIDEO_XMSASF);
		alternatives.put("video/x-ms-wmv", MimeType.VIDEO_XMSASF);
		alternatives.put("video/avi", MimeType.VIDEO_XMSVIDEO);
		alternatives.put("video/msvideo", MimeType.VIDEO_XMSVIDEO);
		alternatives.put("video/x-msvideo", MimeType.VIDEO_XMSVIDEO);
		alternatives.put("audio/x-pn-realaudio", MimeType.AUDIO_XPNREALAUDIO);
		alternatives.put("audio/x-realaudio", MimeType.AUDIO_XPNREALAUDIO);
		alternatives.put("audio/vnd.rn-realaudio", MimeType.AUDIO_XPNREALAUDIO);
		alternatives.put("audio/x-rn-realaudio", MimeType.AUDIO_XPNREALAUDIO);
		alternatives.put("video/x-pn-realvideo", MimeType.VIDEO_XPNREALVIDEO);
		alternatives.put("video/vnd.rn-realvideo", MimeType.VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-rn-realvideo", MimeType.VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-realvideo", MimeType.VIDEO_XPNREALVIDEO);
	}
	
	public static MimeType fromAltName(String alternativeString) {
		return alternatives.get(alternativeString);
	}	
}
