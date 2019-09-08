package com.mrjeff.voucherservice.model;

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
}
