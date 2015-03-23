package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

public class TermsOfUse {

    private final String text;

    public TermsOfUse(String text) {
        this.text = checkNotNull(text);
    }

    public String getText() {
        return text;
    }
}
