package org.atlasapi.equiv;

import java.util.Set;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.media.common.Id;
import org.atlasapi.output.Annotation;

import com.google.common.util.concurrent.ListenableFuture;

public interface MergingEquivalentsResolver<E extends Equivalent<E>> {

    ListenableFuture<ResolvedEquivalents<E>> resolveIds(Iterable<Id> ids, ApplicationConfiguration config, Set<Annotation> activeAnnotations);
    
}
