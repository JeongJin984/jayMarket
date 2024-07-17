package com.jay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedRegisteredBankAccountEvent {
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private Long membershipId;
    private boolean isChecked;

    private BigDecimal amount;

    private String firmbankingRequestAggregateIdentifier;

    private String toBankName;
    private String toBankAccountNumber;
}
