package com.jay.banking.application.port.in;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class GetRegisteredBankAccountCommand extends SelfValidating<GetRegisteredBankAccountCommand> {
    @NotNull
    private final String membershipId;

    public GetRegisteredBankAccountCommand(final String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
