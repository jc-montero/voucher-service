package com.mrjeff.voucherservice.exception;


public class BusinessException extends Exception {
    public static final String ERROR_100_INACTIVE_VOUCHER = "El cupon :voucher ha caducado. Utilice un cupon válido";
    public static final String ERROR_101_PRODUCTS_MANDATORY = "No se han recibido productos. Debe enviar una lista de productos";

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}