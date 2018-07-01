/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.Strings;

import java.util.HashMap;

@JsonInclude(Include.NON_EMPTY)
public class ApiResult extends HashMap<String, Object> {
    public ApiResult() {
        put("ret", 0);
    }

    @Override
    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static ApiResult of(int ret) {
        return of(ret, null);
    }

    public static ApiResult of(int ret, String msg) {
        ApiResult result = new ApiResult();
        result.put("ret", ret);

        if (!Strings.isNullOrEmpty(msg)) {
            result.put("msg", msg);
        }

        return result;
    }

//    public static ApiResult of(Object result) {
//        return of(0).put("data", JsonUtil.toJson(result));
//    }

    /**
     * 默认成功
     */
    public final static ApiResult DEFUALT_SUCCESS = of(0, "ok");

    /**
     * 默认失败
     */
    public final static ApiResult DEFUALT_FAILED = of(-1, "failed");
}
