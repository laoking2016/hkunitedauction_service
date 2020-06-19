package com.hkunitedauction.order.model;

import com.hkunitedauction.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "order_order")
public class OrderPO {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private Long id;

    private Integer type;

    private Integer status;

    private String name;

    private String contact;

    private String address;

    private String wechat;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "init_amount")
    private Double initAmount;

    @Column(name = "final_amount")
    private Double finalAmount;

    @Column(name = "transport_cost")
    private Double transportCost;

    private Boolean deleted;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modifed_time")
    private Date modifedTime;

    @Column(name = "deleted_time")
    private Date deletedTime;

    private String detail;

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
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return wechat
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * @param wechat
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * @return order_date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
     * @return transport_cost
     */
    public Double getTransportCost() {
        return transportCost;
    }

    /**
     * @param transportCost
     */
    public void setTransportCost(Double transportCost) {
        this.transportCost = transportCost;
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
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}