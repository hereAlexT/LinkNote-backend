package com.linknote.server.common;

import com.linknote.server.common.responseWrapper.ExceptionWrapper;
import com.linknote.server.common.responseWrapper.OkWrapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(@Nullable MethodParameter methodParameter,
                            @Nullable Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o,
                                  @Nullable MethodParameter methodParameter,
                                  @Nullable MediaType mediaType,
                                  @Nullable Class<? extends HttpMessageConverter<?>> aClass,
                                  @Nullable ServerHttpRequest serverHttpRequest,
                                  @Nullable ServerHttpResponse serverHttpResponse) {
        if (o instanceof ExceptionWrapper) {
            return o;
        }
        return OkWrapper.Result(StatusCode.OK.getCode(), "Ok", o);
    }
}
