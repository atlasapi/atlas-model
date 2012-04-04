package org.atlasapi.media.content;

import org.atlasapi.media.common.Identifier;

public abstract class ContentIdentifier extends Identifier {

    protected ContentIdentifier(Content identified) {
        super(identified);
    }

}
