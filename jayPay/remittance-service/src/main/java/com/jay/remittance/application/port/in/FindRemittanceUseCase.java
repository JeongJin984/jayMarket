package com.jay.remittance.application.port.in;

import com.jay.common.UseCase;
import com.jay.remittance.domain.RemittanceRequest;

import java.util.List;

@UseCase
public interface FindRemittanceUseCase {

    List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
