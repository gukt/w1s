/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Strings;

import javax.annotation.Nullable;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by gukt(29283212@qq.com) on 2018/6/30 16:31
 *
 * @author gukt
 * @version 1.0
 */
public class JsonUtil {
    public static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule());
        objectMapper.setSerializationInclusion(Include.NON_NULL); // null 不参与转换
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static String toJson(@Nullable Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("将对象序列化成JSON字符串时发生解析异常", e);
        }
    }

    public static <T> T toBean(@Nullable String json, Class<T> type) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, type);
        } catch (JsonParseException e) {
            throw new JsonDeserializeException("解析异常", e);
        } catch (JsonMappingException e) {
            throw new JsonDeserializeException("字段映射异常", e);
        } catch (IOException e) {
            throw new JsonDeserializeException("IO异常", e);
        }
    }

    public static <T> T toBean(@Nullable String json, Class<T> type, T defaultValue) {
        T bean = toBean(json, type);
        return bean == null ? defaultValue : bean;
    }

    @SuppressWarnings("unchecked")
    public static <T> T toBean(@Nullable String json, TypeReference<T> valueTypeRef) {
        try {
            if (!Strings.isNullOrEmpty(json)) {
                return (T) objectMapper.readValue(json, valueTypeRef);
            }
            return null;
        } catch (JsonParseException e) {
            throw new JsonDeserializeException("解析异常", e);
        } catch (JsonMappingException e) {
            throw new JsonDeserializeException("字段映射异常", e);
        } catch (IOException e) {
            throw new JsonDeserializeException("IO异常", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T toBean(@Nullable String json, TypeReference<T> valueTypeRef, T defaultValue) {
        T bean = toBean(json, valueTypeRef);
        return bean == null ? defaultValue : bean;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T toBean(@Nullable String json, JavaType type) {
        try {
            if (!Strings.isNullOrEmpty(json)) {
                return (T) objectMapper.readValue(json, type);
            }
            return null;
        } catch (JsonParseException e) {
            throw new JsonDeserializeException("解析异常", e);
        } catch (JsonMappingException e) {
            throw new JsonDeserializeException("字段映射异常", e);
        } catch (IOException e) {
            throw new JsonDeserializeException("IO异常", e);
        }
    }

    public static <T> T toBean(@Nullable String json, JavaType type, T defaultValue) {
        T value = toBean(json, type);
        return value == null ? defaultValue : value;
    }
}
