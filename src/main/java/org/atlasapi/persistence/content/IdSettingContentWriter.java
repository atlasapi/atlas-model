package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Item;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.persistence.lookup.entry.LookupEntryStore;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.metabroadcast.common.ids.IdGenerator;

public class IdSettingContentWriter implements ContentWriter {

    private final LookupEntryStore lookupStore;
    private final ContentWriter delegate;
    private final IdGenerator generator;

    public IdSettingContentWriter(LookupEntryStore lookupStore, IdGenerator generator, ContentWriter delegate) {
        this.lookupStore = lookupStore;
        this.delegate = delegate;
        this.generator = generator;
    }
    
    @Override
    public void createOrUpdate(Item item) {
        delegate.createOrUpdate(ensureId(item));
    }
    
    //Check for existence of a lookup entry for the content. If none, generate a new ID for the content.
    private <T extends Content> T ensureId(T content) {
        Iterable<LookupEntry> entries = lookupStore.entriesForUris(ImmutableList.of(content.getCanonicalUri()));

        if (Iterables.isEmpty(entries)) {
            content.setId(generator.generateRaw());
        } else { //ensures an adapter can't override and assign a new id for the content.
            LookupEntry entry = Iterables.getOnlyElement(entries);
            content.setId(entry.id());
        }
        
        return content;
    }

    @Override
    public void createOrUpdate(Container container) {
        delegate.createOrUpdate(ensureId(container));
    }

}
