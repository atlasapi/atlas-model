package org.uriplay.media.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class EquivTest {
    
    @Test
    public void shouldHaveSameKeyEitherWayRound() {
        Equiv equiv = new Equiv("left", "right");
        Equiv equiv2 = new Equiv("right", "left");
        
        assertThat(equiv.key(), is(equiv2.key()));
    }
}
