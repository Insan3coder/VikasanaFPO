package com.Project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.Path;
import com.Project.demo.model.Files;

@Repository
public interface FileRepository extends JpaRepository<Files, Long> {

    List<Files> findByFilePath(Path filePath);

    List<Files> findByFileType(String fileType);

    List<Files> findByFileName(String fileName);

}