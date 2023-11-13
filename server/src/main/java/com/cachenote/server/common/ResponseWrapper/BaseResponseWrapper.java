package com.cachenote.server.common.ResponseWrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseWrapper<T> {
    @JsonProperty("code")
    private int statusCode;

    @JsonProperty("msg")
    private String message;

    private T data;


}
