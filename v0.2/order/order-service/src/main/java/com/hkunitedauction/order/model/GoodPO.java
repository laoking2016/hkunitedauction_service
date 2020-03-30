package com.hkunitedauction.order.model;

import com.hkunitedauction.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "mall_good")
public class GoodPO {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private String l1;

    private String l2;

    private String l3;

    @Column(name = "init_price")
    private BigDecimal initPrice;

    private Integer quantity;

    private BigDecimal discount;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    private Boolean deleted;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modifed_time")
    private Date modifedTime;

    @Column(name = "deleted_time")
    private Date deletedTime;

    private String images;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return l1
     */
    public String getL1() {
        return l1;
    }

    /**
     * @param l1
     */
    public void setL1(String l1) {
        this.l1 = l1;
    }

    /**
     * @return l2
     */
    public String getL2() {
        return l2;
    }

    /**
     * @param l2
     */
    public void setL2(String l2) {
        this.l2 = l2;
    }

    /**
     * @return l3
     */
    public String getL3() {
        return l3;
    }

    /**
     * @param l3
     */
    public void setL3(String l3) {
        this.l3 = l3;
    }

    /**
     * @return init_price
     */
    public BigDecimal getInitPrice() {
        return initPrice;
    }

    /**
     * @param initPrice
     */
    public void setInitPrice(BigDecimal initPrice) {
        this.initPrice = initPrice;
    }

    /**
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * @return final_price
     */
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    /**
     * @param finalPrice
     */
    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * @return deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return modifed_time
     */
    public Date getModifedTime() {
        return modifedTime;
    }

    /**
     * @param modifedTime
     */
    public void setModifedTime(Date modifedTime) {
        this.modifedTime = modifedTime;
    }

    /**
     * @return deleted_time
     */
    public Date getDeletedTime() {
        return deletedTime;
    }

    /**
     * @param deletedTime
     */
    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }

    /**
     * @return images
     */
    public String getImages() {
        return images;
    }

    /**
     * @param images
     */
    public void setImages(String images) {
        this.images = images;
    }
}