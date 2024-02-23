package com.ponto.ponto_digital.utils.helper;

import java.util.Objects;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class RequestHelper {
  
    private static HttpServletRequest request;

    public RequestHelper build() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.isNull(servletRequestAttributes)) {
            return this;
        }

        request = servletRequestAttributes.getRequest();

        return this;
    }

    public String getIp() {
        if (Objects.isNull(request)) {
            return "Não foi possível obter o ip";
        }

        return request.getRemoteAddr();
    }
}
