package com.mrjeff.voucherservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
public class ParamVouchersDTO {
    @Getter
    @NotBlank(message = "El parámetro 'active' no puede estar vacío!")
    private final String active;

    @Getter
    @NotBlank(message = "El parámetro 'discount' no puede estar vacío!")
    private final String discount;
}
