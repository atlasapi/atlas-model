package org.atlasapi.media.entity;

import java.util.List;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DCTERMS;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class Container<T extends Item> extends Content implements MutableContentList<T> {

	protected ImmutableList<T> contents = ImmutableList.of();

	public Container(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
    
    public Container() {}

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public ImmutableList<T> getContents() {
		return contents;
	}
    
    public List<String> getContentUris() {
    	return Lists.transform(contents, Identified.TO_URI);
    }
    
    public final void setContents(Iterable<? extends T> contents) {
		this.contents = ImmutableList.copyOf(Ordering.from(LAST_UPDATED).reverse().sortedCopy(contents));
		for (T content : this.contents) {
			contentAdded(content);
		}
    }
    
    public final void setContents(T... contents) {
    	setContents(ImmutableList.copyOf(contents));
    }

    protected void contentAdded(T content) {
    	content.setContainer(this);
	}

	public final void addContents(T... contents) {
    	addContents(ImmutableList.copyOf(contents));
    }
    
    @Override
	public void addContents(Iterable<? extends T> contents) {
		setContents(Iterables.concat(this.contents, contents));
    }
    
    public Container<T> toSummary() {
        Container<T> summary = new Container<T>(this.getCanonicalUri(), this.getCurie(), this.getPublisher());
        summary.setTitle(this.getTitle());
        summary.setDescription(this.getDescription());
        return summary;
    }
}
