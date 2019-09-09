package com.mrjeff.voucherservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ResponseDTO {
    @Getter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Float result;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String error;
}
