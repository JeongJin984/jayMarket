package com.jay.membership.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record RegisterMembershipRequest(
        String name,
        String address,
        String email,
        boolean isCorp
) {
}
