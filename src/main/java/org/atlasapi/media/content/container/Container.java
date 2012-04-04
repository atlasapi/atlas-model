package org.atlasapi.media.content.container;


import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.ContentIdentifier;
import org.atlasapi.media.content.item.Item.ItemIdentifier;

import com.google.common.collect.ImmutableList;

public class Container extends Content {
    
    public static Builder<? extends Container, ? extends Builder<?,?>> builder() {
        return new ContainerBuilder();
    }
    
    public static abstract class Builder<T extends Container, B extends Builder<T,B>> extends Content.Builder<T, B> {
        
        protected ImmutableList<ItemIdentifier> subItems;
        
        public B copy(T container) {
            super.copy(container);
            this.subItems = container.subItems;
            return builder();
        };
        
    }
    
    public static final class ContainerBuilder extends Builder<Container, ContainerBuilder> {

        @Override
        protected ContainerBuilder builder() {
            return this;
        }

        @Override
        public Container build() {
            return new Container(this);
        }
        
    }

	protected final ImmutableList<ItemIdentifier> subItems;
    
    protected Container(Builder<? extends Container, ? extends Builder<? extends Container, ?>> builder) {
        super(builder);
        this.subItems = builder.subItems;
    }

    @Override
    public Builder<? extends Container, ? extends Builder<? extends Container, ?>> copy() {
        return new ContainerBuilder().copy(this);
    }
    
    public ImmutableList<ItemIdentifier> subItems() {
        return subItems;
    }
    
    @Override
    public ContainerIdentifier toIdentifier() {
        return new ContainerIdentifier(this);
    }
    
    public static class ContainerIdentifier extends ContentIdentifier {

        protected ContainerIdentifier(Content identified) {
            super(identified);
        }

    }

}
