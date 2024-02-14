package com.terence.imageresizer.services;

import com.terence.imageresizer.models.ImageResizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A service that allows for resizing of an image
 *
 * Example usage:
 *
 <pre>
      ...
      ImageResizerService service = new ImageResizerService();

      int percentageOfImageSizeToRetain = 40; // meaning 60% size reduction

      BufferedImage resizedImageOutput = service.resize(bufferedImageInput,percentageOfImageSizeToRetain);
      ...
 *</pre>
 * @author Terence Lee
 *
 * */
@Service
public class ImageResizerService {

    /**
     * Resizes a buffer image to become a percentage of its original size, based on the height and
     *   width of the image
     *
     *   For example, if the original image has width and height of 1000 pixels, and the percentage of
     *   original size to be retained is 40%, then the new height and width of the image will be
     *   400 pixels.
     *
     * @param imageMultipartFile - multipart file representing the image to be resized
     * @param percentageReductionOfImageSize - the percentage size reduction of the original image,
     *               between 1%(inclusive) and 99%(inclusive)
     *
     * @throws IllegalArgumentException - if percentageOfOriginalToRetain is not between 1 (inclusive)
     *      and 99 (inclusive)
     *
     * @return byte array representing the resized image (in jpeg format)
     * */
    public byte[] resizeImage(MultipartFile imageMultipartFile, int percentageReductionOfImageSize)
            throws IOException {


        return ImageResizer.resizeImage(imageMultipartFile, percentageReductionOfImageSize);
    }




}