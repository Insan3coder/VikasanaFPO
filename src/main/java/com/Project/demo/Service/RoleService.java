package com.Project.demo.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Project.demo.dao.RoleRepo;
import com.Project.demo.dto.RoleDto;
import com.Project.demo.model.Roles;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    private Logger logger = LogManager.getLogger(RoleService.class);

    @Transactional(readOnly = true)
    public List<RoleDto> getAll(Long roleId, String roleName) {
        try {

            if (Objects.isNull(roleId) && Objects.isNull(roleName))
                return roleRepo.findAll().stream().map(x -> assignRoleDto(x)).collect(Collectors.toList());
            else if (Objects.isNull(roleName)) {
                return roleRepo.findByRoleId(roleId).stream().map(x -> assignRoleDto(x)).collect(Collectors.toList());
            } else if (Objects.isNull(roleId)) {
                return roleRepo.findByRoleName(roleName).stream().map(x -> assignRoleDto(x))
                        .collect(Collectors.toList());
            } else if (!roleId.equals(null) && !roleName.isEmpty()) {
                return roleRepo.findByRoleIdAndRoleName(roleId, roleName).stream().map(x -> assignRoleDto(x))
                        .collect(Collectors.toList());
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }

    }

    private RoleDto assignRoleDto(Roles role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public Boolean createRole(RoleDto role) {
        try {
            Roles roleDB = new Roles();
            roleDB.setRoleName(role.getRoleName());
            roleRepo.save(roleDB);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public RoleDto updateRole(Long roleId, String roleName) {
        try {
            roleRepo.updateRoleNameByRoleId(roleId, roleName);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public void deleteRole(Long roleId) {
        try {
            roleRepo.deleteById(roleId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

}
