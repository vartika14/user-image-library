package com.synchrony.assessment.userimagelibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImageData implements Serializable {

    @JsonProperty("data")
    private Image data;
    @JsonProperty("status")
    private String status;
    @JsonProperty("success")
    private boolean success;

    /**
     *
     * @return
     */
    public Image getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Image data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     *
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
