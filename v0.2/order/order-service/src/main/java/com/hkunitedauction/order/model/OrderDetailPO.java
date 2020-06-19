package com.hkunitedauction.order.model;

import com.hkunitedauction.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "order_order_detail")
public class OrderDetailPO {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "context_id")
    private Long contextId;

    private String name;

    @Column(name = "init_price")
    private Double initPrice;

    private Integer quantity;

    private Double discount;

    @Column(name = "final_price")
    private Double finalPrice;

    private String description;

    private Boolean deleted;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modifed_time")
    private Date modifedTime;

    @Column(name = "deleted_time")
    private Date deletedTime;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_contact")
    private String supplierContact;

    @Column(name = "supplier_wechat")
    private String supplierWechat;

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
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return context_id
     */
    public Long getContextId() {
        return contextId;
    }

    /**
     * @param contextId
     */
    public void setContextId(Long contextId) {
        this.contextId = contextId;
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
     * @return init_price
     */
    public Double getInitPrice() {
        return initPrice;
    }

    /**
     * @param initPrice
     */
    public void setInitPrice(Double initPrice) {
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
     * @return final_price
     */
    public Double getFinalPrice() {
        return finalPrice;
    }

    /**
     * @param finalPrice
     */
    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
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
     * @return supplier_name
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return supplier_contact
     */
    public String getSupplierContact() {
        return supplierContact;
    }

    /**
     * @param supplierContact
     */
    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    /**
     * @return supplier_wechat
     */
    public String getSupplierWechat() {
        return supplierWechat;
    }

    /**
     * @param supplierWechat
     */
    public void setSupplierWechat(String supplierWechat) {
        this.supplierWechat = supplierWechat;
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