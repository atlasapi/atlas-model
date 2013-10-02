package org.atlasapi.application;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;

public class SourceRequest {
    private final Id id;
    private final Id appId;
    private final Publisher source;
    private final UsageType usageType;
    private final String email;
    private final String appUrl;
    private final String reason;
    private final boolean approved;
    
    private SourceRequest(Id id, Id appId, Publisher source, UsageType usageType,
            String email, String appUrl, String reason, boolean approved) {
        this.id = id;
        this.appId = appId;
        this.source = source;
        this.usageType = usageType;
        this.email = email;
        this.appUrl = appUrl;
        this.reason = reason;
        this.approved = approved;
    }
    
    public Id getId() {
        return this.id;
    }
    
    public Id getAppId() {
        return appId;
    }
    
    public Publisher getSource() {
        return source;
    }
    
    public UsageType getUsageType() {
        return usageType;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getAppUrl() {
        return appUrl;
    }
    
    public String getReason() {
        return reason;
    }
    
    public boolean isApproved() {
        return approved;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Builder copy() {
        return new Builder()
            .withId(id)
            .withAppId(appId)
            .withSource(source)
            .withUsageType(usageType)
            .withEmail(email)
            .withAppUrl(appUrl)
            .withReason(reason)
            .withApproved(approved);
    }
    
    public static class Builder {
        private Id id;
        private Id appId;
        private Publisher source;
        private UsageType usageType;
        private String email;
        private String appUrl;
        private String reason;
        private boolean approved;
        
        public Builder() {
        }
        
        public Builder withId(Id id) {
            this.id = id;
            return this;
        }
        
        public Builder withAppId(Id appId) {
            this.appId = appId;
            return this;
        }
        
        public Builder withSource(Publisher source) {
            this.source = source;
            return this;
        }
        
        public Builder withUsageType(UsageType usageType) {
            this.usageType = usageType;
            return this;
        }
        
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        
        public Builder withAppUrl(String appUrl) {
            this.appUrl = appUrl;
            return this;
        }
        
        public Builder withReason(String reason) {
            this.reason = reason;
            return this;
        }
        
        public Builder withApproved(boolean approved) {
            this.approved = approved;
            return this;
        }
        
        public SourceRequest build() {
            return new SourceRequest(id, appId, source, usageType,
                    email, appUrl, reason, approved);
        }
    }
}