package com.mrjeff.voucherservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductDTO {
    private String code;

    @NotNull(message = "El precio no puede ser vacío!")
    private Float price;
}
