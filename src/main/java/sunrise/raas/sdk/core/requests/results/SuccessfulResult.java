package sunrise.raas.sdk.core.requests.results;

public final class SuccessfulResult<T> extends SimpleSuccessfulResult implements Result<T> {
    private final T value;

    public SuccessfulResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

