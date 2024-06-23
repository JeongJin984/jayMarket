package com.jay.money.application.service;

import com.jay.money.adaptor.out.persistence.MemberMoneyEntity;
import com.jay.money.adaptor.out.persistence.MemberMoneyWebRequestEntity;
import com.jay.money.application.port.in.ChargeMoneyCommand;
import com.jay.money.application.port.in.ChargeMoneyUseCase;
import com.jay.money.application.port.out.ChargeMoneyPort;
import com.jay.money.domain.ChargeMoneyRequest;
import com.jay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoneyService implements ChargeMoneyUseCase {
    private final ChargeMoneyPort chargeMoneyPort;

    @Override
    public ChargeMoneyRequest charge(ChargeMoneyCommand command) {

        MemberMoneyEntity memberMoney = chargeMoneyPort.chargeMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount()
        );

        MemberMoneyWebRequestEntity requestEntity = chargeMoneyPort.createChargeMoneyRequest(
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
}
