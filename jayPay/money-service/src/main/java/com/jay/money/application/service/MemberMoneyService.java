package com.jay.money.application.service;

import com.jay.money.adaptor.axon.command.MemberMoneyCreatedAxonCommand;
import com.jay.money.adaptor.axon.command.MemberMoneyIncreasedAxonCommand;
import com.jay.money.adaptor.out.persistence.MemberMoneyEntity;
import com.jay.money.adaptor.out.persistence.MemberMoneyWebRequestEntity;
import com.jay.money.application.port.in.ChargeMoneyCommand;
import com.jay.money.application.port.in.ChargeMoneyUseCase;
import com.jay.money.application.port.in.CreateMemberMoneyCommand;
import com.jay.money.application.port.in.CreateMemberMoneyUseCase;
import com.jay.money.application.port.out.ChargeMemberMoneyPort;
import com.jay.money.application.port.out.CreateMemberMoneyPort;
import com.jay.money.application.port.out.GetMemberMoneyPort;
import com.jay.money.domain.ChargeMoneyRequest;
import com.jay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberMoneyService implements ChargeMoneyUseCase, CreateMemberMoneyUseCase {
    private final ChargeMemberMoneyPort chargeMemberMoneyPort;
    private final CreateMemberMoneyPort createMemberMoneyPort;
    private final GetMemberMoneyPort getMemberMoneyPort;
    private final CommandGateway commandGateway;

    @Override
    public ChargeMoneyRequest charge(ChargeMoneyCommand command) {

        MemberMoneyEntity memberMoney = chargeMemberMoneyPort.chargeMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount()
        );

        MemberMoneyWebRequestEntity requestEntity = chargeMemberMoneyPort.createChargeMoneyRequest(
                new ChargeMoneyRequest.TargetMembershipId(command.getTargetMembershipId()),
                ChargeMoneyRequest.ActionType.INCREASING,
                new ChargeMoneyRequest.MoneyAmount(command.getAmount()),
                new ChargeMoneyRequest.RequestStatus(1),
                new ChargeMoneyRequest.Uuid(UUID.randomUUID().toString())
        );

        return ChargeMoneyRequest.generateChargeMoneyRequest(
                new ChargeMoneyRequest.MoneyChargeRequestId(requestEntity.getMoneyActionRequestId()),
                new ChargeMoneyRequest.TargetMembershipId(requestEntity.getTargetMembershipId()),
                requestEntity.getMoneyActionType(),
                new ChargeMoneyRequest.MoneyAmount(requestEntity.getMoneyAmount()),
                new ChargeMoneyRequest.RequestStatus(requestEntity.getRequestStatus()),
                new ChargeMoneyRequest.Uuid(requestEntity.getUuid())
        );
    }

    @Override
    public void chargeMemberMoneyRequestByEvent(ChargeMoneyCommand command) {
        MemberMoneyEntity memberMoney = getMemberMoneyPort.getMemberMoney(new MemberMoney.MembershipId(command.getTargetMembershipId()));

        commandGateway.send(
                MemberMoneyIncreasedAxonCommand.builder()
                        .aggregateIdentifier(memberMoney.getAggregateIdentifier())
                        .membershipId(command.getTargetMembershipId())
                        .amount(command.getAmount())
                        .build()
        ).whenComplete((aggregateId, error) -> {
            if (error != null) {
                log.error("error", error);
                throw new RuntimeException(error);
            } else {
                chargeMemberMoneyPort.chargeMoney(
                        new MemberMoney.MembershipId(command.getTargetMembershipId()),
                        command.getAmount()
                );
            }
        });
    }

    @Override
    public void createMemberMoney(CreateMemberMoneyCommand command) {
        MemberMoneyCreatedAxonCommand axonCommand = new MemberMoneyCreatedAxonCommand(command.getMembershipId());
        commandGateway.send(axonCommand).whenComplete((aggregateId, error) -> {
            if(error != null) {
                log.error("Error creating memberMoney", error);
                throw new RuntimeException(error);
            } else {
                createMemberMoneyPort.createMemberMoney(
                        new MemberMoney.MembershipId(command.getMembershipId()),
                        new MemberMoney.MemberMoneyAggregateIdentifier(aggregateId.toString())
                );
            }
        });
    }
}
