package sunrise.raas.sdk.core.connection;

import sunrise.raas.sdk.core.authentication.AuthenticationDetails;
import sunrise.raas.sdk.core.configuration.Environment;
import sunrise.raas.sdk.core.helpers.Guard;

public final class ConnectionFactoryImpl implements ConnectionFactory {
    @Override
    public Connection create(AuthenticationDetails authenticationDetails, Environment environment) {
        Guard.ThrowIfNull(authenticationDetails, "authenticationDetails");
        Guard.ThrowIfNull(environment, "environment");

        return new ConnectionImpl(authenticationDetails, environment);
    }
}
