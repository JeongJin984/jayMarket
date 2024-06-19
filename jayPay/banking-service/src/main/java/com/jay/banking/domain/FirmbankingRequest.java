package com.jay.banking.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {
    private final String firmbankingRequestId;
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final String moneyAmount;
    @Enumerated(EnumType.STRING)
    private final FirmbankingRequestStatus firmbankingStatus;

    public static FirmbankingRequest generateBankAccount(
            FirmbankingRequestId firmbankingRequestId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmbankingRequestStatus firmbankingRequestStatus
    ) {
        return new FirmbankingRequest(
                firmbankingRequestId.firmbankingRequestId(),
                fromBankName.fromBankName(),
                fromBankAccountNumber.fromBankAccountNumber(),
                toBankName.toBankName(),
                toBankAccountNumber.toBankAccountNumber(),
                moneyAmount.moneyAmount(),
                firmbankingRequestStatus
        );
    }

    public record FirmbankingRequestId(String firmbankingRequestId) {}
    public record FromBankName(String fromBankName) {}
    public record FromBankAccountNumber(String fromBankAccountNumber) {}
    public record ToBankName(String toBankName) {}
    public record ToBankAccountNumber(String toBankAccountNumber) {}
    public record MoneyAmount(String moneyAmount) {}

    public enum FirmbankingRequestStatus {
        REQUEST, COMPLETED, FAIL
    }
}
