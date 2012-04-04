package org.atlasapi.media.content.item;

public final class Clip extends Item {

    public static Clip.Builder builder() {
        return new Clip.Builder();
    }

    public static final class Builder extends Item.BaseBuilder<Clip, Builder> {

        private String clipOf;

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Clip build() {
            Clip clip = new Clip(this);
            clip.clipOf = clipOf;
            return clip;
        }

        public Builder withClipOf(String clipOf) {
            this.clipOf = clipOf;
            return this;
        }
    }

    protected Clip(Builder builder) {
        super(builder);
    }
    private String clipOf;

    public String getClipOf() {
        return clipOf;
    }

    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }
}
