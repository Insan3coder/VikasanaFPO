package com.Project.demo.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
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
public class UserService {

	// @Autowired
	// private EmployeeRepo employeeRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private FileRepo fileRepo;

	private Logger logger = LogManager.getLogger(UserService.class);

	@Transactional(readOnly = true)
	public List<UserDto> getUserslistAll(String designation, Long userId, String userName) {
		try {

			LogManager.getLogger("Inside Findall");

			if (Objects.isNull(userName) && Objects.isNull(designation) && Objects.isNull(userId))
				return userRepo.findAll().stream().map(x -> assignUserstoDto(x)).collect(Collectors.toList());
			else if (Objects.isNull(userName) && Objects.isNull(designation))
				return userRepo.findById(userId).stream().map(x -> assignUserstoDto(x)).collect(Collectors.toList());
			else if (Objects.isNull(userId) && !Objects.isNull(userName) && Objects.isNull(designation))
				return userRepo.findByUserName(userName).stream().map(x -> assignUserstoDto(x))
						.collect(Collectors.toList());
			else if (!Objects.isNull(designation))
				return userRepo.findByUserDesignation(designation).stream().map(x -> assignUserstoDto(x))
						.collect(Collectors.toList());
			else
				return null;

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationContextException(e.getMessage());
		}

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
				Files fileDescription = null;
				Files filePath = null;
				@SuppressWarnings("null")
				Files fileDB = new Files(fileName, file.getContentType(), file.getBytes(), fileDescription.getFileDescription(), filePath.getFilePath());
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
