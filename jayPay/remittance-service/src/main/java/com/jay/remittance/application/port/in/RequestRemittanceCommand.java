package com.jay.remittance.application.port.in;

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
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

    @NotNull
    private String fromMembershipId; // from membership

    private String toMembershipId; // to membership

    private String toBankName;

    private String toBankAccountNumber;

    private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)

    // 송금요청 금액
    @NotNull
    private BigDecimal amount;

    public RequestRemittanceCommand(String fromMembershipId, String toMembershipId, String toBankName, String toBankAccountNumber, int remittanceType, BigDecimal amount) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.remittanceType = remittanceType;
        this.amount = amount;

        this.validateSelf();
    }
}