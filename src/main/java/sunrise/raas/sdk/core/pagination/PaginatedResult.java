package sunrise.raas.sdk.core.pagination;

public interface PaginatedResult<T> {
    PaginatedRequest<T> getRequest();

    Iterable<T> getResults();
}
