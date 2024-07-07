package com.jay.money.application.port.out;

import com.jay.money.adaptor.out.persistence.MemberMoneyWebRequestEntity;
import com.jay.money.adaptor.out.persistence.MemberMoneyEntity;
import com.jay.money.domain.ChargeMoneyRequest;
import com.jay.money.domain.MemberMoney;

import java.math.BigDecimal;

public interface ChargeMemberMoneyPort {
    MemberMoneyWebRequestEntity createChargeMoneyRequest(
            ChargeMoneyRequest.TargetMembershipId targetMembershipId,
            ChargeMoneyRequest.ActionType actionType,
            ChargeMoneyRequest.MoneyAmount moneyAmount,
            ChargeMoneyRequest.RequestStatus requestStatus,
            ChargeMoneyRequest.Uuid uuid
    );

    MemberMoneyEntity chargeMoney(MemberMoney.MembershipId memberMoneyId, BigDecimal moneyAmount);
}
