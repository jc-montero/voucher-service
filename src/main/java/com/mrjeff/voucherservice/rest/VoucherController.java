package com.mrjeff.voucherservice.rest;

import com.mrjeff.voucherservice.dto.ParamTotalDTO;
import com.mrjeff.voucherservice.dto.ParamVouchersDTO;
import com.mrjeff.voucherservice.dto.ResponseDTO;
import com.mrjeff.voucherservice.dto.VoucherDTO;
import com.mrjeff.voucherservice.exception.BusinessException;
import com.mrjeff.voucherservice.exception.ValidationError;
import com.mrjeff.voucherservice.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vouchers-rest")
public class VoucherController {

    @Autowired
    private VoucherService service;

    @PostMapping(value = "/total", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseDTO getTotalAmount(@Valid @RequestBody final ParamTotalDTO payload) {
        ResponseDTO response;

        try {
            Float totalAmount = service.getTotalAmount(payload.getVoucher(), payload.getProducts());
            response = ResponseDTO.builder().result(totalAmount).build();
        } catch (BusinessException e) {
            response = ResponseDTO.builder().error(e.getMessage()).build();
        }

        return response;
    }

    @PostMapping(value = "/vouchers", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<VoucherDTO> getVouchers(@Valid @RequestBody final ParamVouchersDTO payload) {
        boolean active = "true".equalsIgnoreCase(payload.getActive());

        return service.getVouchers(active, payload.getDiscount());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException e) {
        return createValidationError(e);
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        Errors errors = e.getBindingResult();
        List<String> errorMessages = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        return ValidationError.builder().errorMessage("Errores de validación. " + errors.getErrorCount() + " error(es)")
                .errors(errorMessages).build();
    }
}
