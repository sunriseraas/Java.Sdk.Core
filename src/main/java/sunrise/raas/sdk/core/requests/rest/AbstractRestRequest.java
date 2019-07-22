package sunrise.raas.sdk.core.requests.rest;

import sunrise.raas.sdk.core.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestRequest implements RestRequest {
    private final HttpVerb httpVerb;
    private final String route;
    private final boolean requiresSerialization;
    private final Object content;
    private final Map<String, String> headers;

    AbstractRestRequest(HttpVerb httpVerb, String route, boolean requiresSerialization,
                        Object content, Map<String, String> headers) {

        this.httpVerb = httpVerb;
        this.route = route;
        this.requiresSerialization = requiresSerialization;
        this.content = content;
        this.headers = headers == null ? new HashMap<>() : headers;
    }

    @Override
    public final RequestType getRequestType() {
        return RequestType.Rest;
    }

    @Override
    public final HttpVerb getHttpVerb() {
        return httpVerb;
    }

    @Override
    public final String getRoute() {
        return route;
    }

    @Override
    public final Object getContent() {
        return content;
    }

    @Override
    public final boolean requiresSerialization() {
        return requiresSerialization;
    }

    @Override
    public final Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void close() throws Exception {
        if (content instanceof AutoCloseable) {
            ((AutoCloseable) content).close();
        }
    }
}
