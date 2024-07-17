package com.jay.money.adaptor.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingRequestCreateCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String rechargingRequestId;

    private Long memberId;

    private BigDecimal amount;
}
