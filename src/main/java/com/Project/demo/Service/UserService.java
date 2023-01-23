package com.Project.demo.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Project.demo.dao.FileRepo;
import com.Project.demo.dao.UserRepo;
import com.Project.demo.dto.UserDto;
import com.Project.demo.model.Files;
import com.Project.demo.model.Users;

@Service
public class UserService extends BaseService {

	// @Autowired
	// private EmployeeRepo employeeRepo;
	
	  @Autowired 
	  private UserRepo userRepo;

		@Autowired
		private FileRepo fileRepo;

	private Logger logger = LogManager.getLogger(UserService.class);

	public List<UserDto> getUserslistAll() {
		LogManager.getLogger("Inside Findall");
		List<Users> users = userRepo.findAll();
		List<UserDto> returnList = users.stream().map(x -> assignUserstoDto(x)).collect(Collectors.toList());
		return returnList;
	}
	
	public List<UserDto> getByUserDesignation(String userDesignation) {
		LogManager.getLogger("Inside getByUserDesignation");
		List<Users> users = userRepo.findByUserDesignation(userDesignation);
		List<UserDto> returnList = users.stream().map(x -> assignUserstoDto(x)).collect(Collectors.toList());
		return returnList;
	}

	public UserDto assignUserstoDto(Users user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserDesignation(user.getUserDesignation());
		userDto.setUserDOJ(user.getUserDOJ());
		userDto.setUserPhoneNumber(user.getUserPhoneNumber());
		userDto.setUserEmail(user.getUserEmail());
		return userDto;
	}

	public void createUser(MultipartFile file, UserDto user) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Files fileDB = new Files(file.getContentType(), file.getBytes(), fileName);
		Users userDB = new Users();
		userDB.setFiles(fileDB);
		userDB.setPassword(user.getPassword());
		userDB.setUserName(user.getUserName());
		userDB.setUserDesignation(user.getUserDesignation());
		userDB.setUserDOJ(user.getUserDOJ());
		userDB.setUserPhoneNumber(user.getUserPhoneNumber());
		userDB.setUserEmail(user.getUserEmail());
		userRepo.save(userDB);
	}
	
	

}
