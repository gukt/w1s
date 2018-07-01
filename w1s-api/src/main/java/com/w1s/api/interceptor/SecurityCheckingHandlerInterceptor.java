/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.interceptor;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SecurityCheckingHandlerInterceptor extends HandlerInterceptorAdapter {
    final static Logger LOGGER = LoggerFactory.getLogger(SecurityCheckingHandlerInterceptor.class);

    private final static String appSecret = "This site was developed by a dog";

    /**
     * 不需要登陆就可以访问的地址
     */
    private List<String> whiteList;

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        LOGGER.info("收到请求: {}{}, remote addr: {}", uri, Strings.isNullOrEmpty(queryString) ? "" : "?" + queryString,
                request.getRemoteAddr());

        // 放过白名单接口
        if (whiteList.contains(request.getServletPath())) {
            return true;
        }

        // TODO 添加拦截逻辑

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
}
