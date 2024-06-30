package com.jay.remittance.application.port.out.money;

import java.math.BigDecimal;

public record MemberMoneyInfo (
        String membershipId,
        BigDecimal balance
) {

}
