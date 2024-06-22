package com.jay.banking.adapter.out.persistence;

import com.jay.banking.domain.FirmbankingRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "firmbanking_request")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FirmbankingRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long firmbankingRequestId;

    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private BigDecimal moneyAmount;
    @Setter
    private FirmbankingRequest.FirmbankingRequestStatus firmbankingRequestStatus;

    public FirmbankingRequestEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, BigDecimal moneyAmount, FirmbankingRequest.FirmbankingRequestStatus firmbankingRequestStatus) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingRequestStatus = firmbankingRequestStatus;
    }

}
