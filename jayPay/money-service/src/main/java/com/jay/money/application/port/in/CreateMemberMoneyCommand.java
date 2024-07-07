package com.jay.money.application.port.in;

import com.jay.common.SelfValidating;
import io.axoniq.axonserver.grpc.command.Command;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {
    @NotNull
    private final Long membershipId;


    public CreateMemberMoneyCommand(Long membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
