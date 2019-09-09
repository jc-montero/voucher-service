package com.mrjeff.voucherservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
public class ParamTotalDTO {
    @Getter
    private final String voucher;

    @Getter
    @Valid
    @NotEmpty(message = "La lista de productos no puede ser vacía!")
    private final List<@NotNull ProductDTO> products;
}
