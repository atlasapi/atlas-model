package org.atlasapi.persistence.content;

import java.util.Set;

import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.container.Container;
import org.atlasapi.media.content.container.Series;
import org.atlasapi.media.content.item.Item;

import com.google.common.collect.ImmutableSet;

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

    public static ContentCategory categoryFor(Content c) {
        if (c instanceof Item) {
            Item item = (Item) c;
            if (item.containerId() != null) {
                return ContentCategory.CHILD_ITEM;
            } else {
                return ContentCategory.TOP_LEVEL_ITEM;
            }
        } else if (c instanceof Container) {
            if (c instanceof Series && ((Series) c).parent() != null) {
                return ContentCategory.PROGRAMME_GROUP;
            }
            return ContentCategory.CONTAINER;
        } else {
            return null;
        }
    }
}
