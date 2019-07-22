package sunrise.raas.sdk.core.requests.rest;

import sunrise.raas.sdk.core.authentication.ApiKeyAuthenticationDetails;
import sunrise.raas.sdk.core.authentication.AuthenticationDetails;
import sunrise.raas.sdk.core.connection.Connection;
import sunrise.raas.sdk.core.requests.AbstractRequestExecutor;
import sunrise.raas.sdk.core.requests.RequestType;
import sunrise.raas.sdk.core.requests.results.*;
import sunrise.raas.sdk.core.serialization.JsonSerializer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public final class RestRequestExecutor extends AbstractRequestExecutor<RestRequest> {
    private final JsonSerializer jsonSerializer;

    public RestRequestExecutor(JsonSerializer jsonSerializer) {
        super(RequestType.Rest);

        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public <TResult> CompletableFuture<Result<TResult>> executeRequestAsync(Connection connection, RestRequest request,
                                                                            Class<TResult> resultClass) {
        return sendRequest(connection, request,
                failureResult -> new FailureResult<>(failureResult.getReason()),
                successfulResult -> {
                    var httpURLConnection = successfulResult.getValue();

                    try {
                        var value = extract(httpURLConnection, resultClass);

                        return new SuccessfulResult<>(value);
                    } catch (Exception exception) {
                        return createFailureResult(exception.getMessage());
                    }
                });
    }

    @Override
    public CompletableFuture<SimpleResult> executeCommandAsync(Connection connection, RestRequest request) {
        return sendRequest(connection, request,
                failureResult -> new SimpleFailureResult(failureResult.getReason()),
                successfulResult -> new SimpleSuccessfulResult());
    }

    private <T> T extract(HttpURLConnection httpURLConnection, Class<T> resultClass) throws Exception {
        if (httpURLConnection.getContentLength() <= 0) {
            return null;
        }

        var content = httpURLConnection.getContent();

        if (httpURLConnection.getContentType().toLowerCase().contains("application/json")) {
            return jsonSerializer.deserialize(content.toString(), resultClass);
        }

        if (resultClass.isInstance(content)) {
            return resultClass.cast(content);
        }

        return null;
    }

    private <TResult extends SimpleResult> CompletableFuture<TResult> sendRequest(Connection connection,
                                                                                  RestRequest restRequest,
                                                                                  Function<FailureResult<HttpURLConnection>, TResult> onFailure,
                                                                                  Function<SuccessfulResult<HttpURLConnection>, TResult> onSuccess) {
        return CompletableFuture
                .supplyAsync(() -> {
                    String failureReason;

                    try {
                        var urlConnection = createHttpUrlConnection(connection, restRequest);

                        var responseCode = urlConnection.getResponseCode();

                        if (200 <= responseCode && responseCode < 299) {
                            return onSuccess.apply(new SuccessfulResult<>(urlConnection));
                        }

                        urlConnection.disconnect();

                        failureReason = urlConnection.getResponseMessage();
                    } catch (Exception exception) {
                        failureReason = exception.getMessage();
                    }

                    return onFailure.apply(createFailureResult(failureReason));
                });
    }

    private <T> FailureResult<T> createFailureResult(String reason) {
        return new FailureResult<>(String.format("Failed to execute request: %s", reason));
    }

    private HttpURLConnection createHttpUrlConnection(Connection connection, RestRequest restRequest)
            throws Exception {
        var httpVerb = restRequest.getHttpVerb();
        var requestMethod = getRequestMethod(httpVerb);
        var baseUrl = connection.getEnvironment().getBaseUrl();
        var route = restRequest.getRoute();
        var authenticationDetails = connection.getAuthenticationDetails();
        var authorizationHeaderValue = getAuthorizationHeaderValue(authenticationDetails);
        var url = new URL(String.format("%s%s", baseUrl, route));
        var urlConnection = (HttpURLConnection) url.openConnection();

        restRequest.getHeaders().forEach(urlConnection::setRequestProperty);

        urlConnection.setAllowUserInteraction(false);
        urlConnection.setRequestMethod(requestMethod);
        urlConnection.setRequestProperty("Authorization", authorizationHeaderValue);

        if (restRequest instanceof RestRequestWithBody) {
            setContent((RestRequestWithBody) restRequest, urlConnection);
        }

        return urlConnection;
    }

    private void setContent(RestRequestWithBody restRequest, HttpURLConnection urlConnection) throws Exception {
        urlConnection.setDoOutput(true);

        var outputStream = urlConnection.getOutputStream();
        var content = restRequest.getContent();
        byte[] bytes;

        if (restRequest.requiresSerialization()) {
            var json = jsonSerializer.serialize(content);
            bytes = json.getBytes();
        } else if (content instanceof byte[]) {
            bytes = (byte[]) content;
        } else {
            bytes = content.toString().getBytes();
        }

        outputStream.write(bytes);
    }

    private String getAuthorizationHeaderValue(AuthenticationDetails authenticationDetails) {
        if (authenticationDetails instanceof ApiKeyAuthenticationDetails) {
            var apiKey = ((ApiKeyAuthenticationDetails) authenticationDetails).getApiKey();

            return String.format("Bearer %s", apiKey);
        }

        throw new IllegalArgumentException("Invalid authentication details type");
    }

    private String getRequestMethod(HttpVerb httpVerb) {
        switch (httpVerb) {
            case Get:
                return "GET";
            case Put:
                return "PUT";
            case Post:
                return "POST";
            case Delete:
                return "DELETE";
            default:
                throw new IllegalArgumentException("Invalid HttpVerb");
        }
    }
}
