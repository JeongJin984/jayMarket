package com.jay.membership.application.service;

import com.jay.membership.adapter.out.persistence.MembershipEntity;
import com.jay.membership.adapter.out.persistence.MembershipMapper;
import com.jay.membership.application.port.in.FindMembershipCommand;
import com.jay.membership.application.port.in.FindMembershipUseCase;
import com.jay.membership.application.port.out.FindMembershipPort;
import com.jay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class FindMembershipService implements FindMembershipUseCase {
    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipEntity membership = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(membership);
    }
}
