package org.atlasapi.persistence.content;

import java.util.Set;

import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Series;

import com.google.common.collect.ImmutableSet;

public enum ContentCategory {
    
    CHILD_ITEM("children"), 
    TOP_LEVEL_ITEM("topLevelItems"), 
    PROGRAMME_GROUP("programmeGroups"), 
    CONTAINER("containers");

    public static final Set<ContentCategory> ITEMS = ImmutableSet.of(TOP_LEVEL_ITEM, CHILD_ITEM);
    public static final Set<ContentCategory> CONTAINERS = ImmutableSet.of(CONTAINER, PROGRAMME_GROUP);
    public static final Set<ContentCategory> TOP_LEVEL_CONTENT = ImmutableSet.of(CONTAINER, TOP_LEVEL_ITEM);
    
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
            if (c instanceof Series) {
                return ContentCategory.PROGRAMME_GROUP;
            }
            return ContentCategory.CONTAINER;
        } else {
            return null;
        }
    }
}
