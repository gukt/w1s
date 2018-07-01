/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.test.service;

import com.w1s.api.domain.ParkingLot;
import com.w1s.api.service.ParkingService;
import com.w1s.api.test.common.TestBase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 15:28
 *
 * @author gukt
 * @version 1.0
 */
public class ParkingServiceTests extends TestBase {

    @Resource
    private ParkingService parkingService;

    @Test
    public void testCreateParkingLots() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            ParkingLot entity = new ParkingLot();
            entity.setLongitude(random.nextFloat());
            entity.setLatitude(random.nextFloat());
            entity.setCreateTime(new Date());
            parkingService.save(entity);
        }
    }

    @Test
    public void testRetrieveParkingLots() {
        List<ParkingLot> lots = parkingService.getParkingLots();
        System.out.println(lots);
    }
}
