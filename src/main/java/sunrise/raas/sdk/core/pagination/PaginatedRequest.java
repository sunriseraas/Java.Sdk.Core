package sunrise.raas.sdk.core.pagination;

import sunrise.raas.sdk.core.helpers.Equatable;

public interface PaginatedRequest<T> extends Equatable<PaginatedRequest<T>> {
    int getPageNumber();

    int getCountPerPage();
}
