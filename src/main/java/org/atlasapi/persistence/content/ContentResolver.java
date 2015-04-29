/* Copyright 2009 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.atlasapi.persistence.content;


/**
 * Simple interface to the store of available content.
 *
 * @author John Ayres (john@metabroadcast.com)
 * @author Fred van den Driessche (fred@metabroadcast.com)
 */
public interface ContentResolver {

    /**
     * Find content whose canonical URIs are in the provided collection
     * 
     * @param canonicalUris
     * @return
     */
	ResolvedContent findByCanonicalUris(Iterable<? extends String> canonicalUris);
	
	/**
	 * Find content whose canonical URIs or that have alias URIs in the provided collection
	 *   
	 * @param uris
	 * @return
	 */
	ResolvedContent findByUris(Iterable<String> uris);
}
