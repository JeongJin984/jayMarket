package com.jay.remittance.application.service;

import com.jay.remittance.adaptor.out.persistence.RemittanceRequestMapper;
import com.jay.remittance.application.port.in.FindRemittanceCommand;
import com.jay.remittance.application.port.in.FindRemittanceUseCase;
import com.jay.remittance.application.port.out.FindRemittancePort;
import com.jay.remittance.application.port.out.membership.MembershipStatus;
import com.jay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRemittanceService implements FindRemittanceUseCase {
    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        return findRemittancePort.findRemittanceHistory(command).stream().map(mapper::mapToDomainEntity).toList();
    }
}
