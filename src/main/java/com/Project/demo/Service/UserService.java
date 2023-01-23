package com.Project.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.demo.dao.UserRepo;
import com.Project.demo.dto.UserDto;
import com.Project.demo.model.Users;

@Service
public class UserService extends BaseService {

	// @Autowired
	// private EmployeeRepo employeeRepo;
	
	  @Autowired 
	  private UserRepo userRepo;
	 

	private Logger logger = LogManager.getLogger(UserService.class);

	public List<UserDto> getUserslistAll() {
		LogManager.getLogger("Inside Findall");
		List<Users> users = userRepo.findAll();
		List<UserDto> returnList = users.stream().map(x -> assingUserstoDto(x)).collect(Collectors.toList());
		return returnList;
	}
	
	public List<UserDto> getByUserDesgination(String userDesgination) {
		LogManager.getLogger("Inside Findall");
		List<Users> users = userRepo.findByuserDesgination(userDesgination);
		List<UserDto> returnList = users.stream().map(x -> assingUserstoDto(x)).collect(Collectors.toList());
		return returnList;
	}

	public UserDto assingUserstoDto(Users user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserDesgination(user.getUserDesignation());
//		userDto.setUserDOJ(user.getUserDOJ());
		userDto.setUserPhoneNumber(user.getUserPhoneNumber());
		return userDto;
	}

	
	

}
