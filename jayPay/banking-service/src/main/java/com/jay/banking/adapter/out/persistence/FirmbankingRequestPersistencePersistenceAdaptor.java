package com.jay.banking.adapter.out.persistence;

import com.jay.banking.application.port.out.FirmbankingRequestPersistencePort;
import com.jay.banking.domain.FirmbankingRequest;
import com.jay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistencePersistenceAdaptor implements FirmbankingRequestPersistencePort {
    private final FirmbankingRequestRepository firmbankingRequestRepository;

    @Override
    public FirmbankingRequestEntity createFirmbankingRequest(FirmbankingRequest.FromBankName fromBankName, FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber, FirmbankingRequest.ToBankName toBankName, FirmbankingRequest.ToBankAccountNumber toBankAccountNumber, FirmbankingRequest.MoneyAmount moneyAmount, FirmbankingRequest.FirmbankingRequestStatus firmbankingRequestStatus) {
        return firmbankingRequestRepository.save(
                new FirmbankingRequestEntity(
                        fromBankName.fromBankName(),
                        fromBankAccountNumber.fromBankAccountNumber(),
                        toBankName.toBankName(),
                        toBankAccountNumber.toBankAccountNumber(),
                        moneyAmount.moneyAmount(),
                        firmbankingRequestStatus
                )
        );
    }
}
