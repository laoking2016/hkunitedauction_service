package com.hkunitedauction.maindata.model;

import com.hkunitedauction.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "maindata_image")
public class ImagePO {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private Long id;

    private String name;

    private String path;

    private String vpath;

    private String url;

    @Column(name = "icon_name")
    private String iconName;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "icon_vpath")
    private String iconVpath;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "checkSum")
    private String checksum;

    private Boolean deleted;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modifed_time")
    private Date modifedTime;

    @Column(name = "deleted_time")
    private Date deletedTime;

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
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return vpath
     */
    public String getVpath() {
        return vpath;
    }

    /**
     * @param vpath
     */
    public void setVpath(String vpath) {
        this.vpath = vpath;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return icon_name
     */
    public String getIconName() {
        return iconName;
    }

    /**
     * @param iconName
     */
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    /**
     * @return icon_path
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * @return icon_vpath
     */
    public String getIconVpath() {
        return iconVpath;
    }

    /**
     * @param iconVpath
     */
    public void setIconVpath(String iconVpath) {
        this.iconVpath = iconVpath;
    }

    /**
     * @return icon_url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return checkSum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * @param checksum
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
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
}