package sunrise.raas.sdk.core.requests.results;

public class SimpleFailureResult extends AbstractSimpleResult {
    private final String reason;

    public SimpleFailureResult(String reason) {
        super(false);

        this.reason = reason;
    }

    public final String getReason() {
        return reason;
    }
}

