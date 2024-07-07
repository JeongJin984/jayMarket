package com.jay.money.adaptor.axon.event;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberMoneyCreatedEvent extends SelfValidating<MemberMoneyCreatedEvent> {
    @NotNull
    private final Long membershipId;


    public MemberMoneyCreatedEvent(Long membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
