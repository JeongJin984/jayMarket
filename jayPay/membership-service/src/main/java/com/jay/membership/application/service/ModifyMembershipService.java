 package com.jay.membership.application.service;

import com.jay.membership.adapter.out.persistence.MembershipEntity;
import com.jay.membership.adapter.out.persistence.MembershipMapper;
import com.jay.membership.application.port.in.ModifyMembershipCommand;
import com.jay.membership.application.port.in.ModifyMembershipUseCase;
import com.jay.membership.application.port.in.RegisterMembershipCommand;
import com.jay.membership.application.port.in.RegisterMembershipUseCase;
import com.jay.membership.application.port.out.ModifyMembershipPort;
import com.jay.membership.application.port.out.RegisterMembershipPort;
import com.jay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

 @RequiredArgsConstructor
 @Transactional
 @Service
 public class ModifyMembershipService implements ModifyMembershipUseCase {
     private final ModifyMembershipPort modifyMembershipPort;
     private final MembershipMapper membershipMapper;

     @Override
     public Membership modifyMembership(ModifyMembershipCommand command) {
         MembershipEntity entity = modifyMembershipPort.modifyMembership(
                 new Membership.MembershipId(command.getMembershipId()),
                 new Membership.MembershipName(command.getName()),
                 new Membership.MembershipEmail(command.getEmail()),
                 new Membership.MembershipAddress(command.getAddress()),
                 new Membership.MembershipIsValid(command.isValid()),
                 new Membership.MembershipIsCorp(command.isCorp())
         );

         return membershipMapper.mapToDomainEntity(entity);
     }
 }
