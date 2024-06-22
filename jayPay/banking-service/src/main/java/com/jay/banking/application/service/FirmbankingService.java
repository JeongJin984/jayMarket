package com.jay.banking.application.service;

import com.jay.banking.adapter.out.external.FirmbankingServiceRequest;
import com.jay.banking.adapter.out.external.FirmbankingServiceResponse;
import com.jay.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.jay.banking.adapter.out.persistence.FirmbankingRequestEntity;
import com.jay.banking.application.port.in.FirmbankingRequestCommand;
import com.jay.banking.application.port.in.FirmbankingUseCase;
import com.jay.banking.application.port.out.FirmbankingServicePort;
import com.jay.banking.application.port.out.FirmbankingRequestPersistencePort;
import com.jay.banking.domain.FirmbankingRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FirmbankingService implements FirmbankingUseCase {
    private final FirmBankingRequestMapper mapper;
    private final FirmbankingRequestPersistencePort firmbankingRequestPersistencePort;
    private final FirmbankingServicePort firmbankingServicePort;

    @Override
    public FirmbankingRequest requestFirmbanking(FirmbankingRequestCommand command) {
        FirmbankingRequestEntity firmbankingRequest = firmbankingRequestPersistencePort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                FirmbankingRequest.FirmbankingRequestStatus.REQUEST
        );

        FirmbankingServiceResponse response = firmbankingServicePort.send(new FirmbankingServiceRequest(
                command.getFromBankName(),
                command.getFromAccountNumber(),
                command.getToBankName(),
                command.getToAccountNumber()
        ));

        if(response.status() == 0) {
            firmbankingRequest.setFirmbankingRequestStatus(FirmbankingRequest.FirmbankingRequestStatus.COMPLETED);
        } else {
            firmbankingRequest.setFirmbankingRequestStatus(FirmbankingRequest.FirmbankingRequestStatus.FAIL);
        }
        return mapper.mapDomainToEntity(firmbankingRequest, UUID.randomUUID());
    }
}
