package com.jay.money.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMoneyEntityRepository extends JpaRepository<MemberMoneyEntity, Long> {
    List<MemberMoneyEntity> findByMembershipId(Long membershipId);
}
