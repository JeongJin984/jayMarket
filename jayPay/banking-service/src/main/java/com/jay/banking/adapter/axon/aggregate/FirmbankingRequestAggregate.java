package com.jay.banking.adapter.axon.aggregate;

import com.jay.banking.adapter.axon.command.FirmbankingRequestAxonCommand;
import com.jay.banking.adapter.axon.command.UpdateFirmbankingStatusAxonCommand;
import com.jay.banking.adapter.axon.event.FirmbankingRequestEvent;
import com.jay.banking.adapter.axon.event.UpdateFirmbankingStatusEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class FirmbankingRequestAggregate {
    @AggregateIdentifier
    private String id;

    private String fromBankName;
    private String fromAccountNumber;

    private String toBankName;
    private String toBankAccountNumber;

    private BigDecimal moneyAmount;
    private int firmbankingStatus;

    @CommandHandler
    public FirmbankingRequestAggregate(FirmbankingRequestAxonCommand command) {
        apply(new FirmbankingRequestEvent(
                command.getFromBankName(),
                command.getFromAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                command.getFirmbankingStatus()
        ));
    }

    @EventSourcingHandler
    public void on(FirmbankingRequestEvent event) {
        id = UUID.randomUUID().toString();
        fromBankName = event. getFromBankName();
        fromAccountNumber = event. getFromAccountNumber();
        toBankName = event. getToBankName();
        toBankAccountNumber = event. getToBankAccountNumber();
        moneyAmount = event. getMoneyAmount();
        firmbankingStatus = event. getFirmbankingStatus();
    }

    @CommandHandler
    public String handle(UpdateFirmbankingStatusAxonCommand command) {
        id = command.getAggregateIdentifier();
        apply(new UpdateFirmbankingStatusEvent(command.getFirmbankingStatus()));
        return id;
    }

    @EventSourcingHandler
    public void on(UpdateFirmbankingStatusEvent event) {

    }
}
