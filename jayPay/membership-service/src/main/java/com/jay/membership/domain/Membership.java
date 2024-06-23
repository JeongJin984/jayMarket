package com.jay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean isValid;
    private final boolean isCorp;

    public static Membership generateMembership(
            MembershipId membershipId,
            MembershipName membershipName,
            MembershipEmail membershipEmail,
            MembershipAddress membershipAddress,
            MembershipIsValid membershipIsValid,
            MembershipIsCorp membershipIsCorp
    ) {
        return new Membership(
                membershipId.membershipId,
                membershipName.membershipName,
                membershipEmail.membershipEmail,
                membershipAddress.membershipAddress,
                membershipIsValid.membershipIsValid,
                membershipIsCorp.membershipIsCorp
        );
    }

    public record MembershipId (String membershipId) {}
    public record MembershipName (String membershipName) {}
    public record MembershipEmail (String membershipEmail) {}
    public record MembershipAddress (String membershipAddress) {}
    public record MembershipIsValid (boolean membershipIsValid) {}
    public record MembershipIsCorp (boolean membershipIsCorp) {}
}
