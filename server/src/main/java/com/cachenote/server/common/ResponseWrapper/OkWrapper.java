package com.cachenote.server.common.ResponseWrapper;

import lombok.Data;
import lombok.NoArgsConstructor;



public class OkWrapper<T> extends BaseResponseWrapper<T> {

    public OkWrapper(int statusCode, String message, T data) {
        super(statusCode, message, data);
    }


    public static <T> OkWrapper<T> Result(int statusCode, String message, T data) {
//        OkWrapper<T> okWrapper = new OkWrapper<>();
//        okWrapper.setData(data);
//        return okWrapper;
        return new OkWrapper<>(statusCode, message, data);
    }
}

