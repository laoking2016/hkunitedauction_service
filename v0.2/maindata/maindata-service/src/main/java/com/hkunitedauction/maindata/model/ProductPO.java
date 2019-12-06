package com.hkunitedauction.maindata.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "maindata_product")
public class ProductPO {
    @Id
    private Integer id;

    private String name;

    private String description;
}