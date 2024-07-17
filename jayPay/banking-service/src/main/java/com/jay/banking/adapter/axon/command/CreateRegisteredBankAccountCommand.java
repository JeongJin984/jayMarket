package com.jay.banking.adapter.axon.command;

import lombok.*;
import org.checkerframework.checker.signature.qual.BinaryName;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegisteredBankAccountCommand {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
}
