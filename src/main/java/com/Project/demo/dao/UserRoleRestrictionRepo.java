package com.Project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.UserRoleRestriction;

@Repository
public interface UserRoleRestrictionRepo extends JpaRepository<UserRoleRestriction, Long> {

}
