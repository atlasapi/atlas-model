package org.atlasapi.media.content;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Resolved;
import org.atlasapi.media.util.WriteResult;

import com.google.common.util.concurrent.ListenableFuture;
import com.metabroadcast.common.collect.OptionalMap;


public abstract class ForwardingContentStore implements ContentStore {

    protected ForwardingContentStore() { }
    
    protected abstract ContentStore delegate();
    
    @Override
    public ListenableFuture<Resolved<Content>> resolveIds(Iterable<Id> ids) {
        return delegate().resolveIds(ids);
    }

    @Override
    public OptionalMap<Alias, Content> resolveAliases(Iterable<Alias> aliases, Publisher source) {
        return delegate().resolveAliases(aliases, source);
    }

    @Override
    public <C extends Content> WriteResult<C> writeContent(C content) {
        return delegate().writeContent(content);
    }

}
