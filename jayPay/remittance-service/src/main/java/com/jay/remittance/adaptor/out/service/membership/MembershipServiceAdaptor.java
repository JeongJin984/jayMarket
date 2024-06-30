package com.jay.remittance.adaptor.out.service.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.common.CommonHttpClient;
import com.jay.common.ExternalSystemAdapter;
import com.jay.remittance.application.port.out.membership.MembershipPort;
import com.jay.remittance.application.port.out.membership.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdaptor implements MembershipPort {
    private final CommonHttpClient membershipServiceHttpClient;

    @Value("${service.membership.url}")
    private String membershipServiceEndpoint;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String buildUrl = String.join("/", this.membershipServiceEndpoint, "membership", membershipId);
        try {
            String jsonResponse = membershipServiceHttpClient.sendGetRequest(buildUrl).body();

            Membership membership = mapper.readValue(jsonResponse, Membership.class);
            if (membership.isValid()){
                return new MembershipStatus(membership.membershipId(), true);
            } else{
                return new MembershipStatus(membership.membershipId(), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
