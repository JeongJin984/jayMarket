package com.jay.banking.adapter.out.external;

public record FirmbankingServiceRequest(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber
) {
}
