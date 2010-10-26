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

package org.atlasapi.genres;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Common base class for mapping genres. Extend this class and provide a
 * specific mapping for a specific source.s
 *  
 * @author Robert Chatley (robert@metabroadcast.com)
 */
public abstract class GenreMap {

	// initialise this in subclass to provide mapping.
	protected static final Map<String, AtlasGenre> genres = Maps.newHashMap();

	public Set<String> map(Set<String> sourceGenres) {
		
		Set<String> mappedGenres = Sets.newHashSet();
		
		if (sourceGenres == null) { return mappedGenres; }
		
		for (String sourceGenre : sourceGenres) {
			AtlasGenre mappedGenre = genres.get(sourceGenre.toLowerCase());
			if (mappedGenre != null) { 
				mappedGenres.add(mappedGenre.getUri()); 
			}
		}
		
		mappedGenres.addAll(sourceGenres);
		
		return mappedGenres;
	}
	
	public Set<String> mapRecognised(Set<String> sourceGenres) {
	    Set<String> mappedGenres = Sets.newHashSet();
        
        if (sourceGenres == null) { return mappedGenres; }
        
        for (String sourceGenre : sourceGenres) {
            AtlasGenre mappedGenre = genres.get(sourceGenre.toLowerCase());
            if (mappedGenre != null) { 
                mappedGenres.add(sourceGenre); 
                mappedGenres.add(mappedGenre.getUri()); 
            }
        }
        
        return mappedGenres;
	}

}