package com.Project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Files;
import com.Project.demo.model.Users;

@Repository
public interface FileRepo extends JpaRepository<Files, Long> {

    Users findByFileId(Long fileId);

    // @Query(value = "select e from Users e where e.userDesignation like
    // %:userDesgination%")
    // List<Users> findByuserDesgination(String userDesgination);

}
