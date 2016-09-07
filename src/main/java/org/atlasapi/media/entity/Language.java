package org.atlasapi.media.entity;

import java.util.Objects;

import javax.annotation.Nullable;

public class Language {

    private final String display;
    private final String code;
    private final String dubbing;

    private Language(
            String code,
            String display,
            @Nullable String dubbing
    ) {
        this.code = code;
        this.display = display;
        this.dubbing = dubbing;
    }

    private Language(Language.Builder builder) {
        this.code = builder.code;
        this.display = builder.display;
        this.dubbing = builder.dubbing;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }

    @Nullable
    public String getDubbing() {
        return dubbing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Language)) {
            return false;
        }
        Language language = (Language) o;
        return Objects.equals(display, language.display) &&
                Objects.equals(code, language.code) &&
                Objects.equals(dubbing, language.dubbing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(display, code, dubbing);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("display", display)
                .add("code", code)
                .add("dubbing", dubbing)
                .toString();
    }

    public Builder copy() {
        return new Builder()
                .withDubbing(dubbing)
                .withDisplay(display)
                .withCode(code);
    }

    public static class Builder {

        private String display;
        private String code;
        private String dubbing;

        private Builder() {

        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withDisplay(String display) {
            this.display = display;
            return this;
        }

        public Builder withDubbing(@Nullable String dubbing) {
            this.dubbing = dubbing;
            return this;
        }

        public Language build() {
            return new Language(this);
        }
    }
}