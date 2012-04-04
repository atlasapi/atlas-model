package org.atlasapi.media.topic;

import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;

public final class Topic extends Identified implements Described {
    
    public static Builder builder(TopicCategory category) {
        return new Builder(category);
    }

    public final static class Builder extends Identified.Builder<Topic, Builder> {

        private TopicCategory category;
        private String namespace;
        private String value;
        private Description description;
        
        public Builder(TopicCategory category) {
            this.category = category;
        }

        @Override
        public Builder copy(Topic topic) {
            return super.copy(topic)
                    .withNamespace(topic.namespace)
                    .withDescription(topic.description)
                    .withValue(topic.value);
        }

        public Builder withDescription(Description description) {
            this.description = description;
            return builder();
        }

        public Builder withDescription(Description.Builder descriptionBuilder) {
            return withDescription(descriptionBuilder.build());
        }
        
        public Builder withNamespace(String namespace) {
            this.namespace = namespace;
            return builder();
        }
        
        public Builder withValue(String value) {
            this.value = value;
            return builder();
        }

        protected Builder builder() {
            return this;
        }

        @Override
        public Topic build() {
            return new Topic(this);
        }

    }
    
    private TopicCategory category;
    private String namespace;
    private String value;
    private Description description;
    
    public Topic(Builder builder) {
        super(builder);
        this.category = builder.category;
        this.namespace = builder.namespace;
        this.value = builder.value;
        this.description = builder.description;
    }
    
    @Override
    public Builder copy() {
        return new Builder(category).copy(this);
    }

    public TopicCategory category() {
        return this.category;
    }

    public String namespace() {
        return this.namespace;
    }

    public String value() {
        return this.value;
    }
    
    public Description description() {
        return this.description;
    }
    
    @Override
    public TopicIdentifier toIdentifier() {
        return new TopicIdentifier(this);
    }
    
    public static final class TopicIdentifier extends Identifier {

        public TopicIdentifier(Topic topic) {
            super(topic);
        }
    }
}
