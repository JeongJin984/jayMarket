package com.jay.remittance.application.service;

import com.jay.remittance.adaptor.out.persistence.RemittanceRequestEntity;
import com.jay.remittance.adaptor.out.persistence.RemittanceRequestMapper;
import com.jay.remittance.application.port.in.RequestRemittanceCommand;
import com.jay.remittance.application.port.in.RequestRemittanceUseCase;
import com.jay.remittance.application.port.out.RequestRemittancePort;
import com.jay.remittance.application.port.out.banking.BankingPort;
import com.jay.remittance.application.port.out.membership.MembershipPort;
import com.jay.remittance.application.port.out.membership.MembershipStatus;
import com.jay.remittance.application.port.out.money.MemberMoneyInfo;
import com.jay.remittance.application.port.out.money.MemberMoneyPort;
import com.jay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RequestRemittanceService implements RequestRemittanceUseCase {
    private final RequestRemittancePort requestRemittancePort;
    private final RemittanceRequestMapper mapper;
    private final MembershipPort membershipPort;
    private final MemberMoneyPort memberMoneyPort;
    private final BankingPort bankingPort;

    @Override
    public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {
        RemittanceRequestEntity entity = requestRemittancePort.createRemittanceRequestHistory(command);

        MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
        if(!membershipStatus.isValid()) {
            return null;
        }

        MemberMoneyInfo moneyInfo = memberMoneyPort.getMoneyInfo(command.getFromMembershipId());
        if(moneyInfo.balance().compareTo(command.getAmount()) < 0) {
            BigDecimal rechargeAmount = command.getAmount().subtract(moneyInfo.balance());
            boolean moneyResult = memberMoneyPort.requestMemberMoneyRecharge(command.getFromMembershipId(), rechargeAmount);
            if(!moneyResult) { return null; }
        }

        if(command.getRemittanceType() == 0) {
            boolean remittanceResult1;
            boolean remittanceResult2;

            remittanceResult1 = memberMoneyPort.requestMemberMoneyConsume(command.getFromMembershipId(), command.getAmount());
            remittanceResult2 = memberMoneyPort.requestMemberMoneyCharge(command.getToMembershipId(), command.getAmount());

            if(!remittanceResult1 || !remittanceResult2) {
                return null;
            }
        } else {
            boolean remittanceResult = bankingPort.requestFirmbanking(command.getToBankName(), command.getToBankAccountNumber(), command.getAmount());
            if(!remittanceResult) {
                return null;
            }
        }

        entity.setRemittanceStatus("success");
        boolean result = requestRemittancePort.saveRemittanceRequestHistory(entity);
        if(!result) {
            return null;
        }

        return mapper.mapToDomainEntity(entity);
    }
}
