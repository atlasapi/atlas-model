package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

public class TermsOfUse {

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    // Needed by JAXB :-(
    public TermsOfUse() {}
    public TermsOfUse(String text) {
        this.text = checkNotNull(text);
    }

    public String getText() {
        return text;
    }
}
