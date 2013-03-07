package org.atlasapi.search.model;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.query.Selection;
import com.metabroadcast.common.url.QueryStringParameters;
import com.metabroadcast.common.url.UrlEncoding;

import org.atlasapi.media.entity.Specialization;

public class SearchQuery {
    
    public static final Builder builder(String query) {
        return new Builder(query);
    }
    
    public static class Builder {

        private final String query;
        private Selection selection = Selection.ALL;
        private Set<Specialization> specializations = ImmutableSet.of();
        private Set<Publisher> publishers = ImmutableSet.of();
        private float title = 0;
        private float broadcast = 0;
        private float catchup = 0;
        private Maybe<Float> priorityChannelWeighting = Maybe.nothing();
        private Maybe<Float> firstBroadcastWeighting = Maybe.nothing();
        private String type;
        private Boolean topLevelOnly;
        private Boolean currentBroadcastsOnly;
        private float priorityChannelWeighting = 1.0f;

        public Builder(String query) {
            this.query = query;
        }
        
        public Builder withSelection(Selection selection) {
            this.selection = selection;
            return this;
        }
        
        public Builder withSpecializations(Iterable<Specialization> specializations) {
            this.specializations = ImmutableSet.copyOf(specializations);
            return this;
        }
        
        public Builder withPublishers(Iterable<Publisher> publishers) {
            this.publishers = ImmutableSet.copyOf(publishers);
            return this;
        }
        
        public Builder withTitleWeighting(float titleWeighting) {
            this.title = titleWeighting;
            return this;
        }
        
        public Builder withBroadcastWeighting(float broadcastWeighting) {
            this.broadcast = broadcastWeighting;
            return this;
        }
        
        public Builder withCatchupWeighting(float catchupWeighting) {
            this.catchup = catchupWeighting;
            return this;
        }
        
        public Builder withPriorityChannelWeighting(Float priorityChannelWeighting) {
            this.priorityChannelWeighting  = Maybe.fromPossibleNullValue(priorityChannelWeighting);
            return this;
        }

        public Builder withFirstBroadcastWeighting(Float firstBroadcastWeighting) {
            this.firstBroadcastWeighting  = Maybe.fromPossibleNullValue(firstBroadcastWeighting);
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }
        
        public Builder withCurrentBroadcastsOnly(boolean currentBroadcastsOnly) {
            this.currentBroadcastsOnly = currentBroadcastsOnly;
            return this;
        }
        
        public Builder withPriorityChannelWeighting(float priorityChannelWeighting) {
            this.priorityChannelWeighting = priorityChannelWeighting;
            return this;
        }
        
        public SearchQuery build() {
            return new SearchQuery(query, selection, specializations, 
                publishers, title, broadcast, catchup, type, topLevelOnly, currentBroadcastsOnly, priorityChannelWeighting);
        }

        public Builder isTopLevelOnly(Boolean topLevel) {
            this.topLevelOnly = topLevel;
            return this;
        }
        
        public SearchQuery build() {
            return new SearchQuery(query, selection, specializations, 
                    publishers, title, broadcast, catchup, 
                    priorityChannelWeighting, firstBroadcastWeighting, 
                    type, topLevel);
        }
    }

    private static final Joiner CSV = Joiner.on(',');

	private final String term;
	private final Selection selection;
    private final Set<Specialization> includedSpecializations;
	private final Set<Publisher> includedPublishers;
    private final float titleWeighting;
    private final float broadcastWeighting;
    private final float catchupWeighting;
	private final Maybe<Float> priorityChannelWeighting;
	private final Maybe<Float> firstBroadcastWeighting;
    private final String type;
    private final float priorityChannelWeighting;
    private final Boolean topLevelOnly;
    private final Boolean currentBroadcastsOnly;


    /**
     * Use a Builder 
     */
    @Deprecated
    public SearchQuery(String term, Selection selection, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
<<<<<<< HEAD
		this(term, selection, Sets.<Specialization>newHashSet(), Sets.<Publisher>newHashSet(), titleWeighting, broadcastWeighting, availabilityWeighting, Maybe.<Float>nothing(), Maybe.<Float>nothing(), null, null);
	}
    
    /**
     * Use a Builder 
     */
    @Deprecated
    public SearchQuery(String term, Selection selection, Iterable<Specialization> includedSpecializations, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
		this(term, selection, includedSpecializations, includedPublishers, titleWeighting, broadcastWeighting, availabilityWeighting, Maybe.<Float>nothing(), Maybe.<Float>nothing(), null, null);
	}
    
    /**
     * Use a Builder 
     */
    @Deprecated
    public SearchQuery(String term, Selection selection, float titleWeighting, float broadcastWeighting, float availabilityWeighting, Maybe<Float> priorityChannelWeighting, Maybe<Float> firstBroadcastWeighting) {
		this(term, selection, Sets.<Specialization>newHashSet(), Sets.<Publisher>newHashSet(), titleWeighting, broadcastWeighting, availabilityWeighting, priorityChannelWeighting, firstBroadcastWeighting, null, null);
=======
		this(term, selection, Sets.<Specialization>newHashSet(), Sets.<Publisher>newHashSet(), titleWeighting, broadcastWeighting, availabilityWeighting, null, null, null, 1.0f);
>>>>>>> master
	}
    
    /**
     * Use a Builder 
     */
    @Deprecated
<<<<<<< HEAD
	public SearchQuery(String term, Selection selection, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting, Maybe<Float> priorityChannelWeighting, Maybe<Float> firstBroadcastWeighting) {
		this(term, selection, Sets.<Specialization>newHashSet(), includedPublishers, titleWeighting, broadcastWeighting, availabilityWeighting, priorityChannelWeighting, firstBroadcastWeighting, null, null);
	}
    
    public SearchQuery(String term, Selection selection, Iterable<Specialization> includedSpecializations, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting, Maybe<Float> priorityChannelWeighting, Maybe<Float> firstBroadcastWeighting, String type, Boolean topLevelOnly) {
=======
	public SearchQuery(String term, Selection selection, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
		this(term, selection, Sets.<Specialization>newHashSet(), includedPublishers, titleWeighting, broadcastWeighting, availabilityWeighting, null, null, null, 1.0f);
	}
    
    public SearchQuery(String term, Selection selection, 
       Iterable<Specialization> includedSpecializations, Iterable<Publisher> includedPublishers, 
       float titleWeighting, float broadcastWeighting, float availabilityWeighting, 
       String type, Boolean topLevelOnly, Boolean currentBroadcastsOnly, float priorityChannelWeighting) {
>>>>>>> master
		this.term = term;
		this.selection = selection;
        this.titleWeighting = titleWeighting;
        this.broadcastWeighting = broadcastWeighting;
        this.catchupWeighting = availabilityWeighting;
        this.type = type;
        this.topLevelOnly = topLevelOnly;
        this.currentBroadcastsOnly = currentBroadcastsOnly;
        this.includedSpecializations = ImmutableSet.copyOf(includedSpecializations);
		this.includedPublishers = ImmutableSet.copyOf(includedPublishers);
		this.priorityChannelWeighting = priorityChannelWeighting;
<<<<<<< HEAD
		this.firstBroadcastWeighting = firstBroadcastWeighting;
=======
>>>>>>> master
	}
	
	public String getTerm() {
		return term;
	}
	
	public Selection getSelection() {
		return selection;
	}
    
    public Set<Specialization> getIncludedSpecializations() {
        return includedSpecializations;
    }
	
	public Set<Publisher> getIncludedPublishers() {
		return includedPublishers;
	}

    public float getTitleWeighting() {
        return titleWeighting;
    }

    public float getBroadcastWeighting() {
        return broadcastWeighting;
    }

    public float getCatchupWeighting() {
        return catchupWeighting;
    }
    
    public Maybe<Float> getPriorityChannelWeighting() {
    	    return priorityChannelWeighting;
    }
    
    public Maybe<Float> getFirstBroadcastWeighting() {
    	    return firstBroadcastWeighting;
    }
    
    	public String type() {
        return this.type;
    }
    
    public Boolean topLevelOnly() {
        return this.topLevelOnly;
    }
    
    public Boolean currentBroadcastsOnly() {
        return this.currentBroadcastsOnly;
    }
    
    public float getPriorityChannelBoost() {
        return this.priorityChannelWeighting;
    }
    
    public QueryStringParameters toQueryStringParameters() {
        QueryStringParameters params = new QueryStringParameters()
            .add("title", UrlEncoding.encode(term))
            .addAll(selection.asQueryStringParameters())
            .add("specializations", CSV.join(includedSpecializations))
            .add("publishers", CSV.join(includedPublishers))
            .add("titleWeighting", String.valueOf(titleWeighting))
            .add("broadcastWeighting",  String.valueOf(broadcastWeighting))
            .add("priorityChannelWeighting", String.valueOf(priorityChannelWeighting))
            .add("catchupWeighting",  String.valueOf(catchupWeighting));
        if (topLevelOnly != null) {
            params.add("topLevelOnly", topLevelOnly.toString());
        }
        if (type != null) {
            params.add("type", type);
        }
<<<<<<< HEAD
        if (priorityChannelWeighting.hasValue()) {
            params.add("priorityChannelWeighting",priorityChannelWeighting.requireValue().toString());
        }
        if (firstBroadcastWeighting.hasValue()) {
            params.add("firstBroadcastWeighting",firstBroadcastWeighting.requireValue().toString());
=======
        if (currentBroadcastsOnly != null) {
            params.add("currentBroadcastsOnly", currentBroadcastsOnly.toString());
>>>>>>> master
        }
        return params;
    }
}
