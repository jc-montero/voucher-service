package com.mrjeff.voucherservice.service.impl;

import com.mrjeff.voucherservice.dto.ProductDTO;
import com.mrjeff.voucherservice.dto.VoucherDTO;
import com.mrjeff.voucherservice.exception.BusinessException;
import com.mrjeff.voucherservice.model.Discount;
import com.mrjeff.voucherservice.model.DiscountTypeEnum;
import com.mrjeff.voucherservice.model.Voucher;
import com.mrjeff.voucherservice.repository.VoucherRepository;
import com.mrjeff.voucherservice.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository repository;

    @Override
    public Float getTotalAmount(String code, List<ProductDTO> products) throws BusinessException {
        Voucher voucher = null;

        Float total = products.stream().map(ProductDTO::getPrice).reduce(0f, Float::sum);

        if (StringUtils.isNotBlank(code)) {
            voucher = repository.findByCode(code);
        }

        if (voucher != null) {
            Discount discount = voucher.getDiscount();

            if (voucher.isActive()) {
                if (DiscountTypeEnum.FIJO.equals(discount.getType())) {
                    total -= discount.getValue();
                } else if (DiscountTypeEnum.PORCENTAJE.equals(discount.getType())) {
                    total = total - (total * discount.getValue() / 100);
                }
            } else {
                throw new BusinessException(StringUtils.replace(BusinessException.ERROR_100_INACTIVE_VOUCHER
                        , ":voucher", code));
            }
        }

        return total > 0f ? total : 0f;
    }

    @Override
    public List<VoucherDTO> getVouchers(boolean active, String discountCode) {
        List<Voucher> vouchers = repository.findAllByActiveAndDiscount(active, discountCode);

        return vouchers.stream().map(Voucher::toDTO).collect(Collectors.toList());
    }
}
