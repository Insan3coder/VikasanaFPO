package com.Project.demo.Controller;

import java.util.List;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

import com.Project.demo.Service.UserService;
import com.Project.demo.dto.UserDto;

@Component
@RestController()
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/users")
public class UserController { // extends BaseController {

	// @Autowired
	// private UserRepo userRepo;
	  
	  @Autowired 
	  private UserService userService;

	private Logger logger = LogManager.getLogger(UserController.class);

	@GetMapping(value = "/{designation}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> UserView(@PathVariable("designation") String designation) {
		logger.debug("Inside UserView");
		List<UserDto> listEmployee = userService.getByUserDesignation(designation);
		logger.debug("Exiting UserView");
		return listEmployee;
		// return "index.html";
	}
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> getAll() {
		LogManager.getLogger("Inside Findall");
		List<UserDto> users = userService.getUserslistAll();
		return users;
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> createUser(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestBody UserDto user) throws IOException {
//		if (file.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
String str = userService.createUser(file, user);
if (str.equals("Successfull"))
	return new ResponseEntity<>(HttpStatus.CREATED);
else
	return new ResponseEntity<Object>(str, null, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping(value = "/{userName}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteUser(@PathVariable("userName") String userName)  {
		userService.removeUser(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
