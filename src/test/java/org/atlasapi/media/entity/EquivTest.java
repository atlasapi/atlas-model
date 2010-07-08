package org.atlasapi.media.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.atlasapi.media.entity.Equiv;
import org.junit.Test;

public class EquivTest {
    
    @Test
    public void shouldHaveSameKeyEitherWayRound() {
        Equiv equiv = new Equiv("left", "right");
        Equiv equiv2 = new Equiv("right", "left");
        Equiv equiv3 = new Equiv("right", "leftleft");
        
        
        assertThat(equiv.key(), is(equiv2.key()));

        assertThat(equiv3.key(), is(not(equiv.key())));
    }
}
