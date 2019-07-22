package sunrise.raas.sdk.core.authentication;

import sunrise.raas.sdk.core.helpers.Equatable;

import java.util.UUID;

public interface AuthenticationDetails extends Equatable<AuthenticationDetails> {
    UUID getClientId();
}
