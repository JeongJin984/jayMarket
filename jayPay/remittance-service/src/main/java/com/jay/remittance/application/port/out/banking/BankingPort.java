package com.jay.remittance.application.port.out.banking;

import java.math.BigDecimal;

public interface BankingPort {
    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);
    boolean requestFirmbanking(String bankName, String bankAccountNumber, BigDecimal amount);
}
