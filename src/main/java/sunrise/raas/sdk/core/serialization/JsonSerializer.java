package sunrise.raas.sdk.core.serialization;

public interface JsonSerializer {
    String serialize(Object value) throws Exception;

    <T> T deserialize(String serializedValue, Class<T> resultClass) throws Exception;
}
