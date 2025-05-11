package com.joel.users.domain.usecases;

import com.joel.users.domain.dtos.response.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListUserUseCase {

    Page<UserDTO> findAll(Pageable pageable);
}
