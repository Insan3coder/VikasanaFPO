package com.Project.demo.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Transactional(readOnly = true)
	public List<UserDto> getUserslistAll() {
		LogManager.getLogger("Inside Findall");
		List<Users> users = userRepo.findAll();
		List<UserDto> returnList = users.stream().map(x -> assignUserstoDto(x)).collect(Collectors.toList());
		return returnList;
	}

	@Transactional(readOnly = true)
	public List<UserDto> getByUserDesignation(String userDesignation) {
		LogManager.getLogger("Inside getByUserDesignation");
		List<Users> users = userRepo.findByUserDesignation(userDesignation);
		List<UserDto> returnList = users.stream().map(x -> assignUserstoDto(x)).collect(Collectors.toList());
		return returnList;
	}

	public UserDto assignUserstoDto(Users user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		if (!user.getUserName().equals(null))
			userDto.setUserName(user.getUserName());
		if (user.getUserDesignation() != null && !user.getUserDesignation().equals(null))
			userDto.setUserDesignation(user.getUserDesignation());
		if (user.getUserDOJ() != null && !user.getUserDOJ().equals(null))
			userDto.setUserDOJ(user.getUserDOJ());
		if (user.getUserPhoneNumber() != null && !user.getUserPhoneNumber().equals(null))
			userDto.setUserPhoneNumber(user.getUserPhoneNumber());
		if (user.getUserEmail() != null && !user.getUserEmail().equals(null))
			userDto.setUserEmail(user.getUserEmail());
		if (user.getFiles() != null && !user.getFiles().equals(null))
			userDto.setFileId(user.getFiles().getFileId());
		return userDto;
	}

	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public String createUser(MultipartFile file, UserDto user) throws IOException {
		try {

			Users userDB = new Users();
			if (file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Files fileDB = new Files(file.getContentType(), file.getBytes(), fileName);
				userDB.setFiles(fileDB);
			}
			userDB.setPassword(user.getPassword());
			userDB.setUserName(user.getUserName());
			userDB.setUserDesignation(user.getUserDesignation());
			userDB.setUserDOJ(user.getUserDOJ());
			userDB.setUserPhoneNumber(user.getUserPhoneNumber());
			userDB.setUserEmail(user.getUserEmail());
			userDB.setUserId(user.getUserId());
			userRepo.save(userDB);
			return "Successful";

		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public void removeUser(String userName) {
		userRepo.deleteByUserName(userName);
	}

}
