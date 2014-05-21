package org.atlasapi.media.entity;


public class Service extends Described {

    @Override
    public Described copy() {
        Service copy = new Service();
        copyTo(this, copy);
        return copy;
    }

}
