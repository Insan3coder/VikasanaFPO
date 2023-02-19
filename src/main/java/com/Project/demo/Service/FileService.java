package com.Project.demo.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Project.demo.dao.FileRepository;
import com.Project.demo.dto.FileDto;
import com.Project.demo.model.Files;

@Service
public class FileService {

    private Logger logger = LogManager.getLogger(UserService.class);

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private FileRepository fileRepo;

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public Boolean storeFile(FileDto file) throws Exception {
        try {
            Files fileDb = modelMapper.map(file, Files.class);
            fileRepo.save(fileDb);
            return true;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<FileDto> getFiles(String filePath, Long fileId, String fileName, String fileType) {
        try {
            if (!Objects.isNull(filePath))
                return fileRepo.findByFilePath(filePath).stream().map(x -> assignFileToDto(x))
                        .collect(Collectors.toList());
            if (!Objects.isNull(fileId))
                return fileRepo.findById(fileId).stream().map(x -> assignFileToDto(x))
                        .collect(Collectors.toList());
            if (!Objects.isNull(fileName))
                return fileRepo.findByFileName(fileName).stream().map(x -> assignFileToDto(x))
                        .collect(Collectors.toList());

            if (!Objects.isNull(fileType))
                return fileRepo.findByFileType(fileType).stream().map(x -> assignFileToDto(x))
                        .collect(Collectors.toList());
            return fileRepo.findAll().stream().map(x -> assignFileToDto(x))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }

    }

    private FileDto assignFileToDto(Files file) {
        FileDto fileDto = modelMapper.map(file, FileDto.class);
        return fileDto;
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public void removeFile(Long fileId) {
        try {
            fileRepo.deleteById(fileId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException("Error while deleting file " + (fileId).toString());
        }
    }

}