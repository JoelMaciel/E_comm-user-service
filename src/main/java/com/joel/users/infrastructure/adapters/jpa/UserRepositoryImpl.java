package com.joel.users.infrastructure.adapters.jpa;

import com.joel.users.application.mapper.UserMapper;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    @Override
    public Pagination<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> jpaPage = jpaRepository.findAll(pageable);
        return new Pagination<>(
                jpaPage.getContent().stream()
                        .map(mapper::toDomainFromEntity)
                        .toList(),
                jpaPage.getNumber(),
                jpaPage.getSize(),
                jpaPage.getTotalElements()
        );
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public User update(UUID id, User user) {
        UserEntity existingEntity = jpaRepository.findById(id).get();
        mapper.updateEntityFromDomain(existingEntity, user);
        UserEntity saved = jpaRepository.save(existingEntity);
        return mapper.toDomainFromEntity(saved);
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toEntityFromDomain(user);
        UserEntity savedEntity = jpaRepository.save(userEntity);
        return mapper.toDomainFromEntity(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomainFromEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }
}