package com.jay.remittance.adaptor.out.service.membership;

public record Membership (
        String membershipId,
        String name,
        String email,
        String address,
        boolean isValid,
        boolean isCorp
) {
    @Override
    public String toString() {
        return "Membership from Remittance {" +
                "membershipId='" + membershipId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isValid=" + isValid +
                ", isCorp=" + isCorp +
                '}';
    }
}
