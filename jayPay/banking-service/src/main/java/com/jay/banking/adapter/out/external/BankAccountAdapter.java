package com.jay.banking.adapter.out.external;

import com.jay.banking.application.port.out.RequestBankAccountInfoPort;
import com.jay.common.ExternalSystemAdapter;

@ExternalSystemAdapter
public class BankAccountAdapter implements RequestBankAccountInfoPort {
    @Override
    public BankAccount getBankAccountInfo(BankAccountRequest bankAccountRequest) {
        return new BankAccount(bankAccountRequest.bankAccountNumber(), bankAccountRequest.bankName(), true);
    }
}
