package com.mrjeff.voucherservice.repository.impl;

import com.mrjeff.voucherservice.model.Voucher;
import com.mrjeff.voucherservice.repository.VoucherRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class VoucherRepositoryImpl implements VoucherRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Voucher findByCode(String code) {
        Query query = entityManager.createNativeQuery("SELECT v.* FROM voucher as v WHERE v.code = :code"
                , Voucher.class);
        query.setParameter("code", code);

        return (Voucher) query.getSingleResult();
    }
}
