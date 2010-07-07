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

package org.atlasapi.media.reference.entity;

import java.util.Map;

import com.google.common.collect.Maps;
import com.metabroadcast.common.media.MimeType;

public class AudioFormat {
    private static Map<String, MimeType> alternatives = Maps.newHashMap();

	static {
		alternatives.put("audio/ac3)", MimeType.AUDIO_AC3);
		alternatives.put("audio/AMR", MimeType.AUDIO_AMR);
		alternatives.put("audio/mpeg", MimeType.AUDIO_MPEG);
		alternatives.put("audio/mp3", MimeType.AUDIO_MPEG);
		alternatives.put("audio/mp4", MimeType.AUDIO_MP4);
		alternatives.put("audio/vorbis", MimeType.AUDIO_VORBIS);
		alternatives.put("audio/x-flac", MimeType.AUDIO_XFLAC);
		alternatives.put("audio/x-ms-wma", MimeType.AUDIO_XMSWMA);
		alternatives.put("audio/x-rn-realaudio", MimeType.AUDIO_XPNREALAUDIO);
		alternatives.put("audio/vnd.rn-realaudio", MimeType.AUDIO_XPNREALAUDIO);
		alternatives.put("audio/x-realaudio", MimeType.AUDIO_XPNREALAUDIO);
	}		
    
	public static MimeType fromAltName(String alternativeString) {
		return alternatives.get(alternativeString);
	}	
}
