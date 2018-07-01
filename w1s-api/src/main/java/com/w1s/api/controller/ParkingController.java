/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.controller;

import com.w1s.api.domain.ParkingLot;
import com.w1s.api.service.ParkingService;
import com.w1s.api.util.ApiResult;
import com.w1s.api.util.ParkingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 12:15
 *
 * @author gukt
 * @version 1.0
 */
@Controller
public class ParkingController {
    @Resource
    private ParkingService parkingService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("parking/list")
    @ResponseBody
    public Object parkingLots() {
        List<ParkingLot> parkingLots = parkingService.getParkingLots();
        return ApiResult.of(0).put("data", parkingLots);
    }

//    /**
//     * Request parking
//     *
//     * @param placeId  the id of parking lot
//     * @param clientId clientId
//     * @return ApiResult object
//     */
//    @CrossOrigin(origins = "*", maxAge = 3600)
//    @RequestMapping(value = "parking")
////    @ResponseBody
//    public void parking(@RequestParam(name = "client_id") String clientId,
//                        @RequestParam(name = "place_id") long placeId, HttpServletResponse response) {
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//
//        response.addHeader("aaa","aaa");
//        response.addHeader("bbb","bbb");
//
//        Object result;
//        try {
//            parkingService.beginParking(clientId, placeId);
//            result = ApiResult.DEFUALT_SUCCESS;
//        } catch (ParkingException e) {
//            result = ApiResult.of(e.getErrorCode(), e.getMessage());
//        }
//
//        try {
//            response.getWriter().write(JsonUtil.toJson(result));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Request parking
     *
     * @param placeId  the id of parking lot
     * @param clientId clientId
     * @return ApiResult object
     */
    @RequestMapping(value = "parking")
    @ResponseBody
    public Object parking(@RequestParam(name = "client_id") String clientId,
                          @RequestParam(name = "place_id") long placeId) {
        try {
            parkingService.beginParking(clientId, placeId);
            return ApiResult.DEFUALT_SUCCESS;
        } catch (ParkingException e) {
            return ApiResult.of(e.getErrorCode(), e.getMessage());
        }
    }

    /**
     * Request paying
     *
     * @param placeId  the id of parking lot
     * @param clientId clientId
     * @return ApiResult object
     */
    @RequestMapping("request_pay")
    @ResponseBody
    public Object requestPay(@RequestParam(name = "client_id") String clientId,
                             @RequestParam(name = "place_id") long placeId) {
        try {
            float amount = parkingService.requestPay(clientId, placeId);
            return ApiResult.of(0).put("amount", amount);
        } catch (ParkingException e) {
            return ApiResult.of(e.getErrorCode(), e.getMessage());
        }
    }

    /**
     * Request paying
     *
     * @param placeId  the id of parking lot
     * @param clientId clientId
     * @return ApiResult object
     */
    @RequestMapping("pay")
    @ResponseBody
    public Object pay(@RequestParam(name = "client_id") String clientId,
                      @RequestParam(name = "place_id") long placeId) {
        try {
            parkingService.pay(clientId, placeId);
            return ApiResult.DEFUALT_SUCCESS;
        } catch (ParkingException e) {
            return ApiResult.of(e.getErrorCode(), e.getMessage());
        }
    }

    /**
     * 提交新的停车场信息
     *
     * @return 返回Json格式数据
     */
    @RequestMapping("submit")
    @ResponseBody
    public Object submit() {

        return ApiResult.DEFUALT_SUCCESS;
    }
}
