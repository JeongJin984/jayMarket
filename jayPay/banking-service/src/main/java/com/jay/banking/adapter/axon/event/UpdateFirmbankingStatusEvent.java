package com.jay.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Getter
public class UpdateFirmbankingStatusEvent {
    private int firmbankingStatus;
}
