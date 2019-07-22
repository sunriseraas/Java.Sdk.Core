package sunrise.raas.sdk.core.requests.results;

public final class FailureResult<T> extends SimpleFailureResult implements Result<T> {
    public FailureResult(String reason) {
        super(reason);
    }
}
