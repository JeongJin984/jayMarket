package com.jay.remittance.adaptor.out.service.banking;

import com.jay.common.CommonHttpClient;
import com.jay.common.ExternalSystemAdapter;
import com.jay.remittance.application.port.out.banking.BankingInfo;
import com.jay.remittance.application.port.out.banking.BankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdaptor implements BankingPort {
    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.banking.url}")
    private String bankingServiceEndpoint;

    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {
        // TODO : 미개발
        return new BankingInfo();
    }

    @Override
    public boolean requestFirmbanking(String bankName, String bankAccountNumber, BigDecimal amount) {
        // TODO : 미개발
        return true;
    }
}
