package com.jay.banking.adapter.out.external;

import lombok.Data;

public record BankAccountRequest(
        String bankName,
        String bankAccountNumber
) {
}
