package com.jay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingFinishedEvent {
    private String requestFirmbankingId;
    private String rechargingRequestId;
    private Long membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private BigDecimal moneyAmount; // only won
    private int status;
    private String requestFirmbankingAggregateIdentifier;
}