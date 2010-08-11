package org.atlasapi.media.entity;

public class Series extends Playlist {
	
	private Integer seriesNumber;
	private boolean isASummary = false;
	
	public Series() {}
	
	public Series(String uri, String curie) {
		super(uri, curie);
	}
	
	public Series toSummary() {
	   Series summary = new Series(this.getCanonicalUri(), this.getCurie());
       summary.setTitle(this.getTitle());
       summary.setDescription(this.getDescription());
       summary.withSeriesNumber(seriesNumber);
       summary.markAsSummary();
       return summary;
	}

	public void markAsSummary() {
		this.isASummary = true;
	}
	
	public boolean isASummary() {
		return isASummary;
	}

	public Series withSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
		return this;
	}
	
	@Override
    public void addItem(Item item) {
        super.addItem(item);
        
        if (item instanceof Episode) {
            ((Episode) item).setSeries(this);
        }
    }

	public Integer getSeriesNumber() {
		return seriesNumber;
	}
}
