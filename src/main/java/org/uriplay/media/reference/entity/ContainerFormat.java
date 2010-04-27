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

package org.uriplay.media.reference.entity;

import static org.jherd.core.MimeType.APPLICATION_XSHOCKWAVEFLASH;
import static org.jherd.core.MimeType.AUDIO_3GPP;
import static org.jherd.core.MimeType.AUDIO_MP4;
import static org.jherd.core.MimeType.AUDIO_MPEG;
import static org.jherd.core.MimeType.AUDIO_OGG;
import static org.jherd.core.MimeType.AUDIO_XMATROSKA;
import static org.jherd.core.MimeType.AUDIO_XMP2T;
import static org.jherd.core.MimeType.AUDIO_XPNREALAUDIO;
import static org.jherd.core.MimeType.VIDEO_3GPP;
import static org.jherd.core.MimeType.VIDEO_MP2T;
import static org.jherd.core.MimeType.VIDEO_MP4;
import static org.jherd.core.MimeType.VIDEO_MPEG;
import static org.jherd.core.MimeType.VIDEO_OGG;
import static org.jherd.core.MimeType.VIDEO_QUICKTIME;
import static org.jherd.core.MimeType.VIDEO_XFLV;
import static org.jherd.core.MimeType.VIDEO_XMATROSKA;
import static org.jherd.core.MimeType.VIDEO_XMSASF;
import static org.jherd.core.MimeType.VIDEO_XMSVIDEO;
import static org.jherd.core.MimeType.VIDEO_XPNREALVIDEO;

import java.util.Map;

import javax.persistence.Entity;

import org.jherd.core.MimeType;

import com.google.common.collect.Maps;

@Entity
public class ContainerFormat {
    private static Map<String, MimeType> alternatives = Maps.newHashMap();

	static {
		alternatives.put("application/x-shockwave-flash", APPLICATION_XSHOCKWAVEFLASH);
		alternatives.put("audio/3gpp", AUDIO_3GPP);
		alternatives.put("audio/3gpp2", AUDIO_3GPP);
		alternatives.put("video/3gpp", VIDEO_3GPP);
		alternatives.put("video/3gpp2", VIDEO_3GPP);
		alternatives.put("audio/mpeg", AUDIO_MPEG);
		alternatives.put("audio/mp1", AUDIO_MPEG);
		alternatives.put("audio/mp2", AUDIO_MPEG);
		alternatives.put("audio/mp3", AUDIO_MPEG);
		alternatives.put("audio/mpeg2", AUDIO_MPEG);
		alternatives.put("audio/MPA", AUDIO_MPEG);
		alternatives.put("video/mpeg", VIDEO_MPEG);
		alternatives.put("video/MPV", VIDEO_MPEG);
		alternatives.put("audio/x-MP2T", AUDIO_XMP2T);
		alternatives.put("video/MP2T", VIDEO_MP2T);
		alternatives.put("audio/mp4", AUDIO_MP4);
		alternatives.put("audio/m4a", AUDIO_MP4);
		alternatives.put("audio/x-m4a", AUDIO_MP4);
		alternatives.put("video/mp4", VIDEO_MP4);
		alternatives.put("video/m4v", VIDEO_MP4);
		alternatives.put("audio/ogg", AUDIO_OGG);
		alternatives.put("video/ogg", VIDEO_OGG);
		alternatives.put("video/quicktime", VIDEO_QUICKTIME);
		alternatives.put("video/x-flv", VIDEO_XFLV);
		alternatives.put("video/x-flash-video", VIDEO_XFLV);
		alternatives.put("audio/x-matroska", AUDIO_XMATROSKA);
		alternatives.put("video/x-matroska", VIDEO_XMATROSKA);
		alternatives.put("video/x-wmv", VIDEO_XMSASF);
		alternatives.put("video/x-ms-asf", VIDEO_XMSASF);
		alternatives.put("video/x-ms-wmv", VIDEO_XMSASF);
		alternatives.put("video/avi", VIDEO_XMSVIDEO);
		alternatives.put("video/msvideo", VIDEO_XMSVIDEO);
		alternatives.put("video/x-msvideo", VIDEO_XMSVIDEO);
		alternatives.put("audio/x-pn-realaudio", AUDIO_XPNREALAUDIO);
		alternatives.put("audio/x-realaudio", AUDIO_XPNREALAUDIO);
		alternatives.put("audio/vnd.rn-realaudio", AUDIO_XPNREALAUDIO);
		alternatives.put("audio/x-rn-realaudio", AUDIO_XPNREALAUDIO);
		alternatives.put("video/x-pn-realvideo", VIDEO_XPNREALVIDEO);
		alternatives.put("video/vnd.rn-realvideo", VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-rn-realvideo", VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-realvideo", VIDEO_XPNREALVIDEO);
	}
	
	public static MimeType fromAltName(String alternativeString) {
		return alternatives.get(alternativeString);
	}	
}
