package com.joel.users.application.adapters.impl;

import com.joel.users.application.ports.usecases.users.ListUserUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListUserUseCaseImpl implements ListUserUseCase {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public Pagination<User> findAll(int page, int size) {
        return userRepository.findAll(page, size);
    }
}
