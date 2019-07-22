package sunrise.raas.sdk.core.requests.rest;

import sunrise.raas.sdk.core.requests.Request;

import java.util.Map;

public interface RestRequest extends Request {
    HttpVerb getHttpVerb();
    String getRoute();
    Object getContent();
    boolean requiresSerialization();
    Map<String, String> getHeaders();
}

