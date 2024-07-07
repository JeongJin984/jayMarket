package com.jay.banking.application.port.out;

import com.jay.banking.adapter.out.persistence.FirmbankingRequestEntity;
import com.jay.banking.domain.FirmbankingRequest;

public interface FirmbankingRequestPersistencePort {
    FirmbankingRequestEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmbankingRequestStatus firmbankingRequestStatus,
            FirmbankingRequest.AggregateIdentifier aggregateIdentifier
    );

    FirmbankingRequestEntity getFirmbankingRequest(
            FirmbankingRequest.AggregateIdentifier aggregateIdentifier
    );
}
