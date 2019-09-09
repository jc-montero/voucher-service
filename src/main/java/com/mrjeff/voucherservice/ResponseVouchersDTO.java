package com.mrjeff.voucherservice;

import com.mrjeff.voucherservice.dto.VoucherDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class ResponseVouchersDTO {
    @Getter
    private final List<VoucherDTO> result;

    @Getter
    private final String error;
}
