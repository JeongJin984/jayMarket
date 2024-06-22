package com.jay.banking.adapter.in.web;

public record FirmbankingWebRequest(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber,
        int moneyAmount
) {
}
