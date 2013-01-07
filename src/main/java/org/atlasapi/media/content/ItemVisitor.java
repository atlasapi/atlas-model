package org.atlasapi.media.content;

import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Film;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Song;

public interface ItemVisitor<V> {

    V visit(Episode episode);
    
    V visit(Film film);
    
    V visit(Song song);

    V visit(Item item);
    
    V visit(Clip clip);

}
