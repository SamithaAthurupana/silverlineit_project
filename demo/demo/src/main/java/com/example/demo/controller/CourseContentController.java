package com.example.demo.controller;

import com.example.demo.entity.CourseContent;
import com.example.demo.service.CourseContentService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseContentController {

    private final CourseContentService service;

    public CourseContentController(CourseContentService service) {
        this.service = service;
    }

    // upload
    @PostMapping("/upload")
    public CourseContent upload(@RequestParam("file") MultipartFile file) throws IOException {
        return service.uploadFile(file);
    }

    // get all
    @GetMapping
    public List<CourseContent> getAll() {
        return service.getAll();
    }

    // get by id
    @GetMapping("/{id}")
    public CourseContent getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // download file
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        CourseContent content = service.getById(id);
        if (content == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(content.getFileUrl());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + content.getFileName() + "\"")
                .body(resource);
    }

}