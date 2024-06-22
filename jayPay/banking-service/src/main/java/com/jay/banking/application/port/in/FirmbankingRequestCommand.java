package com.jay.banking.application.port.in;

import com.jay.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FirmbankingRequestCommand extends SelfValidating<FirmbankingRequestCommand> {
    @NotNull
    @NotBlank
    private String fromBankName;

    @NotNull
    @NotBlank
    private String fromAccountNumber;

    @NotNull
    @NotBlank
    private String toBankName;

    @NotNull
    @NotBlank
    private String toAccountNumber;

    @NotNull
    private BigDecimal moneyAmount;

    public FirmbankingRequestCommand(String fromBankName, String fromAccountNumber, String toBankName, String toAccountNumber, BigDecimal moneyAmount) {
        this.fromBankName = fromBankName;
        this.fromAccountNumber = fromAccountNumber;
        this.toBankName = toBankName;
        this.toAccountNumber = toAccountNumber;
        this.moneyAmount = moneyAmount;
        this.validateSelf();
    }
}
