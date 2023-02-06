package com.Project.demo.Service;


import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Project.demo.dao.DBFileRepository;
import com.Project.demo.model.Files;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public Files storeFile(String fileDescription, String filePath,MultipartFile file) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Files dbFile = new Files(fileName, file.getContentType(), file.getBytes(), fileDescription, filePath);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Files getFile(String fileId) {
        return dbFileRepository.findById(fileId).get();
    }
    
}