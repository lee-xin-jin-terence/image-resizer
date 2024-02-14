package com.terence.imageresizer.controllers;

import com.terence.imageresizer.services.ImageResizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


/**
 * A controller that provides routing for the server
 * */
@Controller
public class ImageResizerController {

    @Autowired
    private ImageResizerService imageResizerService;

    /**
     * Handles the request for image resizing
     *
     * @param percentageReductionOfImageSize the percentage of the image to be reduced
     *                  e.g. if the image is to be reduced by 40%, then the parcentage
     *                                       reduction is 40
     * @param imageFile - a multipart image file to be resized
     *
     * @return a resized JPEG image aas body of response (if successful)
     * */
    @PostMapping(value = "/resize-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> resizeImage(
            @RequestParam("percentageReductionOfImageSize") int percentageReductionOfImageSize,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {


        byte [] afterResizedImageByteArray =
                        this.imageResizerService.resizeImage(imageFile, percentageReductionOfImageSize);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(afterResizedImageByteArray);

    }


    /**
     * Handles any exceptions encountered when image resizing failed
     *
     * @return a HTTP bad request response (400)
     * */
    @ExceptionHandler({ IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException() {
        return ResponseEntity.badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Invalid percentageReductionOfImageSize value");
    }


}
