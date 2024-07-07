package com.jay.banking.application.port.in;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmbankingStatusCommand extends SelfValidating<UpdateFirmbankingStatusCommand> {
    @NotNull
    private final String firmbankingAggregateId;

    @NotNull
    private final int firmbankingStatus;
}
