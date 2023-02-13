package com.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Project.demo.Service.MappingService;
import com.Project.demo.dto.MappingDto;

@Component
@RestController
@RequestMapping("/map")
public class MappingController {

    @Autowired
    private MappingService mappingService;


    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createMapping(@RequestBody MappingDto map) throws Exception {
        mappingService.createMapping(map);
    }

    // @PostMapping("/uploadMultipleFiles")
    // public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files")
    // MultipartFile[] files) {
    // return Arrays.asList(files).stream().map(file -> uploadFile(file))
    // .collect(Collectors.toList());
    // }

}