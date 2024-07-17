package com.jay.banking.application.port.out;

import com.jay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.jay.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}