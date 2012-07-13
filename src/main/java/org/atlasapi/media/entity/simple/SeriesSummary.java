package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="seriesSummary", namespace=PLAY_SIMPLE_XML.NS)
public class SeriesSummary extends Identified {

	private String title;
	private String description;
	private Integer seriesNumber;
    private Integer totalEpisodes;
	
	public SeriesSummary() { /* required for XML/JSON tools */ }
	
	public SeriesSummary(String uri) {
		super(uri);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	
	public void setSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }
	
	public SeriesSummary copy() {
        SeriesSummary copy = new SeriesSummary();
        
        copyTo(copy);
        
        copy.setTitle(getTitle());
        copy.setDescription(getDescription());
        copy.setSeriesNumber(getSeriesNumber());
        copy.setTotalEpisodes(getTotalEpisodes());
        
        return copy;
    }
}
