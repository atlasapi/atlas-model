package org.atlasapi.persistence.output;

import org.atlasapi.media.content.Container;

public interface UpcomingChildrenResolver {

    Iterable<String> availableChildrenFor(Container container);

}