package com.jay.membership.application.port.out;

import com.jay.membership.adapter.out.persistence.MembershipEntity;
import com.jay.membership.domain.Membership;

public interface RegisterMembershipPort {
    MembershipEntity createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    );
}
