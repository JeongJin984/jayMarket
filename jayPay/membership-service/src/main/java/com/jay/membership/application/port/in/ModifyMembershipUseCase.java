package com.jay.membership.application.port.in;

import com.jay.membership.domain.Membership;

import java.lang.reflect.Member;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand member);
}
