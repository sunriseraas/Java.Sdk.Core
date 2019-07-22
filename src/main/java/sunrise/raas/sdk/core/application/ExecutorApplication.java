package sunrise.raas.sdk.core.application;

import sunrise.raas.sdk.core.connection.Connection;
import sunrise.raas.sdk.core.helpers.Guard;
import sunrise.raas.sdk.core.requests.*;
import sunrise.raas.sdk.core.requests.results.Result;
import sunrise.raas.sdk.core.requests.results.SimpleResult;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

final class ExecutorApplication implements Application {
    private final Connection connection;
    private final Map<RequestType, RequestExecutor> requestExecutorsByType;

    ExecutorApplication(Connection connection, Map<RequestType, RequestExecutor> requestExecutorsByType) {
        this.connection = connection;
        this.requestExecutorsByType = Collections.unmodifiableMap(requestExecutorsByType);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public <TRequest extends Request, TResult> Result<TResult> executeRequest(TRequest request,
                                                                              Class<TResult> resultClass)
            throws ExecutionException, InterruptedException {
        return executeRequestAsync(request, resultClass).get();
    }

    @Override
    public <TRequest extends Request, TResult> CompletableFuture<Result<TResult>> executeRequestAsync(TRequest request,
                                                                                                      Class<TResult> resultClass) {
        return execute(request,
                requestExecutor -> requestExecutor.executeRequestAsync(connection, request, resultClass));
    }

    @Override
    public <TRequest extends Request> SimpleResult executeCommand(TRequest request) throws ExecutionException,
            InterruptedException {
        return executeCommandAsync(request).get();
    }

    @Override
    public <TRequest extends Request> CompletableFuture<SimpleResult> executeCommandAsync(TRequest request) {
        return execute(request, requestExecutor -> requestExecutor.executeCommandAsync(connection, request));
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private <TRequest extends Request, TResult extends SimpleResult> CompletableFuture<TResult> execute(TRequest request,
                                                                                                        Function<RequestExecutor<TRequest>, CompletableFuture<TResult>> executionFunction) {
        Guard.ThrowIfNull(request, "request");

        var requestType = request.getRequestType();

        if (!requestExecutorsByType.containsKey(requestType)) {
            throw new UnsupportedOperationException("Unsupported request type");
        }

        //noinspection unchecked
        RequestExecutor<TRequest> requestExecutor = requestExecutorsByType.get(requestType);

        return executionFunction.apply(requestExecutor)
                .thenApply(result -> {
                    try {
                        request.close();
                    } catch (Exception ignored) {
                        // Ignored
                    }

                    return result;
                });
    }
}
