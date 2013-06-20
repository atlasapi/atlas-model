package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.ItemVisitor;
import org.atlasapi.media.vocabulary.PO;

import com.google.common.base.Function;

@RdfClass(namespace = PO.NS)
public class Clip extends Item {

	private String clipOf;
	
	public Clip(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	   
    public Clip(Id id, Publisher source) {
        super(id, source);
    }
	
	public void setClipOf(String clipOf) {
		this.clipOf = clipOf;
	}
	
	public String getClipOf() {
		return clipOf;
	}
	
	public Clip() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getCanonicalUri() != null && obj instanceof Clip) {
            return getCanonicalUri().equals(((Clip) obj).getCanonicalUri());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getCanonicalUri() == null ? super.hashCode() 
                                         : getCanonicalUri().hashCode();
    }
	
	@Override
	public Clip copy() {
	    Clip clip = new Clip();
	    Item.copyTo(this, clip);
	    clip.clipOf = clipOf;
	    return clip;
	}
	
	public final static Function<Clip, Clip> COPIES = new Function<Clip, Clip>() {
        @Override
        public Clip apply(Clip input) {
            return input.copy();
        }
	};
	
	@Override
	public <V> V accept(ItemVisitor<V> visitor) {
	    return visitor.visit(this);
	}
	
}
