package sunrise.raas.sdk.core.requests.rest;

import java.util.Map;

public final class DeleteRequest extends AbstractRestRequest {
    public DeleteRequest(String route, boolean requiresSerialization, Object content, Map<String, String> headers) {
        super(HttpVerb.Delete, route, requiresSerialization, content, headers);
    }
}
