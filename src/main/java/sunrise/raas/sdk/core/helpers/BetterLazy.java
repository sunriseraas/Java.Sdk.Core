package sunrise.raas.sdk.core.helpers;

import java.util.function.Supplier;

public final class BetterLazy<T> {
    private Supplier<T> getter;

    public BetterLazy(Supplier<T> getter) {
        this.getter = () -> {
            final var value = getter.get();

            this.getter = () -> value;

            return value;
        };
    }

    public T get() {
        return getter.get();
    }
}
