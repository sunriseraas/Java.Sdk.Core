package sunrise.raas.sdk.core.dependencyinjection;

import sunrise.raas.sdk.core.application.ApplicationFactory;
import sunrise.raas.sdk.core.application.ExecutorApplicationFactory;
import sunrise.raas.sdk.core.authentication.AuthenticationDetailsFactory;
import sunrise.raas.sdk.core.authentication.AuthenticationDetailsFactoryImpl;
import sunrise.raas.sdk.core.connection.ConnectionFactory;
import sunrise.raas.sdk.core.connection.ConnectionFactoryImpl;
import sunrise.raas.sdk.core.requests.RequestExecutor;
import sunrise.raas.sdk.core.requests.rest.RestRequest;
import sunrise.raas.sdk.core.requests.rest.RestRequestExecutor;
import sunrise.raas.sdk.core.serialization.JacksonJsonSerializer;
import sunrise.raas.sdk.core.serialization.JsonSerializer;

import java.util.Set;

public final class ServiceLocator {
    private ServiceLocator() {
    }

    public static ConnectionFactory GetConnectionFactory() {
        return new ConnectionFactoryImpl();
    }

    public static AuthenticationDetailsFactory GetAuthenticationDetailsFactory() {
        return new AuthenticationDetailsFactoryImpl();
    }

    public static ApplicationFactory GetApplicationFactory() {
        var requestExecutors = GetAllRequestExecutors();

        return new ExecutorApplicationFactory(requestExecutors);
    }

    private static Iterable<RequestExecutor> GetAllRequestExecutors()
    {
        var restRequestExecutor = GetRestRequestExecutor();

        return Set.of(restRequestExecutor);
    }

    private static RequestExecutor<RestRequest> GetRestRequestExecutor()
    {
        var jsonSerializer = GetJsonSerializer();

        return new RestRequestExecutor(jsonSerializer);
    }

    private static JsonSerializer GetJsonSerializer()
    {
        return new JacksonJsonSerializer();
    }
}
