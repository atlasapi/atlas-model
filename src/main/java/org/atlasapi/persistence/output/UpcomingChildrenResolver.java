package org.atlasapi.persistence.output;

import org.atlasapi.media.entity.Container;

public interface UpcomingChildrenResolver {

    Iterable<String> availableChildrenFor(Container container);

}