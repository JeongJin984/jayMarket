package com.jay.remittance.adaptor.in;

import com.jay.common.WebAdapter;
import com.jay.remittance.application.port.in.RequestRemittanceCommand;
import com.jay.remittance.application.port.in.RequestRemittanceUseCase;
import com.jay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RemittanceWebController {
    private final RequestRemittanceUseCase requestRemittanceUseCase;

    @PostMapping(path = "/remittance/request")
    RemittanceRequest requestRemittance(@RequestBody RequestRemittanceRequest request) {
        RequestRemittanceCommand command = RequestRemittanceCommand.builder()
                .fromMembershipId(request.fromMembershipId())
                .toMembershipId(request.toMembershipId())
                .toBankName(request.toBankName())
                .toBankAccountNumber(request.toBankName())
                .amount(new BigDecimal(request.amount()))
                .remittanceType(request.remittanceType())
                .build();

        return requestRemittanceUseCase.requestRemittance(command);
    }
}
