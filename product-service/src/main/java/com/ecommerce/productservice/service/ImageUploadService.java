package com.ecommerce.productservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploadService {

    private final Cloudinary cloudinary;

    public ImageUploadService() {
        // Use your credentials from the Cloudinary Dashboard
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dg8q37e64",
                "api_key", "184677269395847",
                "api_secret", "FHguJTdHX78FTkiDClJpfmbV1J0"
        ));
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }
}
