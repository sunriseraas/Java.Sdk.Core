package sunrise.raas.sdk.core.connection;

import sunrise.raas.sdk.core.authentication.AuthenticationDetails;
import sunrise.raas.sdk.core.configuration.Environment;

public interface ConnectionFactory {
    Connection create(AuthenticationDetails authenticationDetails, Environment environment);
}
