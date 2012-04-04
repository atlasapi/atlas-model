package org.atlasapi.media.common;

//import static org.hamcrest.Matchers.allOf;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.hasItem;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import org.hamcrest.FeatureMatcher;
//import org.hamcrest.Matcher;

import com.google.common.base.Function;

public class Identifieds {

    private Identifieds() { }
    
    public static final Function<Identified, Long> TO_ID = new Function<Identified, Long>() {
        @Override
        public Long apply(Identified input) {
            return input.id();
        }
    };
    
    public static final Function<Identified, Publisher> TO_SOURCE = new Function<Identified, Publisher>() {
        @Override
        public Publisher apply(Identified input) {
            return input.source();
        }
    };
    
//    private static class HasId extends FeatureMatcher<Identified, Long> {
//        public HasId(Matcher<? super Long> toStringMatcher) {
//          super(toStringMatcher, "identified with id", "id");
//        }
//        
//        @Override
//        protected Long featureValueOf(Identified actual) {
//          return actual.id();
//        }
//    }
//    
//    public static final Matcher<Identified> hasId(Matcher<? super Long> longMatcher) {
//        return new HasId(longMatcher);
//    }
//    
//    public static final Matcher<Identified> hasId(Long id) {
//        return new HasId(equalTo(id));
//    }
//    
//    private static class HasEquivalents extends FeatureMatcher<Identified, Set<Long>> {
//
//        public HasEquivalents(Matcher<? super Set<Long>> subMatcher) {
//            super(subMatcher, "identified with equivalents", "equivalents");
//        }
//
//        @Override
//        protected Set<Long> featureValueOf(Identified actual) {
//            return actual.equivalents();
//        }
//        
//    }
//    
//    public static final Matcher<Identified> hasEquivalents(Matcher<? super Set<Long>> longSetMatcher) {
//        return new HasEquivalents(longSetMatcher);
//    }
//    
//    public static final Matcher<Identified> hasEquivalents(Set<Long> equivalents) {
//        List<Matcher<? super Iterable<Long>>> all = new ArrayList<Matcher<? super Iterable<Long>>>(equivalents.size());
//        for (Long element : equivalents) {
//            all.add(hasItem(element));
//        }
//        return new HasEquivalents(allOf(all));
//    }
//    
//    public static final Matcher<Identified> hasEquivalent(Long equivalent) {
//        return new HasEquivalents(hasItem(equivalent));
//    }
//    
//    public static final Matcher<Identified> hasEquivalent(Matcher<? super Long> equivalentMatcher) {
//        return new HasEquivalents(hasItem(equivalentMatcher));
//    }
}
