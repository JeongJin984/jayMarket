package com.jay.remittance.application.port.out.membership;

public record MembershipStatus (
        String membershipId,
        boolean isValid
) {
}
