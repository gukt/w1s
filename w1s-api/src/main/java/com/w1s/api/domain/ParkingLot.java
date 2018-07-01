/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parking_lots")
@JsonInclude(Include.NON_NULL)
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 经度
     */
    private Float longitude;

    /**
     * 纬度
     */
    private Float latitude;

    private Integer type = 0; // 0: 停车场(默认), 1:加油站, 2:厕所, 3:便利店

    /**
     * 地址信息的字符串名称
     */
    private String address = "";
    private Integer amount = 0; // 车位数
    private Integer used = 0; // 已使用车位数
    private Integer distance = 0;
    /**
     * 白天价格
     */
    private Float price1;
    /**
     * 夜间价格
     */
    private Float price2;
    /**
     * 包月价格
     */
    private Float price3;

    //    @JsonInclude(Include.NON_NULL)
    private String images;

    @JsonIgnore
    private Date createTime;

    //    @JsonInclude(Include.NON_NULL)
    private Date updateTime;

    @JsonIgnore
    private Integer status = 0;

    public static ParkingLot of(Long id) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(id);
        return parkingLot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAmount() {
        return amount == null ? Integer.valueOf(0) : amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUsed() {
        return used == null ? Integer.valueOf(0) : used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Float getPrice1() {
        return price1 == null ? Float.valueOf(0L) : price1;
    }

    public void setPrice1(Float price1) {
        this.price1 = price1;
    }

    public Float getPrice2() {
        return price2 == null ? Float.valueOf(0F) : price2;
    }

    public void setPrice2(Float price2) {
        this.price2 = price2;
    }

    public Float getPrice3() {
        return price3 == null ? Float.valueOf(0F) : price3;
    }

    public void setPrice3(Float price3) {
        this.price3 = price3;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
