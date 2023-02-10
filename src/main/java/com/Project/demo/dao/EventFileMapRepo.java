package com.Project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.EventFileMap;

@Repository
public interface EventFileMapRepo extends JpaRepository<EventFileMap, Long> {

}
