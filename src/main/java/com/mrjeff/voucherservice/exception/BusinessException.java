package com.mrjeff.voucherservice.exception;


public class BusinessException extends Exception {
    public static final String ERROR_100_INACTIVE_VOUCHER = "El cupon :voucher ha caducado. Utilice un cupon válido";

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
