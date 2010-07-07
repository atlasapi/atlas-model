/* Copyright 2010 Meta Broadcast Ltd

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

import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PO;
import org.joda.time.DateTime;

import com.google.common.collect.Sets;

@RdfClass(namespace = PO.NS)
public class Policy extends Description {

	private DateTime availabilityStart;

	private DateTime availabilityEnd;
	
    private DateTime drmPlayableFrom;
    
    private Set<Country> availableCountries;

	private Integer availabilityLength;
    
	@RdfProperty(relation = false, namespace=PO.NS, uri="availableCountry")
    public Set<Country> getAvailableCountries() {
		return availableCountries;
	}

	public void setAvailableCountries(Set<Country> availableCountries) {
		this.availableCountries = availableCountries;
	}

	public void addAvailableCountry(Country country) {
    	if (availableCountries == null) {
    		availableCountries = Sets.newHashSet();
    	}
    	availableCountries.add(country);
    }
    
    @RdfProperty(relation=false)
    public DateTime getAvailabilityStart() { 
        return availabilityStart;
    }

    @RdfProperty(relation=false)
    public DateTime getDrmPlayableFrom() { 
        return drmPlayableFrom;
    }
    
    @RdfProperty(relation=false)
    public DateTime getAvailabilityEnd() {
		return availabilityEnd;
	}
    
    public void setAvailabilityEnd(DateTime availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}
    
    public void setAvailabilityStart(DateTime availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public void setDrmPlayableFrom(DateTime drmPlayableFrom) {
        this.drmPlayableFrom = drmPlayableFrom;
    }

	public Policy withAvailabilityStart(DateTime end) {
		setAvailabilityStart(end);
		return this;
	}
	
	public Policy withAvailabilityEnd(DateTime start) {
		setAvailabilityEnd(start);
		return this;
	}

	public Policy withDrmPlayableFrom(DateTime from) {
		setDrmPlayableFrom(from);
		return this;
	}

	public void setAvailabilityLength(Integer seconds) {
		this.availabilityLength = seconds;
	}
	
	@RdfProperty(relation=false)
	public Integer getAvailabilityLength() {
		return availabilityLength;
	}

	public Policy withAvailableCountries(Country... countries) {
		setAvailableCountries(Sets.newHashSet(countries));
		return this;
	}
}
