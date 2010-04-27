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

package org.uriplay.media.vocabulary;

/**
 * Vocabulary for Dbpedia.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
public class DBPP {

	 /** The namespace of the vocabalary as a string ({@value}).
     */
    public static final String NS = "http://dbpedia.org/property/";
    
    /** The default prefix ({@value}) for the DBPP namespace.
     */
    public static final String PREFIX = "dbpp";

    /** The namespace of the vocabalary as a string.
     */
    public static String getURI() { return NS; }

	public static final String SERIES = NS + "series";

}

