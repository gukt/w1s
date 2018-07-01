/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

public abstract class JsonProcessException extends RuntimeException {
    public JsonProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
