package com.synchrony.assessment.userimagelibrary.service;

import com.synchrony.assessment.userimagelibrary.dto.Image;
import com.synchrony.assessment.userimagelibrary.exception.ImageNotFoundException;
import com.synchrony.assessment.userimagelibrary.exception.ImgurAPIException;

public interface ImageService {
    /**
     *
     * @param imageId
     * @return
     */
    public Image getImageDetails(String imageId);

    /**
     *
     * @param base64encodedImage
     * @return
     */
    public String imageUpload(String base64encodedImage);

    /**
     *
     * @param imageId
     * @return
     */
    public String deleteImage(String imageId) throws ImageNotFoundException, ImgurAPIException;
}
