package com.jay.banking.adapter.out.external;

import com.jay.banking.application.port.out.FirmbankingServicePort;
import com.jay.common.ExternalSystemAdapter;
import org.springframework.stereotype.Service;

@Service
@ExternalSystemAdapter
public class FirmbankingServiceAdaptor implements FirmbankingServicePort {
    @Override
    public FirmbankingServiceResponse send(FirmbankingServiceRequest request) {
        return new FirmbankingServiceResponse(0);
    }
}
