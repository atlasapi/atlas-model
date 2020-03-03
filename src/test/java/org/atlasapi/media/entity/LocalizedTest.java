package org.atlasapi.media.entity;

import java.util.Locale;
import java.util.Objects;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class LocalizedTest {

    @Test
    public void testLocalized() {

        //complex model
        Locale locale = new Locale("fr", "FR");
        assertThat(locale.getDisplayCountry(), is("France"));
        Locale alternativeLocale = Locale.forLanguageTag("fr-FR");
        assertEquals(locale, alternativeLocale);

        Locale secondLocale = new Locale("ru", "");
        assertThat(secondLocale.getDisplayLanguage(), is("Russian"));
        Locale alternativeSecondLocale = Locale.forLanguageTag("ru");
        assertEquals(secondLocale, alternativeSecondLocale);

        Locale thirdLocale = new Locale("", "");
        assertThat(thirdLocale, is(notNullValue()));
        Locale alternativeThirdLocale = Locale.forLanguageTag("-");
        assertEquals(thirdLocale, alternativeThirdLocale);

        Locale fourthLocale = new Locale("it");
        assertThat(fourthLocale, is(notNullValue()));
        Locale alternativeFourthLocale = Locale.forLanguageTag("it");
        assertEquals(fourthLocale, alternativeFourthLocale);

        Locale fifthLocale = new Locale("", "RO");
        assertThat(fifthLocale.getDisplayCountry(), is("Romania"));
        Locale alternativeFifthLocale = Locale.forLanguageTag("und-RO");
        assertEquals(fifthLocale, alternativeFifthLocale);

        System.out.println("Debugging...");

    }

}
