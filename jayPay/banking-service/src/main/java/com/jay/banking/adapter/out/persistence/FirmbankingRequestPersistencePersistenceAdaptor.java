package com.jay.banking.adapter.out.persistence;

import com.jay.banking.application.port.out.FirmbankingRequestPersistencePort;
import com.jay.banking.domain.FirmbankingRequest;
import com.jay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistencePersistenceAdaptor implements FirmbankingRequestPersistencePort {
    private final FirmbankingRequestRepository firmbankingRequestRepository;

    @Override
    public FirmbankingRequestEntity createFirmbankingRequest(FirmbankingRequest.FromBankName fromBankName, FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber, FirmbankingRequest.ToBankName toBankName, FirmbankingRequest.ToBankAccountNumber toBankAccountNumber, FirmbankingRequest.MoneyAmount moneyAmount, FirmbankingRequest.FirmbankingRequestStatus firmbankingRequestStatus, FirmbankingRequest.AggregateIdentifier aggregateIdentifier) {
        return firmbankingRequestRepository.save(
                new FirmbankingRequestEntity(
                        fromBankName.fromBankName(),
                        fromBankAccountNumber.fromBankAccountNumber(),
                        toBankName.toBankName(),
                        toBankAccountNumber.toBankAccountNumber(),
                        moneyAmount.moneyAmount(),
                        firmbankingRequestStatus,
                        aggregateIdentifier.aggregateIdentifier()
                )
        );
    }

    @Override
    public FirmbankingRequestEntity getFirmbankingRequest(FirmbankingRequest.AggregateIdentifier aggregateIdentifier) {
        Optional<FirmbankingRequestEntity> opEntity = firmbankingRequestRepository.findByAggregateIdentifier(aggregateIdentifier.aggregateIdentifier());
        opEntity.orElseThrow(() -> new RuntimeException("No Firmbanking request found"));
        return opEntity.get();
    }


}
