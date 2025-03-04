package com.pk.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pk.project.Helper.FileUploadHelper;

import java.util.logging.Logger;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    private final Logger logger = Logger.getLogger(FileUploadController.class.getName());

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Received file: " + file.getOriginalFilename());
        logger.info("File type: " + file.getContentType());
        logger.info("File size: " + file.getSize());

        if (file.isEmpty()) {
            logger.warning("File is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }

        try {
            boolean isUploaded = fileUploadHelper.uploadFile(file);
            if (isUploaded) {
                return ResponseEntity.ok("File uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");
            }
        } catch (Exception e) {
            logger.severe("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");
        }
    }
}
