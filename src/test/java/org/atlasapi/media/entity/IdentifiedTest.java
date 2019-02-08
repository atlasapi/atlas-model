package org.atlasapi.media.entity;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IdentifiedTest {

    @Test
    public void testGettingCustomFieldKeys() {
        Identified identified = new Identified();
        identified.addCustomField("customField", "1");
        identified.addCustomField("additionalField", "2");
        identified.addCustomField("additionalField2", "3");
        assertThat(identified.getCustomFieldKeys(null), is(ImmutableSet.of("customField", "additionalField", "additionalField2")));
        assertThat(identified.getCustomFieldKeys("additional.*"), is(ImmutableSet.of("additionalField", "additionalField2")));
        assertThat(identified.getCustomFieldKeys("custom.*"), is(ImmutableSet.of("customField")));
        assertThat(identified.getCustomFieldKeys(".*Field.*"), is(identified.getCustomFieldKeys(null)));
    }
}
