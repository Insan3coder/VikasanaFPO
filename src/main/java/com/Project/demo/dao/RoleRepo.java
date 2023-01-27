package com.Project.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Roles;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {

    List<Roles> findByRoleId(Long roleId);

    List<Roles> findByRoleName(String roleName);

    List<Roles> findByRoleIdAndRoleName(Long roleId, String roleName);

    @Modifying
    @Query("update Roles r set r.roleName =:roleName where r.roleId =:roleId")
    void updateRoleNameByRoleId(Long roleId, String roleName);

}
