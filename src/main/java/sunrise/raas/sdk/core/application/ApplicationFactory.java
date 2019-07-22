package sunrise.raas.sdk.core.application;

import sunrise.raas.sdk.core.connection.Connection;

public interface ApplicationFactory {
    Application create(Connection connection);
}
