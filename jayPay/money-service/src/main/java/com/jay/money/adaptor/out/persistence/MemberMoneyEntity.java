package com.jay.money.adaptor.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "member_money")
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMoneyId;

    private Long membershipId;

    private BigDecimal balance;

    public MemberMoneyEntity(Long membershipId, BigDecimal balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }

    public void chargeBalance(BigDecimal moneyAmount) {
        balance = balance.add(moneyAmount);
    }
}
