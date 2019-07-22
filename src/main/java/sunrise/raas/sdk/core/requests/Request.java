package sunrise.raas.sdk.core.requests;

public interface Request extends AutoCloseable {
    RequestType getRequestType();
}
