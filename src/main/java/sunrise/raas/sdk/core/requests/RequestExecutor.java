package sunrise.raas.sdk.core.requests;

import sunrise.raas.sdk.core.connection.Connection;
import sunrise.raas.sdk.core.requests.results.Result;
import sunrise.raas.sdk.core.requests.results.SimpleResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface RequestExecutor<TRequest extends Request> {
    RequestType getApplicableType();

    <TResult> Result<TResult> executeRequest(Connection connection, TRequest request, Class<TResult> resultClass)
            throws ExecutionException, InterruptedException;

    <TResult> CompletableFuture<Result<TResult>> executeRequestAsync(Connection connection, TRequest request,
                                                                     Class<TResult> resultClass);

    SimpleResult executeCommand(Connection connection, TRequest request)
            throws ExecutionException, InterruptedException;

    CompletableFuture<SimpleResult> executeCommandAsync(Connection connection, TRequest request);

}
