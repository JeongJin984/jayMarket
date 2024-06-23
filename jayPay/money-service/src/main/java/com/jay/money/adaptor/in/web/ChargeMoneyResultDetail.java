package com.jay.money.adaptor.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public record ChargeMoneyResultDetail(
        Long moneyChargeId,
        Long actionType,
        Integer resultStatus,
        BigDecimal amount
) {
}
