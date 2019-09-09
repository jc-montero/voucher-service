package com.mrjeff.voucherservice.model;

import com.mrjeff.voucherservice.dto.VoucherDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Voucher implements Serializable {
    @Id
    private long id;

    private String code;

    private boolean active;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discount", nullable = false)
    private Discount discount;

    public VoucherDTO toDTO() {
        return VoucherDTO.builder().code(code).active(active?"ACTIVO":"DESACTIVO").discountCode(discount.getCode())
                .build();
    }
}
