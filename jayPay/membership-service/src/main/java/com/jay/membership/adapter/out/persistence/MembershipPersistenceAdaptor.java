package com.jay.membership.adapter.out.persistence;

import com.jay.membership.application.port.out.FindMembershipPort;
import com.jay.membership.application.port.out.RegisterMembershipPort;
import com.jay.membership.common.PersistenceAdapter;
import com.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdaptor implements RegisterMembershipPort, FindMembershipPort {

    private final MembershipEntityRepository membershipRepository;

    @Override
    public MembershipEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return membershipRepository.save(new MembershipEntity(
                membershipName.membershipName(),
                membershipEmail.membershipEmail(),
                membershipAddress.membershipAddress(),
                membershipIsValid.membershipIsValid(),
                membershipIsCorp.membershipIsCorp()
        ));
    }

    @Override
    public MembershipEntity findMembership(Membership.MembershipId membershipId) {
        return membershipRepository.findById(Long.parseLong(membershipId.membershipId())).orElseThrow(() -> {
            log.error("membership id {} not found", membershipId);
            return new IllegalArgumentException("membership id " + membershipId.membershipId() + " not found");
        });
    }
}
