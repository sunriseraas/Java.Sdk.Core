package sunrise.raas.sdk.core.requests.results;

public abstract class AbstractSimpleResult implements SimpleResult {
    private final boolean wasSuccessful;

    protected AbstractSimpleResult(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    @Override
    public final boolean wasSuccessful() {
        return wasSuccessful;
    }
}
