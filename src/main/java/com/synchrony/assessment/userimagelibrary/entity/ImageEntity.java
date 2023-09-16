package com.synchrony.assessment.userimagelibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ImageEntity {

    @Id
    private String imageId;
    private String title;
    private String description;
    private String imageLink;
    private String deleteHash;

    /**
     *
     * @return
     */
    public String getImageId() {
        return imageId;
    }

    /**
     *
     * @param imageId
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     *
     * @param imageLink
     */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     *
     * @return
     */
    public String getDeleteHash() {
        return deleteHash;
    }

    /**
     *
     * @param deleteHash
     */
    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }
}
