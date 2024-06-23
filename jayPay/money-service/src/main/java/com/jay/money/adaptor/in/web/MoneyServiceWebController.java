package com.jay.money.adaptor.in.web;

import com.jay.money.application.port.in.ChargeMoneyCommand;
import com.jay.money.application.port.in.ChargeMoneyUseCase;
import com.jay.money.domain.ChargeMoneyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class MoneyServiceWebController {
    private final ChargeMoneyUseCase chargeMoneyUseCase;

    @GetMapping("/money/charge")
    public ChargeMoneyResultDetail chargeMoney(
            @RequestBody ChargeMoneyWebRequest body
    ) {
        ChargeMoneyCommand command = new ChargeMoneyCommand(
                Long.parseLong(body.targetMembershipId()),
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
}
