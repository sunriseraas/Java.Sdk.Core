package sunrise.raas.sdk.core.requests;

import sunrise.raas.sdk.core.connection.Connection;
import sunrise.raas.sdk.core.requests.results.Result;
import sunrise.raas.sdk.core.requests.results.SimpleResult;

import java.util.concurrent.ExecutionException;

public abstract class AbstractRequestExecutor<TRequest extends Request> implements RequestExecutor<TRequest> {
    private final RequestType applicableType;

    protected AbstractRequestExecutor(RequestType applicableType) {
        this.applicableType = applicableType;
    }

    @Override
    public final RequestType getApplicableType() {
        return applicableType;
    }

    @Override
    public <TResult> Result<TResult> executeRequest(Connection connection, TRequest request, Class<TResult> resultClass)
            throws ExecutionException, InterruptedException {
        return executeRequestAsync(connection, request, resultClass).get();
    }

    @Override
    public SimpleResult executeCommand(Connection connection, TRequest request)
            throws ExecutionException, InterruptedException {
        return executeCommandAsync(connection, request).get();
    }
}
