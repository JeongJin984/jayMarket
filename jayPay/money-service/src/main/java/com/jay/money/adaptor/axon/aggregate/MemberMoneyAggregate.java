package com.jay.money.adaptor.axon.aggregate;

import com.jay.common.event.RollbackFirmbankingFinishedEvent;
import com.jay.money.adaptor.axon.command.MemberMoneyCreatedAxonCommand;
import com.jay.money.adaptor.axon.command.MemberMoneyIncreasedAxonCommand;
import com.jay.money.adaptor.axon.command.RechargingRequestCreateCommand;
import com.jay.money.adaptor.axon.event.MemberMoneyCreatedEvent;
import com.jay.money.adaptor.axon.event.MemberMoneyIncreasedEvent;
import com.jay.money.adaptor.axon.event.RechargingRequestCreatedEvent;
import com.jay.money.application.port.out.GetMemberMoneyPort;
import com.jay.money.application.port.out.GetRegisteredBankAccountPort;
import com.jay.money.application.port.out.RegisterBankAccountAggregate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
@Slf4j
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private BigDecimal balance;

    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyCreatedAxonCommand command) {
        apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        balance = BigDecimal.ZERO;
    }

    @CommandHandler
    public void handle(MemberMoneyIncreasedAxonCommand command) {
        id = command.getAggregateIdentifier();
        apply(new MemberMoneyIncreasedEvent(command.getMembershipId(), command.getAmount(), id));
    }

    @EventSourcingHandler
    public void on(MemberMoneyIncreasedEvent event) {
        id = event.getAggregateIdentifier();
        membershipId = event.getMembershipId();
        balance = balance.add(event.getAmount());
    }

    @CommandHandler
    public void handle(RechargingRequestCreateCommand command, GetRegisteredBankAccountPort getRegisteredBankAccountPort) {
        id = command.getAggregateIdentifier();
        RegisterBankAccountAggregate aggregate = getRegisteredBankAccountPort.getRegisteredBankAccountAggregate(command.getMemberId());

        apply(new RechargingRequestCreatedEvent(
                command.getRechargingRequestId(),
                command.getMemberId(),
                command.getAmount(),
                aggregate.getAggregateIdentifier(),
                aggregate.getBankName(),
                aggregate.getBankAccountNumber()
        ));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmbankingId")
    public void handle(RollbackFirmbankingFinishedEvent event) {
        log.info("RollbackFirmbankingFinishedEvent saga: {}", event.toString());
    }
}
