package com.jay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {
    private final Long memberMoneyId;
    private final Long membershipId;
    private final BigDecimal balance;

    public static MemberMoney generateMemberMoney(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            MemberBalance balance
    ) {
        return new MemberMoney(
                memberMoneyId.memberMoneyId,
                membershipId.membershipId,
                balance.balance
        );
    }

    public record MemberMoneyId(Long memberMoneyId) {}
    public record MembershipId(Long membershipId) {}
    public record MemberBalance(BigDecimal balance) {}


}
