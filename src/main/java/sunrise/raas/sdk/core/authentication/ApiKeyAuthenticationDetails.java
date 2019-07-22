package sunrise.raas.sdk.core.authentication;

import java.util.Objects;
import java.util.UUID;

public final class ApiKeyAuthenticationDetails implements AuthenticationDetails {
    private final UUID clientId;
    private final String apiKey;

    ApiKeyAuthenticationDetails(UUID clientId, String apiKey) {
        this.clientId = clientId;
        this.apiKey = apiKey;
    }

    @Override
    public UUID getClientId() {
        return clientId;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public boolean isEqualTo(AuthenticationDetails other) {
        return other instanceof ApiKeyAuthenticationDetails &&
                isEqualTo((ApiKeyAuthenticationDetails) other);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AuthenticationDetails &&
                isEqualTo((AuthenticationDetails) other);
    }

    private boolean isEqualTo(ApiKeyAuthenticationDetails other) {
        return other != null &&
                clientId == other.clientId &&
                apiKey.equalsIgnoreCase(other.apiKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, apiKey);
    }
}
