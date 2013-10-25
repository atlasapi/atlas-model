package org.atlasapi.application.model.auth;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.social.model.UserRef.UserNamespace;

public enum OAuthProvider {
    TWITTER(UserNamespace.TWITTER, "Sign in with Twitter", "/4.0/auth/twitter/login", "/static/images/sign-in-with-twitter-gray.png");
    
    private final UserNamespace namespace;
    private final String loginPromptMessage;
    private final String authRequestUrl;
    private final String image;
    private static final ImmutableSet<OAuthProvider> ALL = ImmutableSet.copyOf(values());
    
    OAuthProvider(UserNamespace namespace, String loginPromptMessage, String authRequestUrl, String image) {
        this.namespace = namespace;
        this.loginPromptMessage = loginPromptMessage;
        this.authRequestUrl = authRequestUrl;
        this.image = image;
    }
    
    public UserNamespace getNamespace() {
        return namespace;
    }
    
    public String getLoginPromptMessage() {
        return loginPromptMessage;
    }
    
    public String getAuthRequestUrl() {
        return authRequestUrl;
    }
    
    public String getImage() {
        return image;
    }
    
    public static final ImmutableSet<OAuthProvider> all() {
        return ALL;
    }
}
