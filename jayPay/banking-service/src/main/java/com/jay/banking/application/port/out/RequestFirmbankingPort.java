package com.jay.banking.application.port.out;

import com.jay.banking.adapter.out.persistence.FirmbankingRequestEntity;
import com.jay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingPort {

    FirmbankingRequestEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmbankingRequestStatus firmbankingStatus,
            FirmbankingRequest.AggregateIdentifier firmbankingAggregateIdentifier
    );

    FirmbankingRequestEntity modifyFirmbankingRequest(
            FirmbankingRequestEntity entity
    );

    FirmbankingRequestEntity getFirmbankingRequest(
            FirmbankingRequest.AggregateIdentifier firmbankingAggregateIdentifier
    );
}