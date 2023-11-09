package com.cachenote.server.common.ResponseWrapper;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OkWrapper<T> {

    private T data;


    public static <T> OkWrapper<T> Result(T data) {
        OkWrapper<T> okWrapper = new OkWrapper<>();
        okWrapper.setData(data);
        return okWrapper;
    }
}

