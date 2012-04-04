package org.atlasapi.media.common;

/**
 * Marks an resource's origin. This is intended to allow simple filtering of data
 * based on their original provider.
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 * @see Publisher
 */
public interface Sourced {

    /**
     * 
     * 
     * @return the original, 3rd-party provider for this data.
     */
    public abstract Publisher source();

}