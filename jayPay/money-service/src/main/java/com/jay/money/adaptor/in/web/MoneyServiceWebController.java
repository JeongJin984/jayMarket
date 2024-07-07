package com.jay.money.adaptor.in.web;

import com.jay.money.application.port.in.ChargeMoneyCommand;
import com.jay.money.application.port.in.ChargeMoneyUseCase;
import com.jay.money.application.port.in.CreateMemberMoneyCommand;
import com.jay.money.application.port.in.CreateMemberMoneyUseCase;
import com.jay.money.domain.ChargeMoneyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class MoneyServiceWebController {
    private final ChargeMoneyUseCase chargeMoneyUseCase;
    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;

    @GetMapping("/money/charge")
    public ChargeMoneyResultDetail chargeMemberMoney(
            @RequestBody ChargeMoneyWebRequest body
    ) {
        ChargeMoneyCommand command = new ChargeMoneyCommand(
                body.targetMembershipId(),
                new BigDecimal(body.amount())
        );

        ChargeMoneyRequest charge = chargeMoneyUseCase.charge(command);
        return new ChargeMoneyResultDetail(
                charge.getMoneyChargeRequestId(),
                0L,
                0,
                charge.getMoneyAmount()
        );
    }

    @PostMapping("/money/create-member-money")
    void createMemberMoney(@RequestBody ChargeMoneyWebRequest body) {
        createMemberMoneyUseCase.createMemberMoney(
                CreateMemberMoneyCommand.builder()
                        .membershipId(body.targetMembershipId())
                        .build()
        );
    }

    @PostMapping("/money/charge-eda")
    void chargeMemberMoneyEDA(@RequestBody ChargeMoneyWebRequest body) {
        chargeMoneyUseCase.chargeMemberMoneyRequestByEvent(
                ChargeMoneyCommand.builder()
                        .targetMembershipId(body.targetMembershipId())
                        .amount(new BigDecimal(body.amount()))
                        .build()
        );
    }
}
