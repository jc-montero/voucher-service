package com.mrjeff.voucherservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Discount implements Serializable {
    @Id
    private long id;

    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum type;

    private int value;
}
