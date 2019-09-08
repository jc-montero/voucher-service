package com.mrjeff.voucherservice.repository;

import com.mrjeff.voucherservice.model.Voucher;

public interface VoucherRepositoryCustom {
    Voucher findByCode(String code);
}
