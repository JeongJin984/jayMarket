package com.jay.banking.adapter.axon.command;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Getter
@Builder
public class FirmbankingRequestAxonCommand {
    private String fromBankName;
    private String fromAccountNumber;

    private String toBankName;
    private String toBankAccountNumber;

    private BigDecimal moneyAmount;
    private int firmbankingStatus;
}
