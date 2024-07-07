package com.jay.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Getter
public class FirmbankingRequestEvent {
    private String fromBankName;
    private String fromAccountNumber;

    private String toBankName;
    private String toBankAccountNumber;

    private BigDecimal moneyAmount;
    private int firmbankingStatus;
}
