package com.synchrony.assessment.userimagelibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImgurResponseWrapper<T>  implements Serializable {

    @JsonProperty("data")
    private T Data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("status")
    private int status;


    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
