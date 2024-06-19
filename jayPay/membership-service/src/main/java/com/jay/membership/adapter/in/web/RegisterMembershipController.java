package com.jay.membership.adapter.in.web;

import com.jay.membership.application.port.in.RegisterMembershipCommand;
import com.jay.membership.application.port.in.RegisterMembershipUseCase;
import com.jay.membership.domain.Membership;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(value = "/membership/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Membership register(
            @RequestBody final RegisterMembershipRequest body
    ) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(body.name())
                .address(body.address())
                .email(body.email())
                .isValid(true)
                .isCorp(body.isCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }

}
