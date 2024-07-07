package com.jay.banking.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateFirmbankingStatusAxonCommand {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private int firmbankingStatus;
}
