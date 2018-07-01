/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

public class JsonDeserializeException extends JsonProcessException {
    public JsonDeserializeException() {}

    public JsonDeserializeException(String message) {
        super(message);
    }

    public JsonDeserializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
