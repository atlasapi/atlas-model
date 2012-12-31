package org.atlasapi.persistence.content;

import java.util.Set;

import org.atlasapi.media.content.Container;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Series;

import com.google.common.collect.ImmutableSet;
import org.atlasapi.media.entity.ContentGroup;

public enum ContentCategory {

    CHILD_ITEM("children"),
    TOP_LEVEL_ITEM("topLevelItems"),
    PROGRAMME_GROUP("programmeGroups"),
    CONTAINER("containers"),
    CONTENT_GROUP("contentGroups");
    //
    public static final Set<ContentCategory> ITEMS = ImmutableSet.of(TOP_LEVEL_ITEM, CHILD_ITEM);
    public static final Set<ContentCategory> CONTAINERS = ImmutableSet.of(CONTAINER, PROGRAMME_GROUP);
    public static final Set<ContentCategory> TOP_LEVEL_CONTENT = ImmutableSet.of(CONTENT_GROUP, CONTAINER, TOP_LEVEL_ITEM);
    //
    private String name;

    ContentCategory(String name) {
        this.name = name;
    }

    public String tableName() {
        return name;
    }

    public static ContentCategory categoryFor(Described c) {
        if (c instanceof Item) {
            Item item = (Item) c;
            if (item.getContainer() != null) {
                return ContentCategory.CHILD_ITEM;
            } else {
                return ContentCategory.TOP_LEVEL_ITEM;
            }
        } else if (c instanceof Container) {
            if (c instanceof Series && ((Series) c).getParent() != null) {
                return ContentCategory.PROGRAMME_GROUP;
            }
            return ContentCategory.CONTAINER;
        } else if (c instanceof ContentGroup) {
            return ContentCategory.CONTENT_GROUP;
        } else {
            return null;
        }
    }
}
