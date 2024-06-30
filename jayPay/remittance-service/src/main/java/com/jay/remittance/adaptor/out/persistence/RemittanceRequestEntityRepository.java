package com.jay.remittance.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemittanceRequestEntityRepository extends JpaRepository<RemittanceRequestEntity, Long> {
    List<RemittanceRequestEntity> findAllByFromMembershipId(String membershipId);
}
