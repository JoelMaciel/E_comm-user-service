package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.commands.UpdatePasswordCommand;

public interface UpdatePasswordUseCase {
    void execute(UpdatePasswordCommand updatePasswordCommand);
}
