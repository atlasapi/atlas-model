package org.atlasapi.media.entity.simple;

import java.util.Locale;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LocalizedTitleTest {

    @Test
    public void testLocalizedTitle() {
        //simple model
        LocalizedTitle simpleLocalizedTitle = new LocalizedTitle();
        simpleLocalizedTitle.setTitle("Alt Title");
        simpleLocalizedTitle.setRegion("FR");
        simpleLocalizedTitle.setLanguage("fr");

        LocalizedTitle secondLocalizedTitle = new LocalizedTitle();
        secondLocalizedTitle.setTitle("Other Alt Title");
        secondLocalizedTitle.setLanguage("ru");

        //complex model
        Locale locale = new Locale(simpleLocalizedTitle.getLanguage(), simpleLocalizedTitle.getRegion());
        assertThat(locale.getDisplayCountry(), is("France"));

        Locale secondLocale = new Locale(secondLocalizedTitle.getLanguage(), "");
        assertThat(secondLocale.getDisplayLanguage(), is("Russian"));

        Locale thirdLocale = new Locale("", "");
        assertThat(thirdLocale, is(notNullValue()));

        Locale fourthLocale = new Locale("it");
        assertThat(fourthLocale, is(notNullValue()));

        Locale fifthLocale = new Locale("", "RO");
        assertThat(fifthLocale.getDisplayCountry(), is("Romania"));

        System.out.println(fourthLocale);
    }
}
