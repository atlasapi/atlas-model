package org.atlasapi.media.content;

import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Film;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Series;
import org.atlasapi.media.entity.Song;


public abstract class ContentVisitorAdapter<V> implements ContentVisitor<V> {

    @Override
    public V visit(Brand brand) {
        return visitContainer(brand);
    }

    @Override
    public V visit(Series series) {
        return visitContainer(series);
    }

    @Override
    public V visit(Episode episode) {
        return visitItem(episode);
    }

    @Override
    public V visit(Film film) {
        return visitItem(film);
    }

    @Override
    public V visit(Song song) {
        return visitItem(song);
    }

    @Override
    public V visit(Item item) {
        return visitItem(item);
    }

    @Override
    public V visit(Clip clip) {
        return visitItem(clip);
    }

    protected V visitItem(Item item) {
        return null;
    }
    
    protected V visitContainer(Container container) {
        return null;
    }
}
