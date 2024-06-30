package com.jay.remittance.adaptor.in;

public record RequestRemittanceRequest (
        String fromMembershipId,
        String toMembershipId,
        String toBankName,
        String toBankAccountNumber,
        int remittanceType,
        String amount
) {

}
