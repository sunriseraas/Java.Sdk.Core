package sunrise.raas.sdk.core.requests.rest;

public interface RestRequestWithBody extends RestRequest {
    Object getContent();
    boolean requiresSerialization();
}
