package sunrise.raas.sdk.core.requests.rest;

import sunrise.raas.sdk.core.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestRequest implements RestRequest {
    private final HttpVerb httpVerb;
    private final String route;
    private final Map<String, String> headers;

    AbstractRestRequest(HttpVerb httpVerb, String route, Map<String, String> headers) {

        this.httpVerb = httpVerb;
        this.route = route;
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
    public final Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void close() throws Exception {
    }
}
