package com.jay.banking.adapter.out.persistence;

import com.jay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.jay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.jay.banking.application.port.out.RegisterBankAccountPort;
import com.jay.banking.domain.RegisteredBankAccount;
import com.jay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdaptor implements RegisterBankAccountPort, GetRegisteredBankAccountPort {

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

    @Override
    public RegisteredBankAccountEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return registeredBankAccountRepository.findByMembershipId(command.getMembershipId()).orElseThrow(
                () -> new NoSuchElementException("No Such bank account " + command.getMembershipId())
        );
    }
}
