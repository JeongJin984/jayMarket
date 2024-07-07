package com.jay.banking.application.service;

import com.jay.banking.adapter.axon.command.FirmbankingRequestAxonCommand;
import com.jay.banking.adapter.axon.command.UpdateFirmbankingStatusAxonCommand;
import com.jay.banking.adapter.out.external.FirmbankingServiceRequest;
import com.jay.banking.adapter.out.external.FirmbankingServiceResponse;
import com.jay.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.jay.banking.adapter.out.persistence.FirmbankingRequestEntity;
import com.jay.banking.application.port.in.FirmbankingRequestCommand;
import com.jay.banking.application.port.in.FirmbankingRequestUseCase;
import com.jay.banking.application.port.in.UpdateFirmbankingStatusCommand;
import com.jay.banking.application.port.in.UpdateFirmbankingStatusUseCase;
import com.jay.banking.application.port.out.FirmbankingServicePort;
import com.jay.banking.application.port.out.FirmbankingRequestPersistencePort;
import com.jay.banking.domain.FirmbankingRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FirmbankingRequestService implements FirmbankingRequestUseCase, UpdateFirmbankingStatusUseCase {
    private final FirmBankingRequestMapper mapper;
    private final FirmbankingRequestPersistencePort firmbankingRequestPersistencePort;
    private final FirmbankingServicePort firmbankingServicePort;
    private final CommandGateway commandGateway;

    @Override
    public FirmbankingRequest requestFirmbankingCheckAccount(FirmbankingRequestCommand command) {
        FirmbankingRequestEntity firmbankingRequest = firmbankingRequestPersistencePort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                FirmbankingRequest.FirmbankingRequestStatus.REQUEST,
                new FirmbankingRequest.AggregateIdentifier("")
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

    @Override
    public void requestFirmbankingCheckAccountByEvent(FirmbankingRequestCommand command) {
        FirmbankingRequestAxonCommand axonCommand = FirmbankingRequestAxonCommand.builder()
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToAccountNumber())
                .fromBankName(command.getFromBankName())
                .fromAccountNumber(command.getFromAccountNumber())
                .moneyAmount(command.getMoneyAmount())
                .build();

        commandGateway.send(axonCommand).whenComplete(
                (response, ex) -> {
                    if(ex != null) {
                        log.error("error occurred when requesting firmbanking", ex);
                    } else {
                        FirmbankingRequestEntity entity = firmbankingRequestPersistencePort.createFirmbankingRequest(
                                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                                new FirmbankingRequest.FromBankAccountNumber(command.getFromAccountNumber()),
                                new FirmbankingRequest.ToBankName(command.getToBankName()),
                                new FirmbankingRequest.ToBankAccountNumber(command.getToAccountNumber()),
                                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                                FirmbankingRequest.FirmbankingRequestStatus.REQUEST,
                                new FirmbankingRequest.AggregateIdentifier(response.toString())
                        );

                        FirmbankingServiceResponse result = firmbankingServicePort.send(
                                new FirmbankingServiceRequest(
                                        command.getFromBankName(),
                                        command.getFromAccountNumber(),
                                        command.getToBankName(),
                                        command.getToAccountNumber()
                                )
                        );

                        if(result.status() == 0) {
                            entity.setFirmbankingRequestStatus(FirmbankingRequest.FirmbankingRequestStatus.COMPLETED);
                        } else {
                            entity.setFirmbankingRequestStatus(FirmbankingRequest.FirmbankingRequestStatus.FAIL);
                        }
                    }
                }
        );
    }

    @Override
    public void updateFirmbankingStatusByEvent(UpdateFirmbankingStatusCommand command) {
        UpdateFirmbankingStatusAxonCommand axonCommand = new UpdateFirmbankingStatusAxonCommand(command.getFirmbankingAggregateId(), command.getFirmbankingStatus());
        commandGateway.send(axonCommand).whenComplete((response, ex) -> {
            if(ex != null) {
                log.error("error occurred when updating firmbanking status", ex);
            } else {
                FirmbankingRequestEntity firmbankingRequest = firmbankingRequestPersistencePort.getFirmbankingRequest(new FirmbankingRequest.AggregateIdentifier(command.getFirmbankingAggregateId()));
                firmbankingRequest.setFirmbankingRequestStatus(FirmbankingRequest.FirmbankingRequestStatus.findByNum(command.getFirmbankingStatus()));
            }
        })
    }
}
