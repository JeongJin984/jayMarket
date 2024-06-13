package com.jay.membership.adapter.out.persistence;

import com.jay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipEntity membership) {
        return Membership.generateMembership(
          new Membership.MembershipId(membership.getMembershipId().toString()),
          new Membership.MembershipName(membership.getName()),
          new Membership.MembershipEmail(membership.getEmail()),
          new Membership.MembershipAddress(membership.getAddress()),
          new Membership.MembershipIsValid(membership.isValid()),
          new Membership.MembershipIsCorp(membership.isCorp())
        );
    }
}
