package com.jay.banking.adapter.axon.aggregate;

import com.jay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.jay.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.jay.banking.adapter.out.external.BankAccount;
import com.jay.banking.adapter.out.external.BankAccountRequest;
import com.jay.banking.application.port.out.RequestBankAccountInfoPort;
import com.jay.common.event.CheckRegisteredBankAccountCommand;
import com.jay.common.event.CheckedRegisteredBankAccountEvent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RegisteredBankAccountAggregate {
    @AggregateIdentifier
    private String id;

    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {
        apply(new CreateRegisteredBankAccountEvent(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber()
        ));
    }

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event) {
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankName = event.getBankName();
        bankAccountNumber = event.getBankAccountNumber();
    }

    @CommandHandler
    public void handle(@NotNull CheckRegisteredBankAccountCommand command, RequestBankAccountInfoPort bankAccountInfoPort) {
        id = command.getAggregateIdentifier();
        log.info("Check registered bank account {}", id);

        BankAccount account = bankAccountInfoPort.getBankAccountInfo(new BankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean valid = account.isValid();

        String firmbankingUUID = UUID.randomUUID().toString();

        apply(new CheckedRegisteredBankAccountEvent(
                command.getRechargeRequestId(),
                command.getCheckRegisteredBankAccountId(),
                command.getMembershipId(),
                valid,
                command.getAmount(),
                firmbankingUUID,
                account.bankName(),
                account.bankAccountNumber()
        ));
    }
}
