package com.jay.money.adaptor.axon.command;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class MemberMoneyCreatedAxonCommand extends SelfValidating<MemberMoneyCreatedAxonCommand> {
    @NotNull
    private Long membershipId;

    public MemberMoneyCreatedAxonCommand(@NotNull Long membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }


}
