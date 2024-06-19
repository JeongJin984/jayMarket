package com.jay.banking.adapter.in.web;

public record RegisterBankAccountRequest(
        String membershipId,
        String bankName,
        String bankAccountNumber,
        boolean isValid
) {
}
