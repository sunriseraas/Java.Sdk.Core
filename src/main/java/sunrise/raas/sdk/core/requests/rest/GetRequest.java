package sunrise.raas.sdk.core.requests.rest;

import java.util.Map;

public final class GetRequest extends AbstractRestRequest {
    public GetRequest(String route, Map<String, String> headers) {
        super(HttpVerb.Delete, route, headers);
    }
}
