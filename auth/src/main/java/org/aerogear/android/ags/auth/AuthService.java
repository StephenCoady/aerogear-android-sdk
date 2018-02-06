package org.aerogear.android.ags.auth;

import org.aerogear.android.ags.auth.credentials.ICredential;
import org.aerogear.android.ags.auth.impl.OIDCAuthCodeImpl;
import org.aerogear.android.ags.auth.impl.OIDCTokenAuthenticatorImpl;
import org.aerogear.mobile.core.MobileCore;
import org.aerogear.mobile.core.ServiceModule;
import org.aerogear.mobile.core.configuration.ServiceConfiguration;

import java.security.Principal;
import java.util.concurrent.Future;

/**
 * Entry point for authenticating users.
 */
public class AuthService implements ServiceModule {

    private AuthenticationChain authenticatorChain;

    /**
     * Instantiates a new AuthService object
     */
    public AuthService() {}

    public void bootstrap(final MobileCore core, final ServiceConfiguration serviceConfig) {
        this.authenticatorChain = AuthenticationChain
            .newChain()
            .with(new OIDCTokenAuthenticatorImpl(serviceConfig))
            .with(new OIDCAuthCodeImpl(serviceConfig))
            .build();
    }

    private void configureDefaultAuthenticationChain(final AuthenticationChain authenticationChain) {

    }

    /**
     * Log in the user with the given credential. Flow to be used to authenticate the user is automatically
     * selected by analysing the received credentials. If the credentials are null,
     * the browser will be open asking for authentication
     *
     * The login will be asynchronous.
     *
     * @param credentials the credential
     * @return a user principal
     */
    public Future<Principal> login(final ICredential credentials) {
        return authenticatorChain.authenticate(credentials);
    }

    /**
     * Log out the given principal.
     * The logout will be asynchronous.
     *
     * @param principal principal to be logged out
     */
    public Future<Void> logout(Principal principal) {
        if (principal instanceof AbstractPrincipal) {
            return authenticatorChain.logout(principal);
        }

        throw new IllegalArgumentException("Unknown principal type " + principal.getClass().getName());
    }

    public void setAuthenticatorChain(AuthenticationChain newChain) {
        this.authenticatorChain = newChain;
    }
}
