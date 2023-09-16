package com.synchrony.assessment.userimagelibrary.controller;

import com.synchrony.assessment.userimagelibrary.exception.ImageNotFoundException;
import com.synchrony.assessment.userimagelibrary.exception.ImgurAPIException;
import com.synchrony.assessment.userimagelibrary.service.ImageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/images")
public class ImageController {

    private  final ImageServiceImpl imageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    /**
     *
     * @param imageService
     */
    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String uploadFileToImgur(@RequestParam("file")MultipartFile file){
        LOGGER.info("Image Upload Request");
        try{
            String image = Base64.getEncoder().encodeToString(file.getBytes());
            return imageService.imageUpload(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    /**
     *
     * @param imageId
     * @return
     */
    @DeleteMapping(path="/delete/{imageId}")
    public String deleteImgFromImgur(@PathVariable("imageId") String imageId) throws ImgurAPIException, ImageNotFoundException {
        LOGGER.info("Image delete Request for image : {}", imageId);
        return imageService.deleteImage(imageId);
    }

}
