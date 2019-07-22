package sunrise.raas.sdk.core.connection;

import sunrise.raas.sdk.core.authentication.AuthenticationDetails;
import sunrise.raas.sdk.core.configuration.Environment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

final class ConnectionImpl implements Connection {
    private final AuthenticationDetails authenticationDetails;
    private final Environment environment;

    ConnectionImpl(AuthenticationDetails authenticationDetails, Environment environment) {
        this.authenticationDetails = authenticationDetails;
        this.environment = environment;
    }

    @Override
    public AuthenticationDetails getAuthenticationDetails() {
        return authenticationDetails;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void connect() throws ExecutionException, InterruptedException {
        connectAsync().get();
    }

    @Override
    public CompletableFuture<Void> connectAsync() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void disconnect() throws ExecutionException, InterruptedException {
        disconnectAsync().get();
    }

    @Override
    public CompletableFuture<Void> disconnectAsync() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void close() throws Exception {
        disconnect();
    }
}
