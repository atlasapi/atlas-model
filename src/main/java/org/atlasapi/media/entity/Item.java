/* Copyright 2009 British Broadcasting Corporation
 Copyright 2009 Meta Broadcast Ltd

 Licensed under the Apache License, Version 2.0 (the "License"); you
 may not use this file except in compliance with the License. You may
 obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 implied. See the License for the specific language governing
 permissions and limitations under the License. */
package org.atlasapi.media.entity;

import java.util.List;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DC;
import org.atlasapi.media.vocabulary.PO;

import com.metabroadcast.common.intl.Country;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import static org.atlasapi.media.entity.ParentRef.parentRefFrom;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 * @author John Ayres (john@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Item extends Content {

    private ParentRef parent;
    private boolean isLongForm = false;
    private Boolean blackAndWhite;
    private String sortKey;
    private Set<ReleaseDate> releaseDates = ImmutableSet.of();
    private Long duration;

    public Item(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
    }

    public Item() {
    }

    public void setParentRef(ParentRef parentRef) {
        this.parent = parentRef;
    }

    public void setContainer(Container container) {
        setParentRef(parentRefFrom(container));
    }

    @RdfProperty(relation = true)
    public ParentRef getContainer() {
        if (parent == null) {
            return null;
        }
        return this.parent;
    }

    @RdfProperty(namespace = DC.NS)
    public boolean getIsLongForm() {
        return isLongForm;
    }

    public void setIsLongForm(boolean isLongForm) {
        this.isLongForm = isLongForm;
    }

    @RdfProperty(relation = true, uri = "person")
    public List<CrewMember> getPeople() {
        return people();
    }

    public void setBlackAndWhite(Boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public Boolean getBlackAndWhite() {
        return blackAndWhite;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public Item copy() {
        Item copy = new Item();
        Item.copyTo(this, copy);
        return copy;
    }

    public static void copyTo(Item from, Item to) {
        Content.copyTo(from, to);
        if (from.parent != null) {
            to.parent = from.parent;
        }
        to.isLongForm = from.isLongForm;
        to.blackAndWhite = from.blackAndWhite;
        to.releaseDates = from.releaseDates;
        to.duration = from.duration;
    }

    public Item withSortKey(String sortKey) {
        this.sortKey = sortKey;
        return this;
    }

    public String sortKey() {
        return sortKey;
    }

    public Set<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(Iterable<ReleaseDate> releaseDates) {
        this.releaseDates = ImmutableSet.copyOf(releaseDates);
    }

    public boolean isChild() {
        return this.parent == null;
    }
    public static final Function<Item, ChildRef> TO_CHILD_REF = new Function<Item, ChildRef>() {

        @Override
        public ChildRef apply(Item input) {
            return input.childRef();
        }
    };
    public static final Function<Item, Item> COPY = new Function<Item, Item>() {

        @Override
        public Item apply(Item input) {
            return (Item) input.copy();
        }
    };

    @Override
    protected String getSortKey() {
        return SortKey.keyFrom(this);
    }
}
