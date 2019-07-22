package sunrise.raas.sdk.core.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JacksonJsonSerializer implements JsonSerializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String serialize(Object value) throws Exception {
        return objectMapper.writeValueAsString(value);
    }

    @Override
    public <T> T deserialize(String serializedValue, Class<T> resultClass) throws Exception {
        return objectMapper.readValue(serializedValue, resultClass);
    }
}
