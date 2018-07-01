/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 16:56
 *
 * @author gukt
 * @version 1.0
 */
public class ErrorCode {
    /**
     * 停车场记录没有找到
     */
    public static final int PARKING_LOT_NOT_FOUND = 1001;

    /**
     * 停车位不足
     */
    public static final int NOT_ENOUGH_PARKING_SEAT = 1002;

    /**
     * 非法的停车位释放请求
     */
    public static final int ILLEGAL_REQUEST = 1003;
    public static final int DUPLICATED_PARKING = 1004;
}
