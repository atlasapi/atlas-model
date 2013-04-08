package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DCTERMS;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@RdfClass(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "List")
public class ContentGroup extends Described implements MutableContentList {

    private transient String readHash;
    //
    private Type type;
    private ImmutableSet<ChildRef> contents = ImmutableSet.of();

    public ContentGroup(String uri) {
        super(uri);
        this.type = Type.PLAYLIST;
    }

    public ContentGroup(String uri, Publisher publisher) {
        super(uri, null, publisher);
        this.type = Type.PLAYLIST;
    }
    
    protected ContentGroup(Type type, String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
        this.type = type;
    }

    public ContentGroup() {
        this.type = Type.PLAYLIST;
    }

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public ImmutableList<ChildRef> getContents() {
        return contents.asList();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setContents(Iterable<ChildRef> children) {
        this.contents = ImmutableSet.copyOf(children);
    }

    public void addContent(ChildRef childRef) {
        this.contents = ImmutableSet.<ChildRef>builder().addAll(this.getContents()).add(childRef).build();
    }

    public void addContents(Iterable<ChildRef> childRef) {
        this.contents = ImmutableSet.<ChildRef>builder().addAll(this.getContents()).addAll(childRef).build();
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
    
    public ContentGroupRef contentGroupRef() {
        return new ContentGroupRef(getId(), getCanonicalUri());
    }

    public enum Type {

        FRANCHISE, SEASON, PLAYLIST, PERSON;
    }
}
