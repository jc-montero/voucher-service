package com.mrjeff.voucherservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class VoucherDTO {
    @Getter
    private final String code;

    @Getter
    private final String active;

    @Getter
    private final String discountCode;
}
