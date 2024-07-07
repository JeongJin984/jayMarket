package com.jay.money.adaptor.in.web;

import jakarta.validation.constraints.NotNull;

public record ChargeMoneyWebRequest(
        @NotNull Long targetMembershipId,
        @NotNull String amount
) {
}
