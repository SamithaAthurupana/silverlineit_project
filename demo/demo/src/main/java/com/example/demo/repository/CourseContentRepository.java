package com.example.demo.repository;

import com.example.demo.entity.CourseContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseContentRepository extends JpaRepository<CourseContent, Long> {
}