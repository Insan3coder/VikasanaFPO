package com.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.Project.demo.Service.RoleService;
import com.Project.demo.dto.RoleDto;

@Component
@RestController()
// @CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<RoleDto> getAll(@RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "roleId", required = false) Long roleId) {
        logger.info("Inside getAll");
        return roleService.getAll(roleId, roleName);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> createRole(@RequestBody RoleDto role) throws ApplicationContextException {
        Boolean status = roleService.createRole(role);
        if (status)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public RoleDto updateRole(@PathVariable("id") Long roleId, @RequestBody RoleDto role) {
        return roleService.updateRole(roleId, role.getRoleName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") Long roleId) {
        roleService.deleteRole(roleId);
    }

}
