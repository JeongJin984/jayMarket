package com.jay.remittance.adaptor.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "request_remittance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemittanceRequestEntity {
    @Id
    @GeneratedValue
    private Long remittanceRequestId;
    private String fromMembershipId;
    private String toMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int remittanceType;
    private BigDecimal amount;
    private String remittanceStatus;
}
