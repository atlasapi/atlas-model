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

import static org.jherd.core.MimeType.AUDIO_AC3;
import static org.jherd.core.MimeType.AUDIO_AMR;
import static org.jherd.core.MimeType.AUDIO_MP4;
import static org.jherd.core.MimeType.AUDIO_MPEG;
import static org.jherd.core.MimeType.AUDIO_VORBIS;
import static org.jherd.core.MimeType.AUDIO_XFLAC;
import static org.jherd.core.MimeType.AUDIO_XMSWMA;
import static org.jherd.core.MimeType.AUDIO_XPNREALAUDIO;

import java.util.Map;

import org.jherd.core.MimeType;

import com.google.common.collect.Maps;

public class AudioFormat {
    private static Map<String, MimeType> alternatives = Maps.newHashMap();

	static {
		alternatives.put("audio/ac3)", AUDIO_AC3);
		alternatives.put("audio/AMR", AUDIO_AMR);
		alternatives.put("audio/mpeg", AUDIO_MPEG);
		alternatives.put("audio/mp3", AUDIO_MPEG);
		alternatives.put("audio/mp4", AUDIO_MP4);
		alternatives.put("audio/vorbis", AUDIO_VORBIS);
		alternatives.put("audio/x-flac", AUDIO_XFLAC);
		alternatives.put("audio/x-ms-wma", AUDIO_XMSWMA);
		alternatives.put("audio/x-rn-realaudio", AUDIO_XPNREALAUDIO);
		alternatives.put("audio/vnd.rn-realaudio", AUDIO_XPNREALAUDIO);
		alternatives.put("audio/x-realaudio", AUDIO_XPNREALAUDIO);
	}		
    
	public static MimeType fromAltName(String alternativeString) {
		return alternatives.get(alternativeString);
	}	
}
