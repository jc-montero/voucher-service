package com.mrjeff.voucherservice.service;

import com.mrjeff.voucherservice.dto.ProductDTO;
import com.mrjeff.voucherservice.exception.BusinessException;

import java.util.List;

public interface VoucherService {
    Float getTotalAmount(String code, List<ProductDTO> products) throws BusinessException;
}
