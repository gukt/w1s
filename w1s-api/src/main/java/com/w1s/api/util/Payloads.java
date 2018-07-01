/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

import java.util.HashMap;

public class Payloads {
    public static Map map() {
        return new Map();
    }

    public static class Map extends HashMap<String, Object> {
        public Map put(String key, Object value) {
            super.put(key, value);
            return this;
        }
    }
}
