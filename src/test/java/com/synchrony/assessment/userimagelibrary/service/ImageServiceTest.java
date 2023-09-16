package com.synchrony.assessment.userimagelibrary.service;

import com.synchrony.assessment.userimagelibrary.dto.Image;
import com.synchrony.assessment.userimagelibrary.jpa.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageServiceTest {

    @Autowired
    private ImageRepository imageRepository;


    @Test
    public void testGetImage(){
        Image compareImage = new Image();
        compareImage.setId("ZW5n8h1");
        compareImage.setLink("https://i.imgur.com/ZW5n8h1.jpg");
        ImageServiceImpl imageService = new ImageServiceImpl(imageRepository);
        Image resultImage = imageService.getImageDetails("ZW5n8h1");
        assertThat(resultImage).isNotNull();
    }
}
