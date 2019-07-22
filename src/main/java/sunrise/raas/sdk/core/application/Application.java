package sunrise.raas.sdk.core.application;

import sunrise.raas.sdk.core.connection.Connection;
import sunrise.raas.sdk.core.requests.results.Result;
import sunrise.raas.sdk.core.requests.Request;
import sunrise.raas.sdk.core.requests.results.SimpleResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;


public interface Application extends AutoCloseable {
    Connection getConnection();

    <TRequest extends Request, TResult> Result<TResult> executeRequest(TRequest request, Class<TResult> resultClass)
            throws ExecutionException, InterruptedException;

    <TRequest extends Request, TResult> CompletableFuture<Result<TResult>> executeRequestAsync(TRequest request,
                                                                                               Class<TResult> resultClass);

    <TRequest extends Request> SimpleResult executeCommand(TRequest request) throws ExecutionException, InterruptedException;

    <TRequest extends Request> CompletableFuture<SimpleResult> executeCommandAsync(TRequest request);
}