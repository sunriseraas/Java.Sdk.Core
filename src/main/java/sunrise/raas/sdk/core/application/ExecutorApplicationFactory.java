package sunrise.raas.sdk.core.application;

import sunrise.raas.sdk.core.connection.Connection;
import sunrise.raas.sdk.core.helpers.Guard;
import sunrise.raas.sdk.core.helpers.StreamUtilities;
import sunrise.raas.sdk.core.requests.RequestExecutor;
import sunrise.raas.sdk.core.requests.RequestType;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public final class ExecutorApplicationFactory implements ApplicationFactory {
    private final Map<RequestType, RequestExecutor> requestExecutorsByType;

    public ExecutorApplicationFactory(Iterable<RequestExecutor> requestExecutors) {
        var requestExecutorMap = StreamUtilities
                .from(requestExecutors)
                .collect(Collectors.toMap(RequestExecutor::getApplicableType, requestExecutor -> requestExecutor));

        requestExecutorsByType = Collections.unmodifiableMap(requestExecutorMap);
    }

    @Override
    public Application create(Connection connection) {
        Guard.ThrowIfNull(connection, "connection");

        return new ExecutorApplication(connection, requestExecutorsByType);
    }
}
