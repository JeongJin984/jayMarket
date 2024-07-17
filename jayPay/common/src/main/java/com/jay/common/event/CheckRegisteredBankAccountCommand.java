package com.jay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.checkerframework.checker.units.qual.N;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredBankAccountCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private Long membershipId;

    private String checkRegisteredBankAccountId;
    private String bankName;
    private String bankAccountNumber;
    private BigDecimal amount;
}
