package org.atlasapi.media.content.item;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.atlasapi.media.common.Publisher;
import org.atlasapi.media.content.Content;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.metabroadcast.common.intl.Country;

/**
 * 
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 */
public class Item extends Content {
    
    public static BaseBuilder<? extends Item, ?> builder() {
        return new Builder();
    }

    public abstract static class BaseBuilder<T extends Item, B extends BaseBuilder<T, B>> extends Content.Builder<T, B> {

        private Long container;
        
        private ImmutableSet<Version> versions = ImmutableSet.of();
        private ImmutableSet<CrewMember> people = ImmutableSet.of();
        
        private boolean isLongForm = false;
        private Boolean blackAndWhite;
        private ImmutableSet<Country> countriesOfOrigin = ImmutableSet.of();
        //TODO: when an where should sort keys be computed? Probably on write.
        private String sortKey;

        @Override
        public B copy(T item) {
            super.copy(item);
            this.container = item.container;
            this.versions = item.versions;
            this.people = item.people;
            this.isLongForm = item.isLongForm;
            this.blackAndWhite = item.blackAndWhite;
            this.countriesOfOrigin = item.countriesOfOrigin;
            this.sortKey = item.sortKey;
            return builder();
        }

        public B withContainer(Long container) {
            this.container = container;
            return builder();
        }

        public B withVersions(ImmutableSet<Version> versions) {
            this.versions = versions;
            return builder();
        }

        public B withPeople(ImmutableSet<CrewMember> people) {
            this.people = people;
            return builder();
        }

        public B withLongForm(boolean isLongForm) {
            this.isLongForm = isLongForm;
            return builder();
        }

        public B withBlackAndWhite(Boolean blackAndWhite) {
            this.blackAndWhite = blackAndWhite;
            return builder();
        }

        public B withCountriesOfOrigin(ImmutableSet<Country> countriesOfOrigin) {
            this.countriesOfOrigin = countriesOfOrigin;
            return builder();
        }

        B withSortKey(String sortKey) {
            this.sortKey = sortKey;
            return builder();
        }
    }

    public static class Builder extends BaseBuilder<Item, Builder> {

        protected Builder builder() {
            return this;
        }

        @Override
        public Item build() {
            return new Item(this);
        }

    }
    
    private final Long container;
	
	private final ImmutableSet<Version> versions;
	private final ImmutableSet<CrewMember> people;
	private final ImmutableSet<Actor> actors;
	
	private final boolean isLongForm;
	private final Boolean blackAndWhite;
	private final ImmutableSet<Country> countriesOfOrigin;
	private final String sortKey;
	
    protected Item(BaseBuilder<? extends Item, ? extends BaseBuilder<? extends Item, ?>> b) {
        super(b);
        this.container = b.container;
        this.versions = b.versions;
        this.people = b.people;
        this.actors = ImmutableSet.copyOf(Iterables.filter(b.people, Actor.class));
        this.isLongForm = b.isLongForm;
        this.blackAndWhite = b.blackAndWhite;
        this.countriesOfOrigin = b.countriesOfOrigin;
        this.sortKey = b.sortKey;
    }

    @Override
    public BaseBuilder<? extends Item, ? extends BaseBuilder<? extends Item, ?>> copy() {
        return new Builder().copy(this);
    }

    public Long containerId() {
		return this.container;
    }
	
	public boolean isLongForm() {
		return isLongForm;
	}

	public ImmutableSet<Version> versions() {
		return versions;
	}
	
	public Set<Country> countriesOfOrigin() {
	    return countriesOfOrigin;
	}
	
	public ImmutableSet<CrewMember> people() {
	    return people;
	}
	
	public ImmutableSet<Actor> actors() {
	    return actors;
	}
	
    public Boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public String sortKey() {
        return sortKey;
    }
    
    public boolean isChild() {
        return this.container == null;
    }
    
    @Override
    public ItemIdentifier toIdentifier() {
        return new ItemIdentifier(this, sortKey);
    }
    
    public static final class ItemIdentifier extends Identifier implements Comparable<ItemIdentifier> {

        private String sortKey;

        public ItemIdentifier(Identified identified, String sortKey) {
            super(identified);
            this.sortKey = checkNotNull(sortKey);
        }
        
        public ItemIdentifier(Long id, Publisher source, String sortKey) {
            super(id, source);
            this.sortKey = checkNotNull(sortKey);
        }

        @Override
        public int compareTo(ItemIdentifier o) {
            return sortKey.compareTo(o.sortKey);
        }
        
    }
    
}
