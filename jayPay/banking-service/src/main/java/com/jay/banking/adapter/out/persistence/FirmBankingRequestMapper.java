package com.jay.banking.adapter.out.persistence;

import com.jay.banking.domain.FirmbankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmBankingRequestMapper {
    public FirmbankingRequest mapDomainToEntity(final FirmbankingRequestEntity entity, UUID uuid) {
        return FirmbankingRequest.generateFirmbankingRequest(
                new FirmbankingRequest.FirmbankingRequestId(entity.getFirmbankingRequestId().toString()),
                new FirmbankingRequest.FromBankName(entity.getToBankName()),
                new FirmbankingRequest.FromBankAccountNumber(entity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(entity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(entity.getMoneyAmount()),
                entity.getFirmbankingRequestStatus(),
                uuid
        );
    }
}
