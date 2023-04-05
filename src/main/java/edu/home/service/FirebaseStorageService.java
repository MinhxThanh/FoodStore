package edu.home.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FirebaseStorageService {
    String upload(MultipartFile multipartFile);
//    Object download(String fileName) throws IOException;
}
