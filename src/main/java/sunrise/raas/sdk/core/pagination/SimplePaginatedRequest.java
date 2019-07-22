package sunrise.raas.sdk.core.pagination;

public final class SimplePaginatedRequest<T> implements PaginatedRequest<T> {
    private final int pageNumber;
    private final int countPerPage;

    public SimplePaginatedRequest(int pageNumber, int countPerPage) {
        this.pageNumber = pageNumber;
        this.countPerPage = countPerPage;
    }

    @Override
    public final int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getCountPerPage() {
        return countPerPage;
    }

    @Override
    public boolean isEqualTo(PaginatedRequest<T> other) {
        return other instanceof SimplePaginatedRequest &&
                isEqualTo((SimplePaginatedRequest<T>) other);
    }

    @Override
    public boolean equals(Object other) {
        //noinspection unchecked
        return other instanceof PaginatedRequest &&
                isEqualTo((PaginatedRequest<T>) other);
    }

    private boolean isEqualTo(SimplePaginatedRequest<T> other) {
        return other != null &&
                pageNumber == other.pageNumber &&
                countPerPage == other.countPerPage;
    }
}
