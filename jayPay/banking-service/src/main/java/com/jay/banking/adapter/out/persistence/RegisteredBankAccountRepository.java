package com.jay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountEntity, Long> {
    Optional<RegisteredBankAccountEntity> save(String bankAccountNumber);
    Optional<RegisteredBankAccountEntity> findByMembershipId(String membershipId);
}
