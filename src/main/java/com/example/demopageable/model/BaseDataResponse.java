package com.example.demopageable.model;

import lombok.Data;

@Data
public class BaseDataResponse<T> {
    private String status = "OK";
    private int code = 200;
    private String message = "";
    private T data;

    public static BaseDataResponse<Boolean> getBaseResponse() {
        BaseDataResponse<Boolean> baseResponse = new BaseDataResponse<>();
        baseResponse.setData(false);
        return baseResponse;
    }

    public static BaseDataResponse<Boolean> getFailedResponse() {
        return getFailedResponse(400, "");
    }

    public static BaseDataResponse<Boolean> getFailedResponse(int code) {
        return getFailedResponse(code, "");
    }

    public static BaseDataResponse<Boolean> getFailedResponse(int code, String message) {
        BaseDataResponse<Boolean> failedResponse = new BaseDataResponse<>();
        failedResponse.setStatus("ERROR");
        failedResponse.setCode(code);
        failedResponse.setMessage(message);
        failedResponse.setData(false);
        return failedResponse;
    }
}
