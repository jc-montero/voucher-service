package com.mrjeff.voucherservice.rest;

import com.mrjeff.voucherservice.dto.InputParamTotalDTO;
import com.mrjeff.voucherservice.dto.ResponseDTO;
import com.mrjeff.voucherservice.exception.BusinessException;
import com.mrjeff.voucherservice.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoucherController {

    @Autowired
    private VoucherService service;

    @PostMapping(value = "/voucher/total", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseDTO getTotalAmount(@RequestBody final InputParamTotalDTO payload) {
        ResponseDTO result;

        if (isValid(payload)) {
            try {
                Float totalAmount = service.getTotalAmount(payload.getVoucher(), payload.getProducts());
                result = ResponseDTO.builder().result(totalAmount).build();
            } catch (BusinessException e) {
                result = ResponseDTO.builder().error(e.getMessage()).build();
            }
        } else {
            result = ResponseDTO.builder().error(BusinessException.ERROR_101_PRODUCTS_MANDATORY).build();
        }

        return result;
    }

    private boolean isValid(InputParamTotalDTO payload) {
        return payload.getProducts() != null && payload.getProducts().size() > 0;
    }
}
