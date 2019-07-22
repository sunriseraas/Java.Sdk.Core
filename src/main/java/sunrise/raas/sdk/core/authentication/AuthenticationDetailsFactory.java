package sunrise.raas.sdk.core.authentication;

import java.util.UUID;

public interface AuthenticationDetailsFactory {
    AuthenticationDetails createFromApiKey(UUID clientId, String apiKey);
}
