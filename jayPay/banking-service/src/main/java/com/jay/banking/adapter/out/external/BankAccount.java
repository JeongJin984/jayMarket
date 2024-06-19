package com.jay.banking.adapter.out.external;

public record BankAccount(
        String bankAccountNumber,
        String bankName,
        boolean isValid
) {
}
