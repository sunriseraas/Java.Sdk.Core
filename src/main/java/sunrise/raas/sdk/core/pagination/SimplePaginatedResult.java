package sunrise.raas.sdk.core.pagination;

import sunrise.raas.sdk.core.helpers.BetterLazy;
import sunrise.raas.sdk.core.helpers.Guard;
import sunrise.raas.sdk.core.helpers.StreamUtilities;

import java.util.List;
import java.util.stream.Collectors;

public final class SimplePaginatedResult<T> implements PaginatedResult<T> {
    private final PaginatedRequest<T> request;
    private final BetterLazy<List<T>> results;

    public SimplePaginatedResult(final PaginatedRequest<T> request, final Iterable<T> results) {
        Guard.ThrowIfNull(request, "request");

        this.request = request;

        this.results = new BetterLazy<>(() -> StreamUtilities
                .from(results == null ? List.of() : results)
                .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public PaginatedRequest<T> getRequest() {
        return request;
    }

    @Override
    public Iterable<T> getResults() {
        return results.get();
    }
}
