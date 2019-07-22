package sunrise.raas.sdk.core.requests.rest;

import java.util.Map;

public final class PutRequest extends AbstractRestRequest {
    public PutRequest(String route, boolean requiresSerialization, Object content, Map<String, String> headers) {
        super(HttpVerb.Put, route, requiresSerialization, content, headers);
    }
}
