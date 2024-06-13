package com.jay.membership.application.port.in;

import com.jay.membership.common.UseCase;
import com.jay.membership.domain.Membership;

@UseCase
public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
