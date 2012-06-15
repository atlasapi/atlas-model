package org.atlasapi.media.content;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.metabroadcast.common.time.DateTimeZones;


public class Series extends Container {
	
	private Integer seriesNumber;
	private Integer totalEpisodes;
	private ParentRef parent;
	
	public Series() {}
	
	public Series(String uri, String curie, Publisher publisher) {
		 super(uri, curie, publisher);
	}

	public Series toSummary() {
	   Series summary = new Series(this.getCanonicalUri(), this.getCurie(), this.publisher);
       summary.setTitle(this.getTitle());
       summary.setDescription(this.getDescription());
       summary.withSeriesNumber(seriesNumber);
       summary.setLastUpdated(this.getLastUpdated());
       summary.setThumbnail(this.getThumbnail());
       summary.setImage(this.getImage());
       return summary;
	}

	public Series withSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
		return this;
	}

	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	
	public void setParent(Brand parent) {
	    this.parent = ParentRef.parentRefFrom(parent);
	}

    public void setParentRef(ParentRef parent) {
        this.parent = parent;
    }
	
	
	public ParentRef getParent() {
	    return this.parent;
	}
	
	@Override
	public Container copy() {
	    Series copy = new Series();
	    Container.copyTo(this, copy);
	    copy.seriesNumber = seriesNumber;
	    return copy;
	}
	
	public final static Function<Series, Series> COPY = new Function<Series, Series>() {
        @Override
        public Series apply(Series input) {
            return (Series) input.copy();
        }
    };
    
    public ChildRef childRef() {
        return new ChildRef(this.getCanonicalUri(), Strings.nullToEmpty(this.getTitle()), new DateTime(DateTimeZones.UTC), EntityType.from(this));
    }
    
    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }
    
    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }
}
