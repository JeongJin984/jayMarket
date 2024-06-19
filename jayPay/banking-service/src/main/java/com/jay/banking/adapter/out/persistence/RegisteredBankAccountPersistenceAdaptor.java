package com.jay.banking.adapter.out.persistence;

import com.jay.banking.application.port.out.RegisterBankAccountPort;
import com.jay.banking.domain.RegisteredBankAccount;
import com.jay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdaptor implements RegisterBankAccountPort {

    private final RegisteredBankAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountEntity createBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return registeredBankAccountRepository.save(new RegisteredBankAccountEntity(
                membershipId.membershipId(),
                bankName.bankName(),
                bankAccountNumber.bankAccountNumber(),
                linkedStatusIsValid.linkedStatusIsValid()
        )) ;
    }
}
