package org.atlasapi.persistence.auth;

import org.atlasapi.application.model.auth.OAuthRequest;

import com.google.common.base.Optional;
import com.metabroadcast.common.social.model.UserRef.UserNamespace;

/**
 * This class stores requests for tokens, the calling app will have to 
 * access the auth url for the provider and respond with a verifier
 * @author liam
 *
 */
public interface TokenRequestStore {
    /** Stores a request for oauth authentication **/
    void store(OAuthRequest oauthRequest);
    /** 
     * Looks up an OAuthRequest by public token and namespace.
     * Returns absent if not found
     */
    Optional<OAuthRequest> remove(UserNamespace namespace, String token);
}
