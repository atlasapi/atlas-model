package org.atlasapi.media.common;


public interface ResourceLister<R> {

    Iterable<R> list();
    
}
