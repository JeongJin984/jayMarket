package com.jay.membership.adapter.out.persistence;

import com.jay.membership.application.port.out.FindMembershipPort;
import com.jay.membership.application.port.out.ModifyMembershipPort;
import com.jay.membership.application.port.out.RegisterMembershipPort;
import com.jay.membership.domain.Membership;
import com.jay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdaptor implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

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

    @Override
    public MembershipEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        MembershipEntity membershipEntity = membershipRepository.findById(Long.parseLong(membershipId.membershipId()))
                .orElseThrow(() -> {
                    log.error("membership id {} not found", membershipId);
                    return new IllegalArgumentException("membership id " + membershipId.membershipId() + " not found");
        });

        membershipEntity.setName(membershipName.membershipName());
        membershipEntity.setAddress(membershipEntity.getAddress());
        membershipEntity.setEmail(membershipEmail.membershipEmail());
        membershipEntity.setValid(membershipIsValid.membershipIsValid());
        membershipEntity.setCorp(membershipIsCorp.membershipIsCorp());

        return membershipEntity;
    }
}
