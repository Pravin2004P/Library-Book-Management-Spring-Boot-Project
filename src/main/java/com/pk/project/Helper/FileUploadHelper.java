package com.pk.project.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.logging.Logger;

@Component
public class FileUploadHelper {
    private final Logger logger = Logger.getLogger(FileUploadHelper.class.getName());
    public final String upload_Dir = "C:\\Spring Boot Vs Code\\Spring Boot Running Projects\\LibraryBookManagement\\src\\main\\resources\\static\\image";

    public boolean uploadFile(MultipartFile f) {
        if (f.isEmpty()) {
            logger.warning("Failed to upload empty file: " + f.getOriginalFilename());
            return false;
        }

        try {
            Path uploadPath = Paths.get(upload_Dir);
            if (!Files.exists(uploadPath)) {
                logger.info("Upload directory does not exist. Creating directory: " + upload_Dir);
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(f.getOriginalFilename());
            Files.copy(f.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File uploaded successfully: " + f.getOriginalFilename() + " to " + filePath.toString());

            // Verify file existence
            if (Files.exists(filePath)) {
                logger.info("File verified to exist at: " + filePath.toString());
            } else {
                logger.severe("File does not exist after upload: " + filePath.toString());
                return false;
            }

            return true;
        } catch (IOException e) {
            logger.severe("File upload failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                f.getInputStream().close();
            } catch (IOException e) {
                logger.severe("Failed to close file input stream: " + e.getMessage());
            }
        }
    }
}
