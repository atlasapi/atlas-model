package org.atlasapi.media.content;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableList;

public abstract class Container extends Content {

    protected ImmutableList<ChildRef> childRefs = ImmutableList.of();

	public Container(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	
    public Container(Id id, Publisher source) {
        super(id, source);
    }
    
    public Container() {}
    
    @RdfProperty(uri="child",relation=true)
    public ImmutableList<ChildRef> getChildRefs() {
        return childRefs;
    }
    
    public void setChildRefs(Iterable<ChildRef> childRefs) {
        this.childRefs = ImmutableList.copyOf(childRefs);
    }
    
    public final static <T extends Item> void copyTo(Container from, Container to) {
        Content.copyTo(from, to);
        to.childRefs = ImmutableList.copyOf(from.childRefs);
    }

    public abstract <V> V accept(ContainerVisitor<V> visitor);
    
    @Override
    public <V> V accept(ContentVisitor<V> visitor) {
        return accept((ContainerVisitor<V>) visitor);
    }
    
}
