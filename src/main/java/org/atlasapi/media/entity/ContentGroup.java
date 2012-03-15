package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DCTERMS;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;

import com.google.common.collect.ImmutableList;

@RdfClass(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "List")
public class ContentGroup extends Described implements MutableContentList {

    private transient String readHash;
    //
    private Type type;
    private ImmutableList<ChildRef> contents = ImmutableList.of();

    public ContentGroup(String uri) {
        super(uri);
        this.type = Type.PLAYLIST;
    }

    public ContentGroup(String uri, Publisher publisher) {
        super(uri, null, publisher);
        this.type = Type.PLAYLIST;
    }
    
    protected ContentGroup(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
    }

    public ContentGroup() {
    }

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public ImmutableList<ChildRef> getContents() {
        return contents;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setContents(Iterable<ChildRef> children) {
        this.contents = ImmutableList.copyOf(children);
    }

    public void addContents(ChildRef childRef) {
        this.contents = ImmutableList.<ChildRef>builder().addAll(this.getContents()).add(childRef).build();
    }

    public void addContents(Iterable<ChildRef> childRef) {
        this.contents = ImmutableList.<ChildRef>builder().addAll(this.getContents()).addAll(childRef).build();
    }
    
    public void setReadHash(String readHash) {
        this.readHash = readHash;
    }
    
    public boolean hashChanged(String newHash) {
        return readHash == null || !this.readHash.equals(newHash);
    }

    public ContentGroup copy() {
        ContentGroup copy = new ContentGroup();
        copyTo(this, copy);
        return copy;
    }

    public enum Type {

        FRANCHISE, SEASON, PLAYLIST;
    }
}
