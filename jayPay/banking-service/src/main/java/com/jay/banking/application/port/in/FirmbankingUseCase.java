package com.jay.banking.application.port.in;

import com.jay.banking.domain.FirmbankingRequest;

public interface FirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(FirmbankingRequestCommand command);
}
