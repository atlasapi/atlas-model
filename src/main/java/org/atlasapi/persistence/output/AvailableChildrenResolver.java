package org.atlasapi.persistence.output;

import org.atlasapi.media.content.Container;

public interface AvailableChildrenResolver {

    Iterable<String> availableChildrenFor(Container container);

}