package org.atlasapi.persistence.content;

import static com.google.common.collect.ImmutableSet.copyOf;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;


public enum ContentTable {
    CHILD_ITEMS("child items"), 
    PROGRAMME_GROUPS("programme groups"), 
    TOP_LEVEL_ITEMS("top level items"), 
    TOP_LEVEL_CONTAINERS("containers");
    
    private String name;

    ContentTable(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public static ContentTable fromString(String name) {
        return nameMap.get(name);
    }
    
    private static Map<String, ContentTable> nameMap = Maps.uniqueIndex(copyOf(ContentTable.values()), new Function<ContentTable, String>() {
        @Override
        public String apply(ContentTable input) {
            return input.name;
        }
    });
}
