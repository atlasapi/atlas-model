package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.metabroadcast.common.query.Selection;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="content")
@XmlType(name="content", namespace=PLAY_SIMPLE_XML.NS)
public class ContentQueryResult {

    public ContentQueryResult() {}
    
    public ContentQueryResult(Description content) {
        this.contents = Lists.newArrayList(content);
    }
    
    public ContentQueryResult(Iterable<Description> contents) {
        this.contents = Lists.newArrayList(contents);
    }
    
	private List<Description> contents = Lists.newArrayList();

	public void add(Description content) {
		contents.add(content);
	}

	@XmlElements({ 
		@XmlElement(name = "item", type = Item.class, namespace=PLAY_SIMPLE_XML.NS),
		@XmlElement(name = "playlist", type = Playlist.class, namespace=PLAY_SIMPLE_XML.NS) 
	})
	public List<Description> getContents() {
		return contents;
	}

	public void setContents(Iterable<? extends Description> items) {
		this.contents = Lists.newArrayList(items);
	}
	
	public boolean isEmpty() {
	    return contents.isEmpty();
	}

	@Override
	public int hashCode() {
		return contents.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this instanceof ContentQueryResult) {
			ContentQueryResult other = (ContentQueryResult) obj;
			return contents.equals(other.contents);
		}
		return false;
	}
	
	@Override
	public String toString() {
	    return Objects.toStringHelper(this).addValue(contents).toString();
	}
	
	@XmlTransient//@XmlElement(name = "context")
	public Identified getContext() {
	    return null;
	}

	private static class TopicContentQueryResult extends ContentQueryResult {
	    private Topic context;
	    
	    @Override
	    public Identified getContext() {
	        return context;
	    }
	    
	    public void setContext(Topic context) {
	        this.context = context;
	    }
	}
	
	   private static class ProductContentQueryResult extends ContentQueryResult {
	        private Product context;
	        
	        @Override
	        public Identified getContext() {
	            return context;
	        }
	        
	        public void setContext(Product context) {
	            this.context = context;
	        }
	    }
	
    public static ContentQueryResult withContext(Topic topic) {
        TopicContentQueryResult result = new TopicContentQueryResult();
        result.setContext(topic);
        return result;
    }
    
    public static ContentQueryResult withContext(Product topic) {
        ProductContentQueryResult result = new ProductContentQueryResult();
        result.setContext(topic);
        return result;
    }
    
    public static class Pagination {
        
        public static Pagination fromSelection(Selection selection) {
            Pagination pagination = new Pagination();
            pagination.setLimit(selection.getLimit());
            pagination.setOffset(selection.getOffset());
            return pagination;
        }

        private int limit;
        private int offset;

        @XmlTransient
        public int getLimit() {
            return limit;
        }
        public void setLimit(int limit) {
            this.limit = limit;
        }

        @XmlTransient
        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }
    }
    
    private Pagination pagination;

    @XmlTransient
    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
