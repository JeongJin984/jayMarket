package com.jay.banking.application.port.out;

import com.jay.banking.adapter.out.external.BankAccount;
import com.jay.banking.adapter.out.external.BankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(BankAccountRequest bankAccountRequest);
}
