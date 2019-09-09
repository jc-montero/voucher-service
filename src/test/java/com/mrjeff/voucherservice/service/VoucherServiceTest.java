package com.mrjeff.voucherservice.service;

import com.mrjeff.voucherservice.dto.ProductDTO;
import com.mrjeff.voucherservice.dto.VoucherDTO;
import com.mrjeff.voucherservice.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoucherServiceTest {

    @Autowired
    private VoucherService service;

    private List<ProductDTO> products;

    @Before
    public void init() {
        products = Arrays.asList(
                ProductDTO.builder().code("CAMISA").price(40f).build(),
                ProductDTO.builder().code("PANTALON").price(30f).build(),
                ProductDTO.builder().code("CORBATA").price(20f).build(),
                ProductDTO.builder().code("AMERICANA").price(60f).build()
        );
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGetTotalAmountWithoutVoucher() throws BusinessException {
        Float result = service.getTotalAmount(null, products);
        Assert.assertEquals(150f, result, 0.001f);

        result = service.getTotalAmount("CUPON-Z", products);
        Assert.assertEquals(150f, result, 0.001f);
    }

    @Test
    public void testGetTotalAmountWithInactiveVoucher() throws BusinessException {
        exceptionRule.expect(BusinessException.class);
        exceptionRule.expectMessage(StringUtils.replace(BusinessException.ERROR_100_INACTIVE_VOUCHER
                , ":voucher", "CUPON-G"));
        Float result = service.getTotalAmount("CUPON-G", products);
    }

    @Test
    public void testGetTotalAmountWithFreeVoucher() throws BusinessException {
        Float result = service.getTotalAmount("CUPON-A", products);
        Assert.assertEquals(0f, result, 0.001f);
    }

    @Test
    public void testGetTotalAmountWithFixVoucher() throws BusinessException {
        Float result = service.getTotalAmount("CUPON-D", products);
        Assert.assertEquals(140f, result, 0.001f);
    }

    @Test
    public void testGetTotalAmountWithPercentageVoucher() throws BusinessException {
        Float result = service.getTotalAmount("CUPON-E", products);
        Assert.assertEquals(142.5f, result, 0.001f);
    }

    @Test
    public void testGetVouchers() {
        List<VoucherDTO> result = service.getVouchers(true, "GRATIS");
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());

        result = service.getVouchers(false, "FIJO-5");
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}