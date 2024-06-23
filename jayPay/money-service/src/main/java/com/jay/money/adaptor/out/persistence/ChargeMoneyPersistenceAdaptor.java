package com.jay.money.adaptor.out.persistence;

import com.jay.common.PersistenceAdapter;
import com.jay.money.application.port.out.ChargeMoneyPort;
import com.jay.money.domain.ChargeMoneyRequest;
import com.jay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@PersistenceAdapter
@RequiredArgsConstructor
public class ChargeMoneyPersistenceAdaptor implements ChargeMoneyPort {
    private final MemberMoneyWebRequestEntityRepository memberMoneyWebRequestEntityRepository;
    private final MemberMoneyEntityRepository memberMoneyEntityRepository;

    @Override
    public MemberMoneyWebRequestEntity createChargeMoneyRequest(ChargeMoneyRequest.TargetMembershipId targetMembershipId, ChargeMoneyRequest.ActionType actionType, ChargeMoneyRequest.MoneyAmount moneyAmount, ChargeMoneyRequest.RequestStatus requestStatus, ChargeMoneyRequest.Uuid uuid) {
        return memberMoneyWebRequestEntityRepository.save(
                new MemberMoneyWebRequestEntity(
                        targetMembershipId.targetMembershipId(),
                        actionType,
                        moneyAmount.moneyAmount(),
                        LocalDateTime.now(),
                        requestStatus.requestStatus(),
                        UUID.randomUUID().toString()
                )
        );
    }

    @Override
    public MemberMoneyEntity chargeMoney(MemberMoney.MembershipId membershipId, BigDecimal moneyAmount) {
        List<MemberMoneyEntity> memberMoneyList = memberMoneyEntityRepository.findByMembershipId(membershipId.membershipId());
        try {
            memberMoneyList.get(0).chargeBalance(moneyAmount);
            return memberMoneyList.get(0);
        } catch (Exception e) {
            return memberMoneyEntityRepository.save(
                    new MemberMoneyEntity(
                            membershipId.membershipId(),
                            moneyAmount
                    )
            );
        }
    }
}
