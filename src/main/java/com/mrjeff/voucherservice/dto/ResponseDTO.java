package com.mrjeff.voucherservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ResponseDTO {
    @Getter
    private final Float result;

    @Getter
    private final String error;
}
