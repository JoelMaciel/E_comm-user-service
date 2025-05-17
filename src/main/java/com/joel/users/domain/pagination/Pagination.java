package com.joel.users.domain.pagination;

import java.util.List;

public record Pagination<T>(
        List<T> content,
        int pageNumber,
        int size,
        long totalElements
) {
}
