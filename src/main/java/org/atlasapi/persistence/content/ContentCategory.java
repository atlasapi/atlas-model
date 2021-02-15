package org.atlasapi.persistence.content;


import com.google.common.collect.ImmutableSet;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.ContentGroup;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Series;

public enum ContentCategory {

    CHILD_ITEM("children"),
    TOP_LEVEL_ITEM("topLevelItems"),
    PROGRAMME_GROUP("programmeGroups"),
    CONTAINER("containers"),
    // This shouldn't be here.
    CONTENT_GROUP("contentGroups");

    public static final ImmutableSet<ContentCategory> ALL = ImmutableSet.copyOf(ContentCategory.values());
    public static final ImmutableSet<ContentCategory> ITEMS = ImmutableSet.of(TOP_LEVEL_ITEM, CHILD_ITEM);
    public static final ImmutableSet<ContentCategory> CONTAINERS = ImmutableSet.of(CONTAINER, PROGRAMME_GROUP);
    public static final ImmutableSet<ContentCategory> TOP_LEVEL_CONTENT = ImmutableSet.of(CONTENT_GROUP, CONTAINER, TOP_LEVEL_ITEM);

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
            if (item.getContainer() != null || (c instanceof Episode && ((Episode) c).getSeriesRef() != null)) {
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
