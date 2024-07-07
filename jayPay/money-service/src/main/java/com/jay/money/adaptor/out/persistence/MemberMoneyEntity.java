package com.jay.money.adaptor.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "member_money")
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMoneyId;

    private Long membershipId;

    private BigDecimal balance;

    private String aggregateIdentifier;

    public MemberMoneyEntity(Long membershipId, BigDecimal balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public void chargeBalance(BigDecimal moneyAmount) {
        balance = balance.add(moneyAmount);
    }
}
