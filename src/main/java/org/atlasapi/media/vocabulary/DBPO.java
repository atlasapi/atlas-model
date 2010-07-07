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
 * Vocabulary for Dbpedia.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
public class DBPO {

	 /** The namespace of the vocabulary as a string ({@value}).
     */
    public static final String NS = "http://dbpedia.org/ontology/";
    
    /** The default prefix ({@value}) for the DBPO namespace.
     */
    public static final String PREFIX = "dbpo";

    /** The namespace of the vocabalary as a string.
     */
    public static String getURI() { return NS; }

    public static final String FILM = DBPO.NS + "Film";
    public static final String PERSON = DBPO.NS + "Person";
	public static final String TELEVISION_EPISODE = NS + "TelevisionEpisode";
	public static final String TELEVISION_SHOW = DBPO.NS + "TelevisionShow";
}

