package com.jay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    private final String registeredBankAccountId;
    private final String membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final Boolean linkedStatusIsValid;

    public static RegisteredBankAccount generateBankAccount(
            RegisteredBankAccountId registeredBankAccountId,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid
    ) {
        return new RegisteredBankAccount(
                registeredBankAccountId.registeredBankAccountId,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid
        );
    }

    public record RegisteredBankAccountId (String registeredBankAccountId) {}
    public record MembershipId (String membershipId) {}
    public record BankName (String bankName) {}
    public record BankAccountNumber (String bankAccountNumber) {}
    public record LinkedStatusIsValid (boolean linkedStatusIsValid) {}
}
