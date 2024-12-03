package com.titans.SilentSpeech.services.AudioService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class AudioService {
    private final Cloudinary cloudinary;

    public AudioService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadAudio(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));  // Automatically detects resource type, including audio
        return uploadResult.get("url").toString();  // Return the URL of the uploaded audio file
    }
}
