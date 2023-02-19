package com.Project.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
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

import com.Project.demo.Service.UserService;
import com.Project.demo.dto.UserDto;

@Component
@RestController()
// @CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/user")
public class UserController { // extends BaseController {

	@Autowired
	private UserService userService;

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> getAll(@RequestParam(value = "designation", required = false) String designation,
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "userName", required = false) String userName) {
		LogManager.getLogger("Inside findAll");
		List<UserDto> users = userService.getUsersListAll(designation, userId, userName);
		return users;
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> createUser(@RequestBody UserDto user) throws IOException {
		// if (file.isEmpty()) {
		// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// }
		Boolean status = userService.createUser(user);
		if (status)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>(status, null, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/{userName}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteUser(@PathVariable("userName") String userName) {
		userService.removeUser(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
