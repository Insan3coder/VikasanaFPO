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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	PasswordEncoder passwordEncoder;
	
	private Logger logger = LogManager.getLogger(UserService.class);

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Transactional(readOnly = true)
	public List<UserDto> getUsersListAll(String designation, Long userId, String userName) {
		try {

			LogManager.getLogger("Inside findAll");

			if (Objects.isNull(userName) && Objects.isNull(designation) && Objects.isNull(userId))
				return userRepo.findAll().stream().map(x -> assignUsersToDto(x)).collect(Collectors.toList());
			else if (Objects.isNull(userName) && Objects.isNull(designation))
				return userRepo.findById(userId).stream().map(x -> assignUsersToDto(x)).collect(Collectors.toList());
			else if (Objects.isNull(userId) && !Objects.isNull(userName) && Objects.isNull(designation))
				return userRepo.findByUserName(userName).stream().map(x -> assignUsersToDto(x))
						.collect(Collectors.toList());
			else if (!Objects.isNull(designation))
				return userRepo.findByUserDesignation(designation).stream().map(x -> assignUsersToDto(x))
						.collect(Collectors.toList());
			else if (!Objects.isNull(userId))
				return userRepo.findById(userId).stream().map(x -> assignUsersToDto(x)).collect(Collectors.toList());
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
		List<UserDto> returnList = users.stream().map(x -> assignUsersToDto(x)).collect(Collectors.toList());
		return returnList;
	}

	public UserDto assignUsersToDto(Users user) {
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
		if (user.getUserRoleRestrictions().size() != 0)
			userDto.setUserRoleRestrictions(user.getUserRoleRestrictions().get(0).getRoles().stream()
					.map(x -> x.getRoleName()).collect(Collectors.toList()));
		if (Objects.nonNull(user.getFiles()))
			userDto.setFileContent(user.getFiles().getFileContent());
		return userDto;
	}

	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public Boolean createUser(UserDto user) throws IOException {
		try {
			Users userDB = new Users();
			String filePath;
			if (Objects.nonNull(user.getFileContent())) {
				Files fileDb = new Files();
				fileDb.setFileContent(user.getFileContent());
				if (Objects.nonNull(user.getFileName()))
					fileDb.setFileName(user.getFileName());
				if (Objects.nonNull(user.getFileType()))
					fileDb.setFileType(user.getFileType());
				if (Objects.nonNull(user.getFilePath()))
					filePath = user.getFilePath();
				else
					filePath = "user";
				fileDb.setFilePath(filePath);
				fileDb = fileRepo.save(fileDb);
				userDB.setFiles(fileDb);
			}
			if (Objects.nonNull(user.getPassword())) {
				String encodePassword = this.passwordEncoder.encode(user.getPassword());
				userDB.setPassword(encodePassword);
			}
			userDB.setUserName(user.getUserName());
			userDB.setUserDesignation(user.getUserDesignation());
			userDB.setUserDOJ(user.getUserDOJ());
			userDB.setUserPhoneNumber(user.getUserPhoneNumber());
			userDB.setUserEmail(user.getUserEmail());
			userDB.setUserId(user.getUserId());
			// if (Objects.equals(null, file))
			// userDB.setFiles(null);
			userRepo.save(userDB);

			return true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public void removeUser(String userName) {
		userRepo.deleteByUserName(userName);
	}

	
	@Transactional(readOnly = true, rollbackFor = SQLException.class)
	public UserDto loginUser(UserDto user) throws IOException {
		try {
			Users userDb = userRepo.findByUserEmail(user.getUserEmail());
			
			if (Objects.nonNull(userDb.getPassword())) {
				String password = user.getPassword();
				String encodedPassword = userDb.getPassword();
				
				Boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
					if(isPasswordMatch) {
						return assignUsersToDto(userDb);
					} else {
						logger.error("loginUser : Provided information not vaild");
						throw new ApplicationContextException("Provided information not vaild");
					}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return null;
	}
	
}
