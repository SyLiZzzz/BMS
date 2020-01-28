package com.demo.bms.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Drink implements Serializable {
    private Integer drinkId;

    private String drinkName;

    private String drinkPicture;

    private BigDecimal price;

    private String explain;

    private Integer parentId;

    private Date createTimesd;

    private Date updateTime;

    private Integer isDeleted;

    private Integer version;

    private Integer score;

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName == null ? null : drinkName.trim();
    }

    public String getDrinkPicture() {
        return drinkPicture;
    }

    public void setDrinkPicture(String drinkPicture) {
        this.drinkPicture = drinkPicture == null ? null : drinkPicture.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTimesd() {
        return createTimesd;
    }

    public void setCreateTimesd(Date createTimesd) {
        this.createTimesd = createTimesd;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}