package org.atlasapi.media.content.group;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.atlasapi.media.common.Publisher;
import org.atlasapi.media.content.ContentIdentifier;
import org.atlasapi.media.vocabulary.DCTERMS;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@RdfClass(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "List")
public final class ContentGroup extends Identified implements Described {

    public static final Builder builder(ContentGroupType type) {
        return new Builder(type);
    }
    
    public static final class Builder extends Identified.Builder<ContentGroup,Builder> {

        private ContentGroupType type;
        private Description description;
        private ImmutableSet<ContentIdentifier> contents;

        @Override
        public Builder copy(ContentGroup t) {
            return super.copy(t);
        }
        
        public Builder(ContentGroupType type) {
            this.type = type;
        }

        public Builder withDescription(Description description) {
            this.description = description;
            return builder();
        }
        
        public Builder withDescription(Description.Builder description) {
            return withDescription(description.build());
        }
        
        public Builder withContents(Iterable<ContentIdentifier> contents) {
            this.contents = ImmutableSet.copyOf(contents);
            return this;
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public ContentGroup build() {
            return new ContentGroup(this);
        }
        
    }
    
    private ContentGroupType type;
    private Description description;
    private ImmutableList<ContentIdentifier> contents = ImmutableList.of();

    public ContentGroup(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.description = builder.description;
        this.contents = builder.contents.asList();
    }

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public ImmutableList<ContentIdentifier> contents() {
        return contents;
    }

    public ContentGroupType type() {
        return type;
    }
    
    @Override
    public Description description() {
        return description;
    }

    public Builder copy() {
        return new Builder(type).copy(this);
    }
    
    @Override
    public Identifier toIdentifier() {
        return new ContentGroupIdentifier(this);
    }

    public static class ContentGroupIdentifier extends Identifier {

        protected ContentGroupIdentifier(Identified identified) {
            super(identified);
        }
        
        public ContentGroupIdentifier(Long id, Publisher source) {
            super(id, source);
        }
        
    }
    
}
