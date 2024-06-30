package com.jay.remittance.application.port.in;

import com.jay.common.UseCase;
import com.jay.remittance.domain.RemittanceRequest;

@UseCase
public interface RequestRemittanceUseCase {
    RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
