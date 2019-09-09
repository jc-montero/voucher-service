package com.mrjeff.voucherservice.repository;

import com.mrjeff.voucherservice.model.Voucher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoucherRepositoryTest {

    @Autowired
    private VoucherRepository repository;

    @Test
    public void findAll() {
        List<Voucher> vouchers = repository.findAll();
        Assert.assertEquals(vouchers.size(), 7);
    }

    @Test
    public void findByCode() {
        Voucher voucher = repository.findByCode("CUPON-A");
        Assert.assertNotNull(voucher);
    }

    @Test
    public void findAllByActiveAndDiscount() {
        List<Voucher> vouchers = repository.findAllByActiveAndDiscount(true, "GRATIS");
        Assert.assertNotNull(vouchers);
        Assert.assertEquals(2, vouchers.size());

        vouchers = repository.findAllByActiveAndDiscount(false, "FIJO-5");
        Assert.assertNotNull(vouchers);
        Assert.assertEquals(0, vouchers.size());
    }
}