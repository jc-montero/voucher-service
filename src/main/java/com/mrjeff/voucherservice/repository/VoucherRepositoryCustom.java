package com.mrjeff.voucherservice.repository;

import com.mrjeff.voucherservice.model.Voucher;

import java.util.List;

public interface VoucherRepositoryCustom {
    Voucher findByCode(String code);
    List<Voucher> findAllByActiveAndDiscount(boolean active, String discountCode);
}
