package org.atlasapi.media.util;

import java.util.List;

import org.atlasapi.media.entity.simple.Description;
import org.atlasapi.media.entity.simple.Identified;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class Identifieds {

    private Identifieds() {
    }
    
    public static Function<Identified, Description> TO_DESCRIPTION = new Function<Identified, Description>() {
        @Override
        public Description apply(Identified input) {
            return (Description) input;
        }
    };
    
    public static Predicate<Identified> IS_DESCRIPTION = new Predicate<Identified>() {
        @Override
        public boolean apply(Identified input) {
            return input instanceof Description;
        }
    };
    
    public static List<Description> getDescriptions(Iterable<? extends Identified> identifieds) {
        return ImmutableList.copyOf(Iterables.transform(Iterables.filter(identifieds, IS_DESCRIPTION), TO_DESCRIPTION));
    }
}
