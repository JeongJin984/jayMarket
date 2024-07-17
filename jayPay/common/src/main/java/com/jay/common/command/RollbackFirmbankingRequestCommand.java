package com.jay.common.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmbankingRequestCommand {
    private String rollbackFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private Long membershipId;
    private String bankName;
    private String bankAccountNumber;
    private BigDecimal moneyAmount;
}