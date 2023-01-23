package com.Project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long>{

	@Query(value = "select p from Project p where p.employee.employeeEmail=?1")
	List<Project> findByEmail(String useremail);

	@Query(value = "select p from Project p where p.employee.employeeEmail=?1 and p.projectName like %?2%")
	List<Project> findByEmailAndProjectName(String useremail, String projectName);

}
