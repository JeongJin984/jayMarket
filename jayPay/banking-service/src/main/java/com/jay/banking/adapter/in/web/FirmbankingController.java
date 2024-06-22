package com.jay.banking.adapter.in.web;

import com.jay.banking.application.port.in.FirmbankingUseCase;
import com.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FirmbankingController {

    private final FirmbankingUseCase firmBankingUseCase;

    @PostMapping("/banking/firmbanking/request")
    public void
}
