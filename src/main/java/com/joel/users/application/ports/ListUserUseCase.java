package com.joel.users.application.ports;

import com.joel.users.application.dtos.response.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListUserUseCase {

    Page<UserDTO> findAll(Pageable pageable);
}
