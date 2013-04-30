package org.atlasapi.media.content;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;

public class ContentEquivalence extends Equivalence<Content> {

    @Override
    protected boolean doEquivalent(Content a, Content b) {
        
        return Objects.equal(a.getDescription(), b.getDescription())
            && Objects.equal(a.getLongDescription(), b.getLongDescription())
            && Objects.equal(a.getLanguages(), b.getLanguages())
            && Objects.equal(a.getLastFetched(), b.getLastFetched())
            && Objects.equal(a.getLastUpdated(), b.getLastUpdated())
            && Objects.equal(a.getYear(), b.getYear());
    }

    @Override
    protected int doHash(Content t) {
        return 0;
    }

}
