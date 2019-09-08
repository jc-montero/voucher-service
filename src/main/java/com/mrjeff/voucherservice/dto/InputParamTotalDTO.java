package com.mrjeff.voucherservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InputParamTotalDTO {
    @Getter
    private final String voucher;

    @Getter
    private final List<ProductDTO> products;
}
