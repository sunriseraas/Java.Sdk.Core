package sunrise.raas.sdk.core.requests.rest;

import java.util.Map;

public final class PostRequest extends AbstractRestRequestWithBody {
    public PostRequest(String route, boolean requiresSerialization, Object content, Map<String, String> headers) {
        super(HttpVerb.Post, route, requiresSerialization, content, headers);
    }
}
