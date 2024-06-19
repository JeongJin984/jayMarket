package com.jay.membership.adapter.in.web;

import com.jay.membership.application.port.in.ModifyMembershipCommand;
import com.jay.membership.application.port.in.ModifyMembershipUseCase;
import com.jay.membership.domain.Membership;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping("/membership/modify/")
    ResponseEntity<Membership> findMembership(
            @RequestBody ModifyMembershipRequest request
    ) {
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.membershipId())
                .name(request.name())
                .address(request.address())
                .email(request.email())
                .isValid(request.isValid())
                .isCorp(request.isCorp())
                .build();

        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }
}
