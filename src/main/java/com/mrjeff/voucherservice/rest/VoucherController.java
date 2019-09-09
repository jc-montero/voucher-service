package com.mrjeff.voucherservice.rest;

import com.mrjeff.voucherservice.ResponseVouchersDTO;
import com.mrjeff.voucherservice.dto.ParamTotalDTO;
import com.mrjeff.voucherservice.dto.ParamVouchersDTO;
import com.mrjeff.voucherservice.dto.ResponseDTO;
import com.mrjeff.voucherservice.dto.VoucherDTO;
import com.mrjeff.voucherservice.exception.BusinessException;
import com.mrjeff.voucherservice.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vouchers-rest")
public class VoucherController {

    @Autowired
    private VoucherService service;

    @PostMapping(value = "/total", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseDTO getTotalAmount(@RequestBody final ParamTotalDTO payload) {
        ResponseDTO response;

        if (isValid(payload)) {
            try {
                Float totalAmount = service.getTotalAmount(payload.getVoucher(), payload.getProducts());
                response = ResponseDTO.builder().result(totalAmount).build();
            } catch (BusinessException e) {
                response = ResponseDTO.builder().error(e.getMessage()).build();
            }
        } else {
            response = ResponseDTO.builder().error(BusinessException.ERROR_101_PRODUCTS_MANDATORY).build();
        }

        return response;
    }

    @PostMapping(value = "/vouchers", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVouchersDTO getVouchers(@RequestBody final ParamVouchersDTO payload) {
        ResponseVouchersDTO response;

        if (isValid(payload)) {
            boolean active = "true".equalsIgnoreCase(payload.getActive());
            List<VoucherDTO> vouchers = service.getVouchers(active, payload.getDiscount());
            response = ResponseVouchersDTO.builder().result(vouchers).build();
        } else {
            response = ResponseVouchersDTO.builder().error(BusinessException.ERROR_200_INVALID_PARAMS).build();
        }

        return response;
    }

    private boolean isValid(ParamTotalDTO payload) {
        return payload.getProducts() != null && payload.getProducts().size() > 0;
    }

    private boolean isValid(ParamVouchersDTO payload) {
        return payload.getActive() != null && payload.getDiscount() != null;
    }
}
