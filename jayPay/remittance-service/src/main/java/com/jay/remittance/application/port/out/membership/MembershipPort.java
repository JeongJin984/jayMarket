package com.jay.remittance.application.port.out.membership;

public interface MembershipPort {
    MembershipStatus getMembershipStatus(final String membershipId);
}
