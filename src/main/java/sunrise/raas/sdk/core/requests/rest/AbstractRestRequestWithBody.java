package sunrise.raas.sdk.core.requests.rest;

import java.util.Map;

public abstract class AbstractRestRequestWithBody extends AbstractRestRequest implements RestRequestWithBody {
    private final boolean requiresSerialization;
    private final Object content;

    AbstractRestRequestWithBody(HttpVerb httpVerb, String route, boolean requiresSerialization,
                        Object content, Map<String, String> headers) {
        super(httpVerb, route, headers);

        this.requiresSerialization = requiresSerialization;
        this.content = content;
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
    public final void close() throws Exception {
        if (content instanceof AutoCloseable) {
            ((AutoCloseable) content).close();
        }
    }
}
