package com.mrjeff.voucherservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParamVouchersDTO {
    @Getter
    private final String active;

    @Getter
    private final String discount;
}
