package com.mrjeff.voucherservice.repository.impl;

import com.mrjeff.voucherservice.model.Voucher;
import com.mrjeff.voucherservice.repository.VoucherRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
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
}
