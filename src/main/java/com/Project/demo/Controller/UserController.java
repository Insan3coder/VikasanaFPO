package com.Project.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Project.demo.Service.UserService;
import com.Project.demo.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RestController()
// @CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/user")
public class UserController { // extends BaseController {

	// @Autowired
	// private UserRepo userRepo;

	@Autowired
	private UserService userService;


	// @GetMapping(value = "/{designation}")
	// @ResponseStatus(code = HttpStatus.OK)
	// public List<UserDto> UserView(@PathVariable("designation") String
	// designation) {
	// logger.debug("Inside UserView");
	// List<UserDto> listEmployee = userService.getByUserDesignation(designation);
	// logger.debug("Exiting UserView");
	// return listEmployee;
	// // return "index.html";
	// }

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> getAll(@RequestParam(value = "designation", required = false) String designation,
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "userName", required = false) String userName) {
		LogManager.getLogger("Inside findAll");
		List<UserDto> users = userService.getUserslistAll(designation, userId, userName);
		return users;
	}

	// @PostMapping()
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> createUser(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "jsonData", required = true) String jsonObject) throws IOException {
		// if (file.isEmpty()) {
		// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// }
		ObjectMapper objectMapper = new ObjectMapper();
		UserDto user = objectMapper.readValue(jsonObject, UserDto.class);
		String str = userService.createUser(file, user);
		if (str.equals("Successful"))
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>(str, null, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/{userName}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteUser(@PathVariable("userName") String userName) {
		userService.removeUser(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
