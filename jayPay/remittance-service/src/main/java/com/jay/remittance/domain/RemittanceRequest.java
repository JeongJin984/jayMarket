package com.jay.remittance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemittanceRequest {
    private final String remittanceRequestId;
    private final String remittanceFromMembershipId;
    private final String toBankName;
    private final String toBankAccountNumber;
    private int remittanceType;
    private BigDecimal amount;
    private String remittanceStatus;

    public record RemittanceRequestId(String remittanceRequestId) {}
    public record RemittanceFromMembershipId(String remittanceFromMembershipId) {}
    public record ToBankName(String toBankName) {}
    public record ToBankAccountNumber(String toBankAccountNumber) {}
    public record RemittanceType(int remittanceType) {}
    public record Amount(BigDecimal amount) {}
    public record RemittanceStatus(String remittanceStatus) {}

    public static RemittanceRequest generatedRemittanceRequest (
            RemittanceRequest.RemittanceRequestId remittanceRequestId,
            RemittanceRequest.RemittanceFromMembershipId remittanceFromMembershipId,
            RemittanceRequest.ToBankName toBankName,
            RemittanceRequest.ToBankAccountNumber toBankAccountNumber,
            RemittanceRequest.RemittanceType remittanceType,
            RemittanceRequest.Amount amount,
            RemittanceRequest.RemittanceStatus remittanceRequestStatus
    ) {
        return new RemittanceRequest(
                remittanceRequestId.remittanceRequestId,
                remittanceFromMembershipId.remittanceFromMembershipId,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                remittanceType.remittanceType,
                amount.amount, remittanceRequestStatus.remittanceStatus
        );
    }
}
