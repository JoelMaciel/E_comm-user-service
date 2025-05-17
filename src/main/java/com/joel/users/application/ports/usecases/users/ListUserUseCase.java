package com.joel.users.application.ports.usecases.users;

import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;

public interface ListUserUseCase {

    Pagination<User> findAll(int page, int size);
}
