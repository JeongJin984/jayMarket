package com.jay.money.application.port.out;

public interface GetRegisteredBankAccountPort {
    RegisterBankAccountAggregate getRegisteredBankAccountAggregate(Long membershipId);
}
