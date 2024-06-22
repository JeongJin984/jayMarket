package com.jay.banking.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registered_bank_account")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredBankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registeredBankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private Boolean linkedStatusIsValid;

    public RegisteredBankAccountEntity(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    @Override
    public String toString() {
        return "RegisteredBankAccountEntity{" +
                "registeredBankAccountId=" + registeredBankAccountId +
                ", membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                '}';
    }
}
