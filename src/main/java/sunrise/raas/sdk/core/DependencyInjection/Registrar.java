package sunrise.raas.sdk.core.DependencyInjection;

import sunrise.raas.sdk.core.application.ApplicationFactory;
import sunrise.raas.sdk.core.application.ExecutorApplicationFactory;
import sunrise.raas.sdk.core.authentication.AuthenticationDetailsFactory;
import sunrise.raas.sdk.core.authentication.AuthenticationDetailsFactoryImpl;
import sunrise.raas.sdk.core.connection.ConnectionFactory;
import sunrise.raas.sdk.core.connection.ConnectionFactoryImpl;
import sunrise.raas.sdk.core.helpers.Guard;
import sunrise.raas.sdk.core.requests.RequestExecutor;
import sunrise.raas.sdk.core.requests.rest.RestRequestExecutor;
import sunrise.raas.sdk.core.serialization.JacksonJsonSerializer;
import sunrise.raas.sdk.core.serialization.JsonSerializer;

public final class Registrar {
    private Registrar() {
    }

    public static <TRegistrationContext extends RegistrationContext<TRegistrationContext>> TRegistrationContext registerServices(TRegistrationContext registrationContext) {
        Guard.ThrowIfNull(registrationContext, "registrationContext");

        return registrationContext
                .register(AuthenticationDetailsFactory.class, AuthenticationDetailsFactoryImpl.class)
                .register(ConnectionFactory.class, ConnectionFactoryImpl.class)
                .register(JsonSerializer.class, JacksonJsonSerializer.class)
                .register(RequestExecutor.class, RestRequestExecutor.class)
                .register(ApplicationFactory.class, ExecutorApplicationFactory.class);
    }
}
