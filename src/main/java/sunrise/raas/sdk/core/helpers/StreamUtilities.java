package sunrise.raas.sdk.core.helpers;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class StreamUtilities {
    private StreamUtilities() {
    }

    public static <T> Stream<T> from(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
