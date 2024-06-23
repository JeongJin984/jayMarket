package com.jay.money.domain;

import com.jay.money.adaptor.out.persistence.MemberMoneyWebRequestEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChargeMoneyRequest {
    private final Long moneyChargeRequestId;
    private final Long targetMembershipId;
    private final ActionType actionType;
    private final BigDecimal moneyAmount;
    private final int requestStatus;
    private final String uuid;
    private final LocalDateTime regDt;


    public static ChargeMoneyRequest generateChargeMoneyRequest(
            MoneyChargeRequestId moneyChargeRequestId,
            TargetMembershipId targetMembershipId,
            ActionType actionType,
            MoneyAmount moneyAmount,
            RequestStatus requestStatus,
            Uuid uuid
    ) {
        return new ChargeMoneyRequest(
                moneyChargeRequestId.moneyChargeRequestId,
                targetMembershipId.targetMembershipId,
                actionType,
                moneyAmount.moneyAmount,
                requestStatus.requestStatus,
                uuid.uuid,
                LocalDateTime.now()
        );
    }

    public record MoneyChargeRequestId(Long moneyChargeRequestId) {}
    public record TargetMembershipId(Long targetMembershipId) {}
    public record MoneyAmount(BigDecimal moneyAmount) {}
    public record RequestStatus(int requestStatus) {}
    public record Uuid(String uuid) {}

    public enum ActionType {
        INCREASING, CHARGE_BACK, REFUND
    }
}
