package com.joel.users.domain.repositories;

import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findById(UUID id);

    User save(User user);

    Pagination<User> findAll(int page, int size);

    User update(UUID id, User user);

    void deleteById(UUID id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
