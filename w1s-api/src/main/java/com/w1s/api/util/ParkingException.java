/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 17:09
 *
 * @author gukt
 * @version 1.0
 */
public class ParkingException extends RuntimeException {
    private int errorCode;

    public ParkingException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ParkingException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ParkingException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
