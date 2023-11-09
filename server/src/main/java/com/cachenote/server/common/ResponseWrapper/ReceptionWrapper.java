package com.cachenote.server.common.ResponseWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class ReceptionWrapper<T> {
    private String message;
    private T data;

    public ReceptionWrapper(String message) {
        this.message = message;
    }

    public static <T> ReceptionWrapper<T> Result(String message, T data) {
        return new ReceptionWrapper<>(message, data);
    }
}
