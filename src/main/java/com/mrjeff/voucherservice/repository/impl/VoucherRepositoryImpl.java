package com.mrjeff.voucherservice.repository.impl;

import com.mrjeff.voucherservice.model.Voucher;
import com.mrjeff.voucherservice.repository.VoucherRepositoryCustom;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
public class VoucherRepositoryImpl implements VoucherRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Voucher findByCode(String code) {
        Voucher result = null;

        try {
            Query query = entityManager.createNativeQuery("SELECT v.* FROM voucher as v WHERE v.code = :code"
                    , Voucher.class);
            query.setParameter("code", code);

            result = (Voucher) query.getSingleResult();
        } catch (Exception e ) {
            log.error("Error trying to retrieve the voucher with the code: {}", code, e);
        }

        return result;
    }

    @Override
    public List<Voucher> findAllByActiveAndDiscount(boolean active, String discountCode) {
        List<Voucher> result = null;

        try {
            Query query = entityManager.createNativeQuery("SELECT v.* FROM voucher as v, discount as d" +
                    " WHERE v.discount=d.id AND v.active = :active AND d.code = :discountCode", Voucher.class);
            query.setParameter("active", active);
            query.setParameter("discountCode", discountCode);

            result = query.getResultList();
        } catch (Exception e) {
            log.error("Error trying to retrieve all vouchers with: active={}, discount={}", active, discountCode, e);
        }

        return result;
    }
}
