package com.jay.banking.adapter.in.web;

import com.jay.banking.application.port.in.FirmbankingRequestCommand;
import com.jay.banking.application.port.in.FirmbankingRequestUseCase;
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

    @PostMapping("/banking/firmbanking/request")
    public void send(
            @RequestBody FirmbankingWebRequest webRequest
    ) {
        firmBankingRequestUseCase.requestFirmbanking(new FirmbankingRequestCommand(
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
        firmBankingRequestUseCase.requestFirmbankingByEvent(new FirmbankingRequestCommand(
                webRequest.fromBankName(),
                webRequest.fromBankAccountNumber(),
                webRequest.toBankName(),
                webRequest.toBankAccountNumber(),
                new BigDecimal(webRequest.moneyAmount())
        ));
    }
}
