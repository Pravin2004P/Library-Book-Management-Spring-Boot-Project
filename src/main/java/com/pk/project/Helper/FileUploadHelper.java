package com.pk.project.Helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    public final String upload_Dir = 
    //Static path
    "C:\\Spring Boot Vs Code\\Spring Boot Running Projects\\LibraryBookManagement\\src\\main\\resources\\static\\image";

    
    public boolean uploadFile(MultipartFile f) {
        if (f.isEmpty()) {
            return false;
        }

        try {
            Path uploadPath = Paths.get(upload_Dir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(f.getOriginalFilename());
            Files.copy(f.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Verify file existence
            return Files.exists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}