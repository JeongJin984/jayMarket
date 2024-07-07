package com.jay.banking.application.port.in;

import com.jay.banking.domain.FirmbankingRequest;

public interface FirmbankingRequestUseCase {
    FirmbankingRequest requestFirmbankingCheckAccount(FirmbankingRequestCommand command);
    void requestFirmbankingCheckAccountByEvent(FirmbankingRequestCommand command);
}
