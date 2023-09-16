package com.synchrony.assessment.userimagelibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgurDeleteResponse {

    @JsonProperty("data")
    private boolean Data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("status")
    private int status;

    public boolean isData() {
        return Data;
    }

    public void setData(boolean data) {
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
