package sunrise.raas.sdk.core.authentication;

import sunrise.raas.sdk.core.helpers.Guard;

import java.util.UUID;

public final class AuthenticationDetailsFactoryImpl implements AuthenticationDetailsFactory {
    @Override
    public AuthenticationDetails createFromApiKey(UUID clientId, String apiKey) {
        Guard.ThrowIfNullOrWhitespace(apiKey, "apiKey");

        return new ApiKeyAuthenticationDetails(clientId, apiKey);
    }
}
