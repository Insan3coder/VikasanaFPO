package com.Project.demo.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Project.demo.Service.DBFileStorageService;
import com.Project.demo.dto.UploadFileResponse;
import com.Project.demo.model.Files;

@Component
@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    private DBFileStorageService dbFileStorageService;

	@PostMapping()
    public UploadFileResponse uploadFile(@RequestParam(value = "fileDescription" , required = false) String jsonObject
    		,@RequestParam(value = "filePath" , required = false) String filePath
    		, @RequestParam("file") MultipartFile file) throws Exception {
    	Files dbFile = dbFileStorageService.storeFile(jsonObject,filePath,file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file/")
                .path(dbFile.getFileId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize() ,dbFile.getFileDescription(), dbFile.getFilePath());
    }

	// @PostMapping("/uploadMultipleFiles")
	// public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files")
	// MultipartFile[] files) {
	// return Arrays.asList(files).stream().map(file -> uploadFile(file))
	// .collect(Collectors.toList());
	// }

	@GetMapping("/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) { // Load file from database DBFile
		Files dbFile = dbFileStorageService.getFile(fileId);

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"" , dbFile.getFileDescription(), dbFile.getFilePath())
				.body(new ByteArrayResource(dbFile.getFileContent()));
	}

}