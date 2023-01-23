package com.Project.demo.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Project.demo.Service.UserService;
import com.Project.demo.dao.UserRepo;
import com.Project.demo.dto.UserDto;

@RestController()
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/users")
public class UserController extends BaseController {

	  @Autowired 
	  private UserRepo userRepo;
	  
	  @Autowired 
	  private UserService userService;

	private Logger logger = LogManager.getLogger(UserController.class);

	@GetMapping(value = "/{designation}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> UserView(@PathVariable("designation") String designation) {
		logger.debug("Inside UserView");
		List<UserDto> listEmployee = userService.getByUserDesgination(designation);
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
}
