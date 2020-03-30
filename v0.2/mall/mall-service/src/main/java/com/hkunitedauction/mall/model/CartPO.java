package com.hkunitedauction.mall.model;

import com.hkunitedauction.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "mall_cart")
public class CartPO {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private Long id;

    private String buyer;

    @Column(name = "init_amount")
    private Double initAmount;

    private Double discount;

    @Column(name = "final_amount")
    private Double finalAmount;

    private Boolean deleted;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modifed_time")
    private Date modifedTime;

    @Column(name = "deleted_time")
    private Date deletedTime;

    private String items;

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
     * @return buyer
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * @param buyer
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * @return init_amount
     */
    public Double getInitAmount() {
        return initAmount;
    }

    /**
     * @param initAmount
     */
    public void setInitAmount(Double initAmount) {
        this.initAmount = initAmount;
    }

    /**
     * @return discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return final_amount
     */
    public Double getFinalAmount() {
        return finalAmount;
    }

    /**
     * @param finalAmount
     */
    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
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
     * @return items
     */
    public String getItems() {
        return items;
    }

    /**
     * @param items
     */
    public void setItems(String items) {
        this.items = items;
    }
}