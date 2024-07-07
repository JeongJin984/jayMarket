package com.jay.money.adaptor.out.persistence;

import com.jay.common.PersistenceAdapter;
import com.jay.money.application.port.out.ChargeMemberMoneyPort;
import com.jay.money.application.port.out.CreateMemberMoneyPort;
import com.jay.money.application.port.out.GetMemberMoneyPort;
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
public class ChangingMemberMoneyPersistenceAdaptor implements ChargeMemberMoneyPort, CreateMemberMoneyPort, GetMemberMoneyPort {
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
                            moneyAmount,
                            ""
                    )
            );
        }
    }

    @Override
    public void createMemberMoney(MemberMoney.MembershipId membershipId, MemberMoney.MemberMoneyAggregateIdentifier identifier) {
        MemberMoneyEntity entity = new MemberMoneyEntity(
                membershipId.membershipId(),
                new BigDecimal("0"),
                identifier.aggregateIdentifier()
        );
        memberMoneyEntityRepository.save(entity);
    }

    @Override
    public MemberMoneyEntity getMemberMoney(MemberMoney.MembershipId membershipId) {
        List<MemberMoneyEntity> entities = memberMoneyEntityRepository.findByMembershipId(membershipId.membershipId());
        if(entities.isEmpty()) {
            MemberMoneyEntity entity = new MemberMoneyEntity(
                    membershipId.membershipId(),
                    new BigDecimal("0"),
                    ""
            );
            entity = memberMoneyEntityRepository.save(entity);
            return entity;
        }
        return entities.get(0);
    }
}
