package com.jay.money.application.port.out;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBankAccountAggregate {
    private String registeredBankAccountId;
    private String aggregateIdentifier;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

}
