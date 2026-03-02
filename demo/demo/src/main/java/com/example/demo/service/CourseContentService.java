package com.example.demo.service;

import com.example.demo.entity.CourseContent;
import com.example.demo.repository.CourseContentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseContentService {

    private final CourseContentRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public CourseContentService(CourseContentRepository repository) {
        this.repository = repository;
    }


    // SAVE FILE (NEW CLEAN METHOD)
    public String saveFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        // create directory if not exists
        Files.createDirectories(uploadPath);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path targetLocation = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return targetLocation.toString(); // IMPORTANT
    }


    // UPLOAD FILE
    public CourseContent uploadFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        Long fileSize = file.getSize();

        // Validate file type
        if (!(fileType.equals("application/pdf") ||
                fileType.equals("video/mp4") ||
                fileType.equals("image/jpeg") ||
                fileType.equals("image/png"))) {
            throw new RuntimeException("Invalid file type");
        }

        // save file using new method
        String filePath = saveFile(file);

        // Save metadata
        CourseContent content = new CourseContent();
        content.setFileName(fileName); // original name for user
        content.setFileType(fileType);
        content.setFileSize(fileSize);
        content.setUploadDate(LocalDateTime.now());
        content.setFileUrl(filePath);

        return repository.save(content);
    }

    public List<CourseContent> getAll() {
        return repository.findAll();
    }

    public CourseContent getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}