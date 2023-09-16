package com.synchrony.assessment.userimagelibrary.service;

import com.synchrony.assessment.userimagelibrary.dto.Image;
import com.synchrony.assessment.userimagelibrary.dto.ImageData;
import com.synchrony.assessment.userimagelibrary.entity.ImageEntity;
import com.synchrony.assessment.userimagelibrary.dto.ImgurDeleteResponse;
import com.synchrony.assessment.userimagelibrary.exception.ImageNotFoundException;
import com.synchrony.assessment.userimagelibrary.exception.ImgurAPIException;
import com.synchrony.assessment.userimagelibrary.jpa.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String IMGUR_CLIENT_ID = "f7dd2d83017a2e5";

    private static final String IMGUR_BASE_URI = "https://api.imgur.com/3/image/";

    private static final String IMGUR_IMAGE_UPLOAD_URI= "https://api.imgur.com/3/image";

    private static final String IMGUR_IMAGE_DELETE_URI=  "https://api.imgur.com/3/image/";

    private static final String SUCCESS_STATUS = "Success";

    private final ImageRepository imageRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    /**
     *
     * @param imageRepository
     */
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     *
     * @param imageId
     * @return
     */
    public Image getImageDetails(String imageId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        LOGGER.info("GET Image details API call for image id : {}",imageId);
        ResponseEntity<ImageData> response = restTemplate.exchange(IMGUR_BASE_URI+imageId, HttpMethod.GET, entity, ImageData.class);
        return response.getBody().getData();
    }

    /**
     *
     * @param base64encodedImage
     * @return
     */
    public String imageUpload(String base64encodedImage){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<String> entity = new HttpEntity<String>(base64encodedImage, headers);
        LOGGER.info("Image upload  API call for image ");
        ResponseEntity<ImageData> response = restTemplate.exchange(IMGUR_IMAGE_UPLOAD_URI,HttpMethod.POST,entity,ImageData.class);
        if(response.getBody().isSuccess()){
            ImageEntity imageDao = mapToImageDaoObject(response.getBody().getData());
            LOGGER.info("Save image in the database {} {}",imageDao.getImageId(),imageDao.getImageLink());
            imageRepository.save(imageDao);
        }
        return response.getBody().getData().getLink();

    }

    /**
     *
     * @param image
     * @return
     */
    private ImageEntity mapToImageDaoObject(Image image) {
        ImageEntity imageDao = new ImageEntity();
        imageDao.setImageId(image.getId());
        imageDao.setImageLink(image.getLink());
        imageDao.setDescription(image.getDescription());
        imageDao.setTitle(image.getTitle());
        imageDao.setDeleteHash(image.getImageDeleteHash());
        return imageDao;
    }

    /**
     *
     * @param imageId
     * @return
     * @throws ImageNotFoundException
     * @throws ImgurAPIException
     */
    public String deleteImage(String imageId) throws ImageNotFoundException, ImgurAPIException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Optional<ImageEntity> image = imageRepository.findById(imageId);
        if(image.isEmpty()){
            throw new ImageNotFoundException("Image does not exists in DB");
        }
        LOGGER.info("IMGUR API call to delete image from their gallery");
        ResponseEntity<ImgurDeleteResponse> response = restTemplate.exchange(IMGUR_IMAGE_DELETE_URI+image.get().getDeleteHash(),HttpMethod.DELETE,entity,ImgurDeleteResponse.class);
        if(response.getBody().isData()) {
            return SUCCESS_STATUS;
        } else {
           throw new ImgurAPIException("Imgur API reponsed with error status : "+ response.getBody().getStatus());
        }

    }
}
