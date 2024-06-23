package com.jay.money.adaptor.out.persistence;

import com.jay.money.domain.ChargeMoneyRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberMoneyWebRequestEntity {
    @Id
    @GeneratedValue
    private Long moneyActionRequestId;

    private Long targetMembershipId;

    @Enumerated(EnumType.STRING)
    private ChargeMoneyRequest.ActionType moneyActionType;

    private BigDecimal moneyAmount;

    private LocalDateTime regDt;

    private int requestStatus;
    private String uuid;

    public MemberMoneyWebRequestEntity(Long targetMembershipId, ChargeMoneyRequest.ActionType moneyActionType, BigDecimal moneyAmount, LocalDateTime regDt, int requestStatus, String uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyActionType = moneyActionType;
        this.moneyAmount = moneyAmount;
        this.regDt = regDt;
        this.requestStatus = requestStatus;
        this.uuid = uuid;
    }
}
