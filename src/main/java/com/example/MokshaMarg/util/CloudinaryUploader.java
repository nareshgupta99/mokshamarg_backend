package com.example.MokshaMarg.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Service
public class CloudinaryUploader {
	
	 @Autowired
	    private Cloudinary cloudinary;

	    public Map uploadFile(MultipartFile file) throws IOException {
	        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	    }

	    public Map deleteFile(String publicId) throws IOException {
	        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
	    }

}
