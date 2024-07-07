package com.jay.money.adaptor.axon.event;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberMoneyIncreasedEvent extends SelfValidating<MemberMoneyIncreasedEvent> {
    @NotNull
    private Long membershipId;

    private BigDecimal amount;

    private String aggregateIdentifier;

    public MemberMoneyIncreasedEvent(Long membershipId, BigDecimal amount, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.amount = amount;
        this.aggregateIdentifier = aggregateIdentifier;
        validateSelf();
    }
}
