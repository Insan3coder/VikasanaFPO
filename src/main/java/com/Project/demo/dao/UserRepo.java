package com.Project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Users;


@Repository
public interface UserRepo extends JpaRepository<Users,Long>{ 
	
	Users findByUserName(String userName);

	// @Query(value = "select e from Users e where e.userDesignation like
	// %:userDesgination%")
	List<Users> findByUserDesignation(String userDesignation);

	void deleteByUserName(String userName);

}
