package com.jay.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipEntityRepository extends JpaRepository<MembershipEntity, Long> {
}
