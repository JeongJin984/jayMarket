package com.jay.money.application.port.in;

import com.jay.money.domain.ChargeMoneyRequest;

public interface ChargeMoneyUseCase {
    ChargeMoneyRequest charge(ChargeMoneyCommand command);
}
