/*package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ImageData;
import com.softserve.itacademy.repository.ImageRepository;
import com.softserve.itacademy.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

       ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

       if (imageData!=null){
           return "file uploaded successfully: " +file.getOriginalFilename();
       } return "something went wrong :(";
    }


    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }


}
*/