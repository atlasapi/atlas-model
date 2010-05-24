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

package org.uriplay.media.vocabulary;

public class RDF {

    /** The namespace of the vocabulary as a string ({@value}).
     */
    public static final String NS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    
    /** The default prefix ({@value}) for the namespace.
     */
    public static final String PREFIX = "rdf";

    public static final String TYPE = "type";
    
    /** The namespace of the vocabulary as a string.
     */
    public static String getURI() { return NS; }
    
}
