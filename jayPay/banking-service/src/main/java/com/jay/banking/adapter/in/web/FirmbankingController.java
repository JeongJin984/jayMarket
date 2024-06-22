package com.jay.banking.adapter.in.web;

import com.jay.banking.application.port.in.FirmbankingRequestCommand;
import com.jay.banking.application.port.in.FirmbankingUseCase;
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

    private final FirmbankingUseCase firmBankingUseCase;

    @PostMapping("/banking/firmbanking/request")
    public void send(
            @RequestBody FirmbankingWebRequest webRequest
    ) {
        firmBankingUseCase.requestFirmbanking(new FirmbankingRequestCommand(
                webRequest.fromBankName(),
                webRequest.fromBankAccountNumber(),
                webRequest.toBankName(),
                webRequest.toBankAccountNumber(),
                new BigDecimal(webRequest.moneyAmount())
        ));
    }
}
