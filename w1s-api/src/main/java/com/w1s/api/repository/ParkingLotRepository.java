/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.repository;

import com.w1s.api.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 12:06
 *
 * @author gukt
 * @version 1.0
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>, JpaSpecificationExecutor<ParkingLot> {

}
