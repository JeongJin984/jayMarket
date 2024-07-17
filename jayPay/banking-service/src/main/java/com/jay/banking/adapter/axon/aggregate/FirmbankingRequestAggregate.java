package com.jay.banking.adapter.axon.aggregate;

import com.jay.banking.adapter.axon.command.FirmbankingRequestAxonCommand;
import com.jay.banking.adapter.axon.command.UpdateFirmbankingStatusAxonCommand;
import com.jay.banking.adapter.axon.event.FirmbankingRequestEvent;
import com.jay.banking.adapter.axon.event.UpdateFirmbankingStatusEvent;
import com.jay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.jay.banking.adapter.out.external.bank.FirmbankingResult;
import com.jay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.jay.banking.application.port.out.RequestFirmbankingPort;
import com.jay.banking.domain.FirmbankingRequest;
import com.jay.common.command.RequestFirmbankingCommand;
import com.jay.common.event.RequestFirmbankingFinishedEvent;
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
        System.out.println("UpdateRequestFirmbankingEvent Sourcing Handler");
        firmbankingStatus = event.getFirmbankingStatus();
    }

    @CommandHandler
    public FirmbankingRequestAggregate(RequestFirmbankingCommand command, RequestFirmbankingPort firmbankingPort, RequestExternalFirmbankingPort externalFirmbankingPort){
        System.out.println("RequestFirmbankingCommand Handler");
        id = command.getAggregateIdentifier();

        firmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getToBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.ToBankName("fastcampus-bank"),
                new FirmbankingRequest.ToBankAccountNumber("123-333-9999"),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                FirmbankingRequest.FirmbankingRequestStatus.REQUEST,
                new FirmbankingRequest.AggregateIdentifier(id));

        // firmbanking!
        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        int resultCode = firmbankingResult.getResultCode();

        // 0. 성공, 1. 실패
        apply(new RequestFirmbankingFinishedEvent(
                command.getRequestFirmbankingId(),
                command.getRechargeRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                resultCode,
                id
        ));
    }
}
