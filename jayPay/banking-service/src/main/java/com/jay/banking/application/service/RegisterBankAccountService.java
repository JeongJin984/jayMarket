 package com.jay.banking.application.service;

import com.jay.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.jay.banking.adapter.out.external.BankAccount;
import com.jay.banking.adapter.out.external.BankAccountRequest;
import com.jay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.jay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.jay.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.jay.banking.application.port.in.RegisterBankAccountCommand;
import com.jay.banking.application.port.in.RegisterBankAccountUseCase;
import com.jay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.jay.banking.application.port.out.RegisterBankAccountPort;
import com.jay.banking.application.port.out.RequestBankAccountInfoPort;
import com.jay.banking.domain.RegisteredBankAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class RegisterBankAccountService implements RegisterBankAccountUseCase, GetRegisteredBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final CommandGateway commandGateway;

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

    @Override
    public void registerBankAccountByEvent(RegisterBankAccountCommand command) {
        commandGateway.send(new CreateRegisteredBankAccountEvent(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber()
        )).whenComplete((result, error) -> {
            if (error != null) {
                log.error("Error occurred when registering bank account {}", error.getMessage());
            } else {
                registeredBankAccountMapper.mapToDomainEntity(registerBankAccountPort.createBankAccount(
                        new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                        new RegisteredBankAccount.BankName(command.getBankName()),
                        new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                        new RegisteredBankAccount.LinkedStatusIsValid(command.isValid())
                ));
            }
        });
    }

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return registeredBankAccountMapper.mapToDomainEntity(getRegisteredBankAccountPort.getRegisteredBankAccount(command));
    }
}
