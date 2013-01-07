package org.atlasapi.media.content;

import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Series;

public interface ContainerVisitor<V> {

    V visit(Brand brand);
    
    V visit(Series series);
    
}
