package com.jay.banking.application.port.out;

import com.jay.banking.adapter.out.external.FirmbankingServiceRequest;
import com.jay.banking.adapter.out.external.FirmbankingServiceResponse;

public interface FirmbankingServicePort {
    FirmbankingServiceResponse send(FirmbankingServiceRequest request);
}
