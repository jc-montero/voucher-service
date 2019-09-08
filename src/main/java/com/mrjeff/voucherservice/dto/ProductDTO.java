package com.mrjeff.voucherservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProductDTO {
    private String code;
    private Float price;
}
