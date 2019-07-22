package sunrise.raas.sdk.core.connection;

import sunrise.raas.sdk.core.authentication.AuthenticationDetails;
import sunrise.raas.sdk.core.configuration.Environment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

public interface Connection extends AutoCloseable {
    AuthenticationDetails getAuthenticationDetails();

    Environment getEnvironment();

    void connect() throws ExecutionException, InterruptedException;

    CompletableFuture<Void> connectAsync();

    void disconnect() throws ExecutionException, InterruptedException;

    CompletableFuture<Void> disconnectAsync();
}
