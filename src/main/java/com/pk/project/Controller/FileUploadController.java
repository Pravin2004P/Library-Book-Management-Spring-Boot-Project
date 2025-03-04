package com.pk.project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @SuppressWarnings("null")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        System.out.println("File Name: " + file.getOriginalFilename());
        System.out.println("File Type: " + file.getContentType());
        System.out.println("File Size: " + file.getSize());
        System.out.println("File Content: " + file.toString());
        System.out.println("File Content: " + file.getResource());
        System.out.println("File Content: " + file.getName());
        System.out.println("File Content: " + file.getOriginalFilename());
        System.out.println("File Content: " + file.getContentType());

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }

        if (file.getContentType().equals("image/mp4") || file.getContentType().equals("image/mkv")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a valid image file to upload");
        }

        return ResponseEntity.ok("File uploaded successfully");
    }
}
