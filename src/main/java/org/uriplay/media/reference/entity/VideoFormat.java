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

import static org.jherd.core.MimeType.VIDEO_H263;
import static org.jherd.core.MimeType.VIDEO_H264;
import static org.jherd.core.MimeType.VIDEO_MP4;
import static org.jherd.core.MimeType.VIDEO_MPEG;
import static org.jherd.core.MimeType.VIDEO_XDIRAC;
import static org.jherd.core.MimeType.VIDEO_XDIVX;
import static org.jherd.core.MimeType.VIDEO_XMSWMV;
import static org.jherd.core.MimeType.VIDEO_XPNREALVIDEO;
import static org.jherd.core.MimeType.VIDEO_XSVQ;
import static org.jherd.core.MimeType.VIDEO_XTHEORA;
import static org.jherd.core.MimeType.VIDEO_XVP6;
import static org.jherd.core.MimeType.VIDEO_XXVID;

import java.util.Map;

import javax.persistence.Entity;

import org.jherd.core.MimeType;

import com.google.common.collect.Maps;

@Entity
public class VideoFormat {
    private static Map<String, MimeType> alternatives = Maps.newHashMap();

	static {
		alternatives.put("video/H263", VIDEO_H263);
		alternatives.put("video/H264", VIDEO_H264);
		alternatives.put("video/mpeg", VIDEO_MPEG);
		alternatives.put("video/x-dirac", VIDEO_XDIRAC);
		alternatives.put("video/x-divx", VIDEO_XDIVX);
		alternatives.put("video/x-m4v", VIDEO_MP4);
		alternatives.put("video/x-ms-wmv", VIDEO_XMSWMV);
		alternatives.put("video/x-pn-realvideo", VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-realvideo", VIDEO_XPNREALVIDEO);
		alternatives.put("video/x-svq", VIDEO_XSVQ);
		alternatives.put("video/x-theora", VIDEO_XTHEORA);
		alternatives.put("video/x-vp6", VIDEO_XVP6);
		alternatives.put("video/x-xvid", VIDEO_XXVID);
	}
    
	public static MimeType fromAltName(String alternativeString) {
		return alternatives.get(alternativeString);
	}	
}
