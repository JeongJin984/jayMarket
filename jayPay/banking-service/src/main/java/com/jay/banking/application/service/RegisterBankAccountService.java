 package com.jay.banking.application.service;

import com.jay.banking.adapter.out.external.BankAccount;
import com.jay.banking.adapter.out.external.BankAccountRequest;
import com.jay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.jay.banking.application.port.in.RegisterBankAccountCommand;
import com.jay.banking.application.port.in.RegisterBankAccountUseCase;
import com.jay.banking.application.port.out.RegisterBankAccountPort;
import com.jay.banking.application.port.out.RequestBankAccountInfoPort;
import com.jay.banking.domain.RegisteredBankAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class RegisterBankAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        BankAccount bankAccount = requestBankAccountInfoPort.getBankAccountInfo(new BankAccountRequest(command.getBankName(), command.getBankAccountNumber()));

        if(bankAccount.isValid()) {
            return registeredBankAccountMapper.mapToDomainEntity(registerBankAccountPort.createBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(bankAccount.bankName()),
                    new RegisteredBankAccount.BankAccountNumber(bankAccount.bankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(bankAccount.isValid())
            ));
        } else {
            return null;
        }
    }
}
