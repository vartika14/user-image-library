package com.synchrony.assessment.userimagelibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ImageMappingEntity {
    @Id
    private String imageId;
    private Long userId;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
