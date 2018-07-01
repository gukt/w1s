/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.service;

import com.w1s.api.domain.Parking;
import com.w1s.api.domain.ParkingLot;

import java.util.List;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 12:18
 *
 * @author gukt
 * @version 1.0
 */
public interface ParkingService {
    List<ParkingLot> getParkingLots();

    ParkingLot save(ParkingLot entity);

    Parking beginParking(String clientId, long placeId);

    float requestPay(String clientId, long placeId);

    void pay(String clientId, long placeId);
}
