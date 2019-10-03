package com.mrjeff.voucherservice.repository;

import com.mrjeff.voucherservice.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);

    @Query("select v from Voucher v where v.active = :active and v.discount.code = :discountCode")
    List<Voucher> findByActiveAndDiscount(boolean active, String discountCode);
}
