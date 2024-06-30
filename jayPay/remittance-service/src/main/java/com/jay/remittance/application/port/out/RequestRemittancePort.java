package com.jay.remittance.application.port.out;

import com.jay.remittance.adaptor.out.persistence.RemittanceRequestEntity;
import com.jay.remittance.application.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {
    RemittanceRequestEntity createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequestEntity entity);
}
