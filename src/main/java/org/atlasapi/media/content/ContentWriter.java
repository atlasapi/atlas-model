package org.atlasapi.media.content;

import org.atlasapi.media.util.WriteResult;

public interface ContentWriter {

    <C extends Content> WriteResult<C> writeContent(C content);
    
}
