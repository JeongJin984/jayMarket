package com.jay.banking.adapter.in.web;

import com.jay.banking.adapter.axon.command.UpdateFirmbankingStatusRequest;
import com.jay.banking.application.port.in.FirmbankingRequestCommand;
import com.jay.banking.application.port.in.FirmbankingRequestUseCase;
import com.jay.banking.application.port.in.UpdateFirmbankingStatusCommand;
import com.jay.banking.application.port.in.UpdateFirmbankingStatusUseCase;
import com.jay.banking.domain.FirmbankingRequest;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FirmbankingController {

    private final FirmbankingRequestUseCase firmBankingRequestUseCase;
    private final UpdateFirmbankingStatusUseCase updateFirmbankingStatusUseCase;

    @PostMapping("/banking/firmbanking/request")
    public void send(
            @RequestBody FirmbankingWebRequest webRequest
    ) {
        FirmbankingRequest firmbankingRequest = firmBankingRequestUseCase.requestFirmbankingCheckAccount(new FirmbankingRequestCommand(
                webRequest.fromBankName(),
                webRequest.fromBankAccountNumber(),
                webRequest.toBankName(),
                webRequest.toBankAccountNumber(),
                new BigDecimal(webRequest.moneyAmount())
        ));
    }

    @PostMapping("/banking/firmbanking/request-eda")
    public void sendByEvent(
            @RequestBody FirmbankingWebRequest webRequest
    ) {
        firmBankingRequestUseCase.requestFirmbankingCheckAccountByEvent(new FirmbankingRequestCommand(
                webRequest.fromBankName(),
                webRequest.fromBankAccountNumber(),
                webRequest.toBankName(),
                webRequest.toBankAccountNumber(),
                new BigDecimal(webRequest.moneyAmount())
        ));
    }

    @PostMapping("/banking/firmbanking/update-eda")
    public void updateFirmbankingStatusByEvent(
            @RequestBody UpdateFirmbankingStatusRequest request
            ) {
        UpdateFirmbankingStatusCommand command = UpdateFirmbankingStatusCommand.builder()
                .firmbankingAggregateId(request.getFirmbankingAggregateIdentifier())
                .firmbankingStatus(request.getStatus())
                .build();
        updateFirmbankingStatusUseCase.updateFirmbankingStatusByEvent(command);
    }
}
