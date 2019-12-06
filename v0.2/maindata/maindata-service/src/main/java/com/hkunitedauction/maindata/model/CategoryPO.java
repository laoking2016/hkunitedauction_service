package com.hkunitedauction.maindata.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "maindata_category")
public class CategoryPO {
    @Id
    private Integer id;

    private String name;
}