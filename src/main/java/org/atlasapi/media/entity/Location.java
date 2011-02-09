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

package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.TransportSubType;
import org.atlasapi.media.TransportType;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
@RdfClass(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS)
public class Location extends Identified {

    private boolean available = true;

    private Boolean transportIsLive;

    private TransportSubType transportSubType;

    private TransportType transportType;
    
    private String uri;

    private String embedCode;
    
    private Policy policy;
    
    @RdfProperty(relation=true)
    public Policy getPolicy() { 
        return this.policy; 
    }

    @RdfProperty
    public Boolean getTransportIsLive() {
        return this.transportIsLive;
    }
    
    @RdfProperty
    public TransportSubType getTransportSubType() { 
        return this.transportSubType; 
    }

    @RdfProperty
    public TransportType getTransportType() { 
        return this.transportType; 
    }

    
    @RdfProperty
    public boolean getAvailable() {
    	return available;
    }
    
    public void setAvailable(boolean available) {
    	this.available = available;
	}
    
    public void setPolicy(Policy policy) { 
        this.policy = policy; 
    }

    public void setTransportIsLive(Boolean transportIsLive) {
        this.transportIsLive = transportIsLive;
    }
    
    public void setTransportSubType(TransportSubType transportSubType) {
		this.transportSubType = transportSubType; 
    }

    public void setTransportType(TransportType transportType) {
		this.transportType = transportType; 
    }

    @RdfProperty(relation=true)
    public String getUri() {
		return uri;
	}
    
    public void setUri(String uri) {
		this.uri = uri;
	}
    
    @RdfProperty
    public String getEmbedCode() {
		return embedCode;
	}
    
    public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}
}
