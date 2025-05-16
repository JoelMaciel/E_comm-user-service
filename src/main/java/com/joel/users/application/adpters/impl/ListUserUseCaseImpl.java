package com.joel.users.application.adpters.impl;

import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.ListUserUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.infrastructure.adapters.jpa.UserEntity;
import com.joel.users.infrastructure.adapters.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListUserUseCaseImpl implements ListUserUseCase {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<UserEntity> users = userJpaRepository.findAll(pageable);
        Page<User> domainPage = users.map(mapper::toDomainFromEntity);
        return mapper.toDtoPage(domainPage);
    }
}
