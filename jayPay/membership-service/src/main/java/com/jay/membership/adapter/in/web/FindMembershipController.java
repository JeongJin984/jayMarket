package com.jay.membership.adapter.in.web;

import com.jay.membership.application.port.in.FindMembershipCommand;
import com.jay.membership.application.port.in.FindMembershipUseCase;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {
    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<Object> findMembershipByMembershipId(@PathVariable("membershipId") String membershipId) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();

        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }
}
