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

package org.atlasapi.media.vocabulary;

/**
 * Vocabulary for DCTERMS
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
public class DCTERMS {

	 /** The namespace of the vocabulary as a string ({@value}).
     */
    public static final String NS = "http://purl.org/dc/terms/";
    
    /** The default prefix ({@value}) for the DCTERMS namespace.
     */
    public static final String PREFIX = "dcterms";

    /** The namespace of the vocabalary as a string.
     */
    public static String getURI() { return NS; }
}

