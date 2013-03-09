package org.atlasapi.media.util;

import java.util.Collection;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.metabroadcast.common.base.MorePredicates;


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

    public static Predicate<Identifiable> idFilter(Collection<Id> ids) {
        return MorePredicates.transformingPredicate(toId(), Predicates.in(ids));
    }
    
}
