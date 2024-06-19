package com.jay.banking.adapter.out.persistence;

import com.jay.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountEntity account) {
        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.RegisteredBankAccountId(account.getRegisteredBankAccountId().toString()),
                new RegisteredBankAccount.MembershipId(account.getMembershipId()),
                new RegisteredBankAccount.BankName(account.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(account.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(account.getLinkedStatusIsValid())
        );
    }
}
