package org.atlasapi.media.util;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;

import com.google.common.base.Function;


public final class Identifiables {

    private Identifiables() {}
    
    public static final Function<Identifiable, Id> toId() {
        return ToIdFunction.INSTANCE;
    }
    
    private enum ToIdFunction implements Function<Identifiable, Id> {
        
        INSTANCE;
        
        @Override
        public Id apply(Identifiable input) {
            return input.getId();
        }
        
        @Override
        public String toString() {
            return "toId";
        }
        
    }
    
}
