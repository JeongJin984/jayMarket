package com.jay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FirmbankingRequestRepository extends JpaRepository<FirmbankingRequestEntity, Long> {
    Optional<FirmbankingRequestEntity> findByAggregateIdentifier(final String aggregateIdentifier);
}
