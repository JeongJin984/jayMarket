package com.jay.remittance.application.port.out.money;

import java.math.BigDecimal;

public interface MemberMoneyPort {
    MemberMoneyInfo getMoneyInfo(String membershipId);
    boolean requestMemberMoneyRecharge(String membershipId, BigDecimal amount);
    boolean requestMemberMoneyCharge(String membershipId, BigDecimal amount);
    boolean requestMemberMoneyConsume(String membershipId, BigDecimal amount);
}
