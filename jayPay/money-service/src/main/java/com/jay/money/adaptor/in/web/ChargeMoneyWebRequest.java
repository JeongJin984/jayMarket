package com.jay.money.adaptor.in.web;

import jakarta.validation.constraints.NotNull;

public record ChargeMoneyWebRequest(
        @NotNull String targetMembershipId,
        @NotNull String amount
) {
}
