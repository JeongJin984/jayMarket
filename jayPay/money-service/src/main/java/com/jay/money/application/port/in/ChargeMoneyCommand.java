package com.jay.money.application.port.in;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeMoneyCommand extends SelfValidating<ChargeMoneyCommand> {
    @NotNull
    private final Long targetMembershipId;

    @NotNull
    private final BigDecimal amount;

    public ChargeMoneyCommand(@NotNull Long targetMembershipId, @NotNull BigDecimal amount) {
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
        this.validateSelf();
    }
}
