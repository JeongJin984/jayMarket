package com.jay.membership.application.port.in;

import com.jay.membership.domain.Membership;
import com.jay.common.UseCase;

@UseCase
public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
