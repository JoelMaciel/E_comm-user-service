package com.joel.users.application.adapters.impl;

import com.joel.users.application.commands.EmployeeCommand;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.AssignEmployeeRoleUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.exceptions.UserNotFoundException;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssignEmployeeRoleUseCaseImpl implements AssignEmployeeRoleUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    @Override
    public void execute(EmployeeCommand employeeCommand) {
        User user = userRepository.findById(employeeCommand.userId())
                .orElseThrow(UserNotFoundException::new);

        User userEmployee = mapper.toUpdateEmployeeDomainFromCommand(user);
        userRepository.save(userEmployee);
    }
}
