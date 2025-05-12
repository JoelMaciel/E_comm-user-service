package com.joel.users.domain.usecases.impl;

import com.joel.users.domain.dtos.response.UserDTO;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.mapper.UserMapper;
import com.joel.users.domain.repositories.UserRepository;
import com.joel.users.domain.usecases.ListUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ListUserUseCaseImpl implements ListUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public Page<UserDTO> findAll(Pageable pageable) {

        Page<User> usersPage = userRepository.findAll(pageable);
        return mapper.toDTOPage(usersPage);
    }
}
