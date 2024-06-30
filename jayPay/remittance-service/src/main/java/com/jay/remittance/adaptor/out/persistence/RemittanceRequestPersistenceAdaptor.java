package com.jay.remittance.adaptor.out.persistence;

import com.jay.common.PersistenceAdapter;
import com.jay.remittance.application.port.in.FindRemittanceCommand;
import com.jay.remittance.application.port.in.RequestRemittanceCommand;
import com.jay.remittance.application.port.out.FindRemittancePort;
import com.jay.remittance.application.port.out.RequestRemittancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@PersistenceAdapter
@Service
@RequiredArgsConstructor
public class RemittanceRequestPersistenceAdaptor implements RequestRemittancePort, FindRemittancePort {
    private final RemittanceRequestEntityRepository remittanceRequestRepository;

    @Override
    public List<RemittanceRequestEntity> findRemittanceHistory(FindRemittanceCommand command) {
        return remittanceRequestRepository.findAllByFromMembershipId(command.getMembershipId());
    }

    @Override
    public RemittanceRequestEntity createRemittanceRequestHistory(RequestRemittanceCommand command) {
        return remittanceRequestRepository.save(RemittanceRequestEntity.builder()
                .fromMembershipId(command.getFromMembershipId())
                .toMembershipId(command.getToMembershipId())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .amount(command.getAmount())
                .remittanceType(command.getRemittanceType())
                .build());
    }

    @Override
    public boolean saveRemittanceRequestHistory(RemittanceRequestEntity entity) {
        remittanceRequestRepository.save(entity);
        return true;
    }
}
