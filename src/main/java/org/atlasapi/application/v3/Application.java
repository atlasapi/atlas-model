package org.atlasapi.application.v3;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.metabroadcast.common.time.DateTimeZones;

public class Application {

    public static final Builder application(String slug) {
        return new Builder(checkNotNull(slug));
    }
    
    public static class Builder {

        private final String slug;
        private String title;
        private String desc;
        private DateTime created;
        private DateTime lastUpdated;
        private ApplicationConfiguration config = ApplicationConfiguration.DEFAULT_CONFIGURATION;
        private ApplicationCredentials creds;
        private Long deerId;
        private boolean revoked;
        private Optional<String> stripeCustomerId = Optional.absent();
        private Optional<String> stripeSubscriptionId = Optional.absent();

        public Builder(String slug) {
            this.slug = slug;
        }
        
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        
        public Builder withDescription(String desc) {
            this.desc = desc;
            return this;
        }
        
        public Builder createdAt(DateTime time) {
            this.created = time;
            return this;
        }
        
        /**
         * Fix a last updated time. The build() method automatically sets the lastUpdated
         * only call this if loading in application from a datastore.
         * @param lastUpdated
         * @return
         */
        public Builder withLastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public Builder withConfiguration(ApplicationConfiguration config) {
            this.config = config;
            return this;
        }
        
        public Builder withCredentials(ApplicationCredentials creds) {
            this.creds = creds;
            return this;
        }
        
        public Builder withDeerId(Long deerId) {
            this.deerId = deerId;
            return this;
        }
        
        public Builder withRevoked(boolean revoked) {
            this.revoked = revoked;
            return this;
        }
        
        public Builder withStripeCustomerId(String stripeCustomerId) {
            this.stripeCustomerId = Optional.fromNullable(stripeCustomerId);
            return this;
        }
        
        public Builder withStripeCustomerId(Optional<String> stripeCustomerId) {
            this.stripeCustomerId = stripeCustomerId;
            return this;
        }
        
        public Builder withStripeSubscriptionId(String stripeSubscriptionId) {
            this.stripeSubscriptionId = Optional.fromNullable(stripeSubscriptionId);
            return this;
        }
        
        public Builder withStripeSubscriptionId(Optional<String> stripeSubscriptionId) {
            this.stripeSubscriptionId = stripeSubscriptionId;
            return this;
        }
        
        
        public Application build() {
            Preconditions.checkState(creds != null, "Application credentials must be set");
            Preconditions.checkState(config != null, "Application configuration must be set");
            // If lastUpdated is null (which it should be unless loading object from db) set to now
            if (lastUpdated == null) {
                lastUpdated = DateTime.now(DateTimeZones.UTC);
            }
            return new Application(slug, title, desc, created, lastUpdated, config, creds, deerId, revoked, stripeCustomerId, stripeSubscriptionId);
        }
    }
    
	private final String slug;
	private final String title;
	private final String description;
	private final DateTime created;
	private final DateTime lastUpdated;

	private final ApplicationConfiguration configuration;
	private final ApplicationCredentials credentials;
	
	private final Long deerId;
	private final boolean revoked;
    private final Optional<String> stripeCustomerId;
    private final Optional<String> stripeSubscriptionId;

	private Application(String slug, 
	        String title, 
	        String desc, 
	        DateTime created, 
	        DateTime lastUpdated, 
	        ApplicationConfiguration config, 
	        ApplicationCredentials creds, 
	        Long deerId, 
	        boolean revoked,
	        Optional<String> stripeCustomerId,
	        Optional<String> stripeSubscriptionId) {
		this.slug = slug;
        this.title = title;
        this.description = desc;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.configuration = config;
        this.credentials = creds;
        this.deerId = deerId;
        this.revoked = revoked;
        this.stripeCustomerId = checkNotNull(stripeCustomerId);
        this.stripeSubscriptionId = checkNotNull(stripeSubscriptionId);
	}

	public String getSlug() {
		return slug;
	}

	public String getTitle() {
		return title;
	}
	
	public ApplicationConfiguration getConfiguration() {
		return configuration;
	}

	public ApplicationCredentials getCredentials() {
		return credentials;
	}
	
    public String getDescription() {
        return description;
    }

    public DateTime getCreated() {
        return created;
    }

    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    public Long getDeerId() {
        return deerId;
    }
    
    public boolean isRevoked() {
        return revoked;
    }
    
    public Optional<String> getStripeCustomerId() {
        return stripeCustomerId;
    }
    
    public Optional<String> getStripeSubscriptionId() {
        return stripeSubscriptionId;
    }
    
    public Builder copy() {
        return new Builder(slug)
            .withTitle(title)
            .withDescription(description)
            .createdAt(created)
            .withLastUpdated(lastUpdated)
            .withConfiguration(configuration)
            .withCredentials(credentials)
            .withDeerId(deerId)
            .withRevoked(revoked)
            .withStripeCustomerId(stripeCustomerId)
            .withStripeSubscriptionId(stripeSubscriptionId);
    }

	@Override
	public int hashCode() {
		return Objects.hashCode(getSlug());
	}

	@Override
	public boolean equals(Object that) {
	    if (this == that) {
	        return true;
	    }
		if (that instanceof Application) {
			Application other = (Application) that;
			return slug.equals(other.slug);
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", getSlug(), getTitle());
	}
}
