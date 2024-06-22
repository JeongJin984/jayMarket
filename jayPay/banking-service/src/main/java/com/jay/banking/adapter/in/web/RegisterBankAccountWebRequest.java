package com.jay.banking.adapter.in.web;

public record RegisterBankAccountWebRequest(
        String membershipId,
        String bankName,
        String bankAccountNumber,
        boolean isValid
) {
}
