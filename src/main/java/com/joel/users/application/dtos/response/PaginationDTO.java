package com.joel.users.application.dtos.response;

import java.util.List;

public record PaginationDTO<T>(
    List<T> content,
    int currentPage,
    int pageSize,
    long totalElements
) {}