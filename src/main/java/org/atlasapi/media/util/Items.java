package org.atlasapi.media.util;

import java.util.List;

import org.atlasapi.media.entity.simple.Description;
import org.atlasapi.media.entity.simple.Item;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class Items {
    private Items() {
    }
    
    public static final Function<Item, Description> TO_DESCRIPTION = new Function<Item, Description>() {
        @Override
        public Description apply(Item input) {
            return (Description) input;
        }
    };
    
    public static List<Description> getDescriptions(Iterable<Item> items) {
        return ImmutableList.copyOf(Iterables.transform(items, TO_DESCRIPTION));
    }
    
}
