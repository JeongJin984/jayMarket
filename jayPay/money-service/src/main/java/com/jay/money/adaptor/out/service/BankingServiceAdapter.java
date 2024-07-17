package com.jay.money.adaptor.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.common.CommonHttpClient;
import com.jay.money.application.port.out.GetRegisteredBankAccountPort;
import com.jay.money.application.port.out.RegisterBankAccountAggregate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankingServiceAdapter implements GetRegisteredBankAccountPort {

    private final CommonHttpClient commonHttpClient;

    private final String bankingServiceUrl;

    public BankingServiceAdapter(CommonHttpClient commonHttpClient,
                                 @Value("${service.banking.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.bankingServiceUrl = membershipServiceUrl;
    }

    @Override
    public RegisterBankAccountAggregate getRegisteredBankAccountAggregate(Long membershipId){
        String url = String.join("/", bankingServiceUrl, "banking/account", membershipId.toString());
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            // json RegisteredBankAccount

            ObjectMapper mapper = new ObjectMapper();
            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount.class);

            return new RegisterBankAccountAggregate(
                    registeredBankAccount.getRegisteredBankAccountId()
                    , registeredBankAccount.getAggregateIdentifier()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankAccountNumber()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}