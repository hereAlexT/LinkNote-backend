package com.cachenote.server.common.responseWrapper;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;



@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExceptionWrapper<T> extends BaseResponseWrapper<T> {

    public ExceptionWrapper(int statusCode, String message, T data) {
        super(statusCode, message, data);
    }

    public static <T> ExceptionWrapper<T> Result(int statusCode, String message, T data) {
        return new ExceptionWrapper<>(statusCode, message, data);
    }
}
