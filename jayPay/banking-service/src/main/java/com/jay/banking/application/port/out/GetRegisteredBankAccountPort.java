package com.jay.banking.application.port.out;

import com.jay.banking.adapter.out.persistence.RegisteredBankAccountEntity;
import com.jay.banking.application.port.in.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
