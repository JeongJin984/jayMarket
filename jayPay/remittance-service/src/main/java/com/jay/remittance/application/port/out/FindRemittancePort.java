package com.jay.remittance.application.port.out;

import com.jay.remittance.adaptor.out.persistence.RemittanceRequestEntity;
import com.jay.remittance.application.port.in.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {
    List<RemittanceRequestEntity> findRemittanceHistory(FindRemittanceCommand command);
}
