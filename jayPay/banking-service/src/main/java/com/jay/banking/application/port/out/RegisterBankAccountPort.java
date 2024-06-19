package com.jay.banking.application.port.out;


import com.jay.banking.adapter.out.persistence.RegisteredBankAccountEntity;
import com.jay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccountEntity createBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
