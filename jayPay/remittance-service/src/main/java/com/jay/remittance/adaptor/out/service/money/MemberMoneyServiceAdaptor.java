package com.jay.remittance.adaptor.out.service.money;

import com.jay.common.ExternalSystemAdapter;
import com.jay.remittance.application.port.out.money.MemberMoneyInfo;
import com.jay.remittance.application.port.out.money.MemberMoneyPort;

import java.math.BigDecimal;

@ExternalSystemAdapter
public class MemberMoneyServiceAdaptor implements MemberMoneyPort {

    @Override
    public MemberMoneyInfo getMoneyInfo(String membershipId) {
        return null;
    }

    @Override
    public boolean requestMemberMoneyRecharge(String membershipId, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean requestMemberMoneyCharge(String membershipId, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean requestMemberMoneyConsume(String membershipId, BigDecimal amount) {
        return false;
    }
}
