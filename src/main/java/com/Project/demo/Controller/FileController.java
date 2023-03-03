package com.Project.demo.Controller;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Project.demo.Service.FileService;
import com.Project.demo.dto.FileDto;

@Component
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/file")
public class FileController {


    @Autowired
	private FileService fileService;

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<FileDto> getAll(@RequestParam(value = "filePath", required = false) String filePath,
			@RequestParam(value = "fileId", required = false) Long fileId,
			@RequestParam(value = "fileName", required = false) String fileName,
			@RequestParam(value = "fileType", required = false) String fileType) {
		List<FileDto> users = fileService.getFiles(filePath, fileId, fileName, fileType);
		return users;
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> uploadFile(@RequestBody FileDto fileDto) throws Exception {
		boolean status = fileService.storeFile(fileDto);
		if (status)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/{fileId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteFile(@PathVariable("fileId") Long fileId) {
		fileService.removeFile(fileId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/encode")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String encode(@RequestParam(value = "file") MultipartFile file) {
		// File temp = new File(file.getOriginalFilename());
		// FileOutputStream fos = new FileOutputStream(temp);
		// fos.write(file.getBytes());
		// fos.close();

		byte[] encoded;
		try {
			encoded = Base64.getEncoder().encode(file.getBytes());
			return new String(encoded, StandardCharsets.US_ASCII);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}