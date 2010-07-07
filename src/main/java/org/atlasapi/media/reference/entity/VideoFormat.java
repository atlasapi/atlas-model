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

public class VideoFormat {
    private static Map<String, MimeType> alternatives = Maps.newHashMap();

	static {
		alternatives.put("video/H263", MimeType.VIDEO_H263);
		alternatives.put("video/H264", MimeType.VIDEO_H264);
		alternatives.put("video/mpeg", MimeType.VIDEO_MPEG);
		alternatives.put("video/x-dirac", MimeType.VIDEO_XDIRAC);
		alternatives.put("video/x-divx", MimeType.VIDEO_XDIVX);
		alternatives.put("video/x-m4v", MimeType.VIDEO_MP4);
		alternatives.put("video/x-ms-wmv", MimeType.VIDEO_XMSWMV);
		alternatives.put("video/x-pn-realvideo", MimeType.VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-realvideo", MimeType.VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-svq", MimeType.VIDEO_XSVQ);
		alternatives.put("video/x-theora", MimeType.VIDEO_XTHEORA);
		alternatives.put("video/x-vp6", MimeType.VIDEO_XVP6);
		alternatives.put("video/x-xvid", MimeType.VIDEO_XXVID);
	}
    
	public static MimeType fromAltName(String alternativeString) {
		return alternatives.get(alternativeString);
	}	
}
