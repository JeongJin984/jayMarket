package com.jay.money.application.port.out;

import com.jay.money.adaptor.out.persistence.MemberMoneyEntity;
import com.jay.money.adaptor.out.persistence.MemberMoneyWebRequestEntity;
import com.jay.money.domain.ChargeMoneyRequest;
import com.jay.money.domain.MemberMoney;

import java.math.BigDecimal;

public interface GetMemberMoneyPort {
    MemberMoneyEntity getMemberMoney(
            MemberMoney.MembershipId membershipId
    );
}
