package com.jay.banking.adapter.in.web;

import com.jay.banking.application.port.in.RegisterBankAccountCommand;
import com.jay.banking.application.port.in.RegisterBankAccountUseCase;
import com.jay.banking.domain.RegisteredBankAccount;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RegisterBankAccountController {
    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping("/banking/account/register")
    RegisteredBankAccount registeredBankAccount(
            @RequestBody final RegisterBankAccountRequest body
    ) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(body.membershipId())
                .bankName(body.bankName())
                .bankAccountNumber(body.bankAccountNumber())
                .isValid(body.isValid())
                .build();

        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccount(command);
        if(registeredBankAccount == null) {
            // ToDo: ERROR HANDLING
            return null;
        } else {
            return registeredBankAccount;
        }
    }
}
