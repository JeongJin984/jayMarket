package com.jay.banking.application.port.in;

import com.jay.banking.domain.FirmbankingRequest;

public interface FirmbankingRequestUseCase {
    FirmbankingRequest requestFirmbanking(FirmbankingRequestCommand command);

    void requestFirmbankingByEvent(FirmbankingRequestCommand command);
}
