package org.atlasapi.media.entity;

import java.util.List;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DCTERMS;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

@RdfClass(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "List")
public class ContentGroup extends Described implements MutableContentList<Content> {
	
	private ImmutableList<Content> contents = ImmutableList.of();
	private ImmutableList<String> contentUris = ImmutableList.of();

	public ContentGroup(String uri, String curie) {
		super(uri, curie);
	}
	
	public ContentGroup(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
    
    public ContentGroup() {}

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public ImmutableList<Content> getContents() {
		return contents;
	}
    
    public final void setContents(Content...contents) {
    	setContents(ImmutableList.copyOf(contents));
    }
    
    public final void setContents(Iterable<? extends Content> contents) {
        List<? extends Content> orderedContents = Ordering.from(LAST_UPDATED).reverse().sortedCopy(contents);
    	Set<Content> evictedContent = Sets.difference(ImmutableSet.copyOf(this.contents), ImmutableSet.copyOf(orderedContents));
    	for (Content item : evictedContent) {
    		contentEvicted(item);
    	}
    	for (Content content : orderedContents) {
    		contentAdded(content);
		}
		this.contents = ImmutableList.copyOf(orderedContents);
	}
    
	public void addContents(Content... contents) {
		addContents(ImmutableList.copyOf(contents));
	}
    
    @Override
	public void addContents(Iterable<? extends Content> contents) {
		setContents(Iterables.concat(this.contents, contents));
	}
    
    protected void contentAdded(Content content) {
    	/* extension point for subclasses, if an exception is throw the whole add attempt is aborted */
	}
    
    protected void contentEvicted(Content content) {
    	/* extension point for subclasses, if an exception is throw the whole add attempt is aborted */
    }

	public List<String> getContentUris() {
        List<String> uris = Lists.newArrayList();
        for (Content content : contents) {
            uris.add(content.getCanonicalUri());
        }
        for (String uri: contentUris) {
            if (!uris.contains(uri)) {
                uris.add(uri);
            }
        }
        return uris;
    }
    
    public void setContentUris(Iterable<String> contentUris) {
		this.contentUris = ImmutableList.copyOf(ImmutableSet.copyOf(contentUris));
	}
}
