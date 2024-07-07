package com.jay.money.adaptor.axon.command;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class MemberMoneyIncreasedAxonCommand extends SelfValidating<MemberMoneyIncreasedAxonCommand> {
    @NotNull
    private Long membershipId;

    private BigDecimal amount;

    private String aggregateIdentifier;

    public MemberMoneyIncreasedAxonCommand(Long membershipId, BigDecimal amount, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.amount = amount;
        this.aggregateIdentifier = aggregateIdentifier;
        validateSelf();
    }
}
