package com.Project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Files;


@Repository
public interface DBFileRepository extends JpaRepository<Files, String> {

}