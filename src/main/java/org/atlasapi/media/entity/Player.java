package org.atlasapi.media.entity;


public class Player extends Described {

    @Override
    public Described copy() {
        Player copy = new Player();
        copyTo(this, copy);
        return copy;
    }

}
