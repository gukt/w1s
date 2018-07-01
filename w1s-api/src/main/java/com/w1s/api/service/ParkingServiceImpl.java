/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.service;

import com.w1s.api.domain.Parking;
import com.w1s.api.domain.ParkingLot;
import com.w1s.api.repository.ParkingLotRepository;
import com.w1s.api.repository.ParkingRepository;
import com.w1s.api.util.ErrorCode;
import com.w1s.api.util.ParkingException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 12:19
 *
 * @author gukt
 * @version 1.0
 */
@Service
public class ParkingServiceImpl implements ParkingService {
    @Resource
    private ParkingLotRepository parkingLotRepository;

    @Resource
    private ParkingRepository parkingRepository;

    @Override
    public List<ParkingLot> getParkingLots() {
        return parkingLotRepository.findAll();
    }

    @Override
    public ParkingLot save(ParkingLot entity) {
        return parkingLotRepository.save(entity);
    }

    /**
     * 开始停车,记录用户停车信息
     *
     * @return Parking object
     */
    @Override
    public Parking beginParking(String clientId, long placeId) {
        // 检查当前是否正在停车中
        Parking parking = parkingRepository.findByClientIdAndPlaceId(clientId, placeId);
        if (parking != null) {
            throw new ParkingException(ErrorCode.DUPLICATED_PARKING, "非法的请求: 当前正在停车中,结束后才能停车");
        }

        Optional<ParkingLot> result = parkingLotRepository.findById(placeId);

        checkExistence(result);

        // find out the parking lot
        ParkingLot pl = result.get();

        if (pl.getUsed() >= pl.getAmount()) {
            throw new ParkingException(ErrorCode.NOT_ENOUGH_PARKING_SEAT, "没有足够的车位");
        }

        // increase and then save
        synchronized (pl) {
            pl.setUsed(pl.getUsed() + 1);
        }
        parkingLotRepository.save(pl);

        parking = new Parking();
        parking.setClientId(clientId);
        parking.setPlaceId(placeId);
        parking.setStartTime(new Date());

        return parkingRepository.save(parking);
    }

    @Override
    public float requestPay(String clientId, long placeId) {
        Optional<ParkingLot> result = parkingLotRepository.findById(placeId);

        checkExistence(result);

//        ParkingLot pl = result.get();
//
//        Parking parking = parkingRepository.findByClientIdAndPlaceId(clientId, placeId);

        // TODO 计算扣钱
        Random random = new Random();
        return random.nextInt(10);
    }

    @Override
    public void pay(String clientId, long placeId) {
        Optional<ParkingLot> result = parkingLotRepository.findById(placeId);

        checkExistence(result);

        ParkingLot pl = result.get();

        if (pl.getUsed() > 0) {
            synchronized (pl) {
                pl.setUsed(pl.getUsed() - 1);
            }
            parkingLotRepository.save(pl);
        } else {
            throw new ParkingException(ErrorCode.ILLEGAL_REQUEST, "非法的请求,当前没有车位在使用");
        }

        // 删除Parking记录
        parkingRepository.deleteByClientIdAndPlaceId(clientId, placeId);
    }

    private void checkExistence(Optional<ParkingLot> result) {
        if (result == null || !result.isPresent()) {
            throw new ParkingException(ErrorCode.PARKING_LOT_NOT_FOUND, "非法请求,没有这个停车场");
        }
    }
}
