package edu.home.service.impl;

import edu.home.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private ServletContext app;

    @Autowired
    private HttpServletRequest request;

    @Override
    public File save(MultipartFile file, String folder) {
        File dir = new File("src/main/resources/static/assets/images/" + folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File saveFile = new File(dir.getAbsolutePath(), name);
            file.transferTo(saveFile);
            System.out.println("path: " + saveFile.getAbsolutePath());
            return saveFile;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}