package org.atlasapi.media.common;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

public abstract class Identifier {

    private final Long id;
    private final Publisher source;

    protected Identifier(Identified identified) {
        this(checkNotNull(identified, "Can't create identifier for null identified").id(), 
            identified.source());
    }
    
    protected Identifier(Long id, Publisher source) {
        this.id = checkNotNull(id, "Can't create identifier with null id");
        this.source = checkNotNull(source, "Can't create identifier with null source");
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(getClass()).addValue(source).addValue(id).toString();
    }
    
    public Long id() {
        return id;
    }

    public Publisher source() {
        return source;
    }
    
}
