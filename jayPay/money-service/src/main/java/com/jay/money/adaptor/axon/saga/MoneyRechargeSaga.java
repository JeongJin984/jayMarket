package com.jay.money.adaptor.axon.saga;

import com.jay.common.command.RequestFirmbankingCommand;
import com.jay.common.command.RollbackFirmbankingRequestCommand;
import com.jay.common.event.CheckRegisteredBankAccountCommand;
import com.jay.common.event.CheckedRegisteredBankAccountEvent;
import com.jay.common.event.RequestFirmbankingFinishedEvent;
import com.jay.money.adaptor.axon.event.RechargingRequestCreatedEvent;
import com.jay.money.adaptor.out.persistence.MemberMoneyEntity;
import com.jay.money.application.port.out.ChargeMemberMoneyPort;
import com.jay.money.domain.MemberMoney;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@NoArgsConstructor
@Slf4j
public class MoneyRechargeSaga {
    @NotNull
    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handle(RechargingRequestCreatedEvent event) {
        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("check", checkRegisteredBankAccountId);

        commandGateway.send(new CheckRegisteredBankAccountCommand(
                event.getRegisteredBankAccountAggregateIdentifier(),
                event.getRechargingRequestId(),
                event.getMembershipId(),
                checkRegisteredBankAccountId,
                event.getBankName(),
                event.getBankAccountNumber(),
                event.getAmount()
        ));
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckedRegisteredBankAccountEvent event) {
        System.out.println("CheckedRegisteredBankAccountEvent saga: " + event.toString());
        boolean status = event.isChecked();
        if (status) {
            System.out.println("CheckedRegisteredBankAccountEvent event success");
        } else {
            System.out.println("CheckedRegisteredBankAccountEvent event Failed");
        }

        String requestFirmbankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmbankingId", requestFirmbankingId);

        // 2. 송금 요청
        commandGateway.send(new RequestFirmbankingCommand(
                requestFirmbankingId,
                event.getFirmbankingRequestAggregateIdentifier()
                , event.getRechargingRequestId()
                , event.getMembershipId()
                , "fastcampus"
                , "123456789"
                , event.getToBankName()
                , event.getToBankAccountNumber()
                , event.getAmount()
        )).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.error("RequestFirmbankingCommand Command failed", throwable);
                    } else {
                        System.out.println("RequestFirmbankingCommand Command success");
                    }
                }
        );
    }

    @SagaEventHandler(associationProperty = "requestFirmbankingId")
    public void handle(RequestFirmbankingFinishedEvent event, ChargeMemberMoneyPort chargeMemberMoneyPort) {
        System.out.println("RequestFirmbankingFinishedEvent saga: " + event.toString());
        boolean status = event.getStatus() == 0;
        if (status) {
            System.out.println("RequestFirmbankingFinishedEvent event success");
        } else {
            System.out.println("RequestFirmbankingFinishedEvent event Failed");
        }

        // DB Update 명령.
        MemberMoneyEntity resultEntity =
                chargeMemberMoneyPort.chargeMoney(
                        new MemberMoney.MembershipId(event.getMembershipId())
                        , event.getMoneyAmount()
                );

        if (resultEntity == null) {
            // 실패 시, 롤백 이벤트
            String rollbackFirmbankingId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackFirmbankingId", rollbackFirmbankingId);
            commandGateway.send(new RollbackFirmbankingRequestCommand(
                    rollbackFirmbankingId
                    ,event.getRequestFirmbankingAggregateIdentifier()
                    , event.getRechargingRequestId()
                    , event.getMembershipId()
                    , event.getToBankName()
                    , event.getToBankAccountNumber()
                    , event.getMoneyAmount()
            )).whenComplete(
                    (result, throwable) -> {
                        if (throwable != null) {
                            throwable.printStackTrace();
                            System.out.println("RollbackFirmbankingRequestCommand Command failed");
                        } else {
                            System.out.println("Saga success : "+ result.toString());
                            SagaLifecycle.end();
                        }
                    }
            );
        } else {
            // 성공 시, saga 종료.
            SagaLifecycle.end();
        }
    }
}
