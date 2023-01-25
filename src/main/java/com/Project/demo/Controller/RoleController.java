package com.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.Project.demo.Service.RoleService;

@RestController()
// @CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService userService;
    private Logger logger = LogManager.getLogger(UserController.class);

}
