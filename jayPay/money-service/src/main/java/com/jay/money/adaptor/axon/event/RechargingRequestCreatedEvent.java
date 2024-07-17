package com.jay.money.adaptor.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingRequestCreatedEvent {
    private String rechargingRequestId;
    private Long membershipId;
    private BigDecimal amount;
    private String registeredBankAccountAggregateIdentifier;
    private String bankName;
    private String bankAccountNumber;
}
