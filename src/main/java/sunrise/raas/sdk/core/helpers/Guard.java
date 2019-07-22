package sunrise.raas.sdk.core.helpers;

public final class Guard {
    private Guard() {
    }

    public static <T> void ThrowIfNull(T value, String parameterName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s can not be null", parameterName));
        }
    }

    public static void ThrowIfNullOrWhitespace(String value, String parameterName) {
        ThrowIfNull(value, parameterName);

        if (value.isBlank()) {
            throw new IllegalArgumentException(String.format("%s can not be whitespace", parameterName));
        }
    }
}
