package com.Project.demo.Service;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.demo.dao.EventFileMapRepo;
import com.Project.demo.dao.EventRepo;
import com.Project.demo.dao.FileRepo;
import com.Project.demo.dao.RoleRepo;
import com.Project.demo.dao.UserRepo;
import com.Project.demo.dao.UserRoleRestrictionRepo;
import com.Project.demo.dto.MappingDto;
import com.Project.demo.model.EventFileMap;
import com.Project.demo.model.Events;
import com.Project.demo.model.Files;
import com.Project.demo.model.Roles;
import com.Project.demo.model.UserRoleRestriction;
import com.Project.demo.model.Users;

@Service
public class MappingService {

    private Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRoleRestrictionRepo userRoleRestrictionRepo;

    @Autowired
    private EventFileMapRepo eventFileMapRepo;

    public void createMapping(MappingDto map) {
        logger.info("Inside Create Mapping");
        logger.debug(map);
        if (!Objects.isNull(map.getRoleId()) && !Objects.isNull(map.getUserId())) {
            Users user = userRepo.findById(map.getUserId()).get();
            Roles role = roleRepo.findById(map.getRoleId()).get();
            UserRoleRestriction ur = new UserRoleRestriction();
            ur.setRoles(role);
            ur.setUsers(user);
            userRoleRestrictionRepo.save(ur);
        }

        if (!Objects.isNull(map.getFileId()) && !Objects.isNull(map.getEventId())) {
            Files file = fileRepo.findById(map.getFileId()).get();
            Events event = eventRepo.findById(map.getEventId()).get();
            EventFileMap ef = new EventFileMap();
            ef.setEvents(event);
            ef.setFiles(file);
            eventFileMapRepo.save(ef);
        }

    }
}