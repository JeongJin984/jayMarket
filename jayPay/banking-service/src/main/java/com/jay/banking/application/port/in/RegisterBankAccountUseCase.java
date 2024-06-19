package com.jay.banking.application.port.in;

import com.jay.banking.domain.RegisteredBankAccount;
import com.jay.common.UseCase;

@UseCase
public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
