package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.commands.EmployeeCommand;

public interface AssignEmployeeRoleUseCase {

    void execute(EmployeeCommand employeeCommand);
}
