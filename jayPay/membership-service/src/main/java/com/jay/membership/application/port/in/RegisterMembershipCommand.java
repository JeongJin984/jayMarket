package com.jay.membership.application.port.in;

import com.jay.membership.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
    @NotNull
    private final String name;

    @NotNull
    private final String email;

    @NotNull
    @NotBlank
    private final String address;

    @NotNull
    private final boolean isCorp;

    @AssertTrue
    private final boolean isValid;

    public RegisterMembershipCommand(String name, String email, String address, boolean isCorp, boolean isValid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isCorp = isCorp;
        this.isValid = isValid;
        this.validateSelf();
    }
}
