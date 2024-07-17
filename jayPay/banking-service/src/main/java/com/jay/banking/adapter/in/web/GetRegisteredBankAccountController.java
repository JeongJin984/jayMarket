package com.jay.banking.adapter.in.web;

import com.jay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.jay.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.jay.banking.domain.RegisteredBankAccount;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetRegisteredBankAccountController {

    private final GetRegisteredBankAccountUseCase getRegisteredBankAccountUseCase;

    @GetMapping("/banking/account/{membershipId}")
    RegisteredBankAccount getRegisteredbankAccount(@PathVariable String membershipId) {
        GetRegisteredBankAccountCommand command = GetRegisteredBankAccountCommand.builder()
                .membershipId(membershipId)
                .build();

        return getRegisteredBankAccountUseCase.getRegisteredBankAccount(command);
    }
}
