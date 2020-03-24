package org.atlasapi.media.entity;

import java.util.Locale;
import java.util.Objects;

import org.atlasapi.media.entity.simple.LocalizedTitle;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

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

    }

    @Test
    public void testLocales() {

        org.atlasapi.media.entity.LocalizedTitle title = new org.atlasapi.media.entity.LocalizedTitle();
        title.setTitle("Title");
        title.setLocale(Locale.forLanguageTag("en-US"));

        org.atlasapi.media.entity.LocalizedTitle localizedTitleTwo = new org.atlasapi.media.entity.LocalizedTitle();
        localizedTitleTwo.setTitle("Title");

        String languageEn = "en";
        String regionUs = "US";
        localizedTitleTwo.setLocale(new Locale(languageEn, regionUs));

        org.atlasapi.media.entity.LocalizedTitle localizedTitleThree = new org.atlasapi.media.entity.LocalizedTitle();
        localizedTitleThree.setTitle("Title");
        localizedTitleThree.setLocale(Locale.forLanguageTag(languageEn + "-" + regionUs));

        assertEquals(title, localizedTitleTwo);
        assertEquals(localizedTitleTwo, localizedTitleThree);

    }
}
