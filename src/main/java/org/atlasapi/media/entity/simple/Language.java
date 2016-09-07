package org.atlasapi.media.entity.simple;

import javax.annotation.Nullable;

public class Language {

    private String display;
    private String code;
    private String dubbing;
    
    public Language(){}
    
    public Language(String code, String display) {
        this.code = code;
        this.display = display;
    }

    private Language(Language.Builder builder) {
        this.display = builder.display;
        this.code = builder.code;
        this.dubbing = builder.dubbing;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Language create() {
        return new Language();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setDubbing(@Nullable String dubbing) {
        this.dubbing = dubbing;
    }

    @Nullable
    public String getDubbing() {
        return dubbing;
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
