package org.atlasapi.persistence.content;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.metabroadcast.common.base.Maybe;

@RunWith(JMock.class)
public class FilteredScheduleOnlyContentResolverTest {
    private Mockery context = new JUnit4Mockery();

    private final ContentResolver resolver = context.mock(ContentResolver.class);
    private ContentResolver filterer;
    
    private final Item item = new Item("item1", "item1", Publisher.BBC);
    private final Item scheduleOnly = new Item("item2", "item2", Publisher.BBC);
    private final Identified identified = new Identified();
    
    @Before
    public void setUp() {
        scheduleOnly.setScheduleOnly(true);
        filterer = new FilterScheduleOnlyContentResolver(resolver);
    }
    
    @Test
    public void shouldFilterScheduleOnly() {
        final List<String> lookup = ImmutableList.of("item1", "item2", "identified1","nothing");
        Map<String, Maybe<Identified>> result = Maps.newHashMap();
        
        result.put("item1", Maybe.<Identified>just(item));
        result.put("item2", Maybe.<Identified>just(scheduleOnly));
        result.put("identified1", Maybe.just(identified));
        result.put("nothing", Maybe.<Identified>nothing());
        
        final ResolvedContent resolvedContent = new ResolvedContent(result);
        
        context.checking(new Expectations() {{
            oneOf(resolver).findByCanonicalUris(lookup); will(returnValue(resolvedContent));
        }});
        
        ResolvedContent resolved = filterer.findByCanonicalUris(lookup);
        assertEquals(ImmutableList.of("item2","nothing"), resolved.getUnresolved());
    }
}
