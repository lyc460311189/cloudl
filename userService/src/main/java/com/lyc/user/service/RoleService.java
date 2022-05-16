package com.lyc.user.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lyc.user.model.Permission;
import com.lyc.user.model.PermissionRelation;
import com.lyc.user.model.Role;
import com.lyc.user.model.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 角色和权限相关方法
 */
@Service
@Transactional
public class RoleService {


//    /**
//     * 获取用户的所有权限
//     *
//     * @query userId
//     * @return
//     */
//    public List<Permission> getUserPermission(Integer userId) {
//        List<PermissionRelation> rolePermissions = rolePermissionRepository.findByRelIdIn(getRoleIdByUserId(userId));
//        return permissionRepository.findAllById(rolePermissions.stream().map(r -> r.getId()).collect(Collectors.toList()));
//    }

//    /**
//     * 获取指定类型的权限，比如菜单权限
//     *
//     * @query userId
//     * @query type
//     * @return
//     */
//    public List<Permission> getUserPermissionByType(Integer userId, Integer type) {
//        List<PermissionRelation> rolePermissions = rolePermissionRepository.findByRelIdIn(getRoleIdByUserId(userId));
//        return permissionRepository.findAllById(rolePermissions.stream().map(r -> r.getId()).collect(Collectors.toList()));
//    }

    /**
     * 获取用户的所有角色id
     *
     * @param userId
     * @return
     */
    public List<Integer> getRoleIdByUserId(Integer userId) {
        //List<UserRole> roles = userRoleRepository.findByUserId(userId);
        List<UserRole> roles = null;
        return roles.stream().map(r -> r.getRoleId()).collect(Collectors.toList());
    }
    public Permission findPermissionById(Integer id) {
        return null;
    }

    /**
     * 获取角色所有权限
     *
     * @param roleId
     * @return
     */
    public List<Permission> getPermissionByRoleId(Integer roleId) {
//        List<PermissionRelation> permissions = permissionRelationRepository.findByRelIdAndRelType(roleId, PermissionRelTypeEnum.Role.getIndex());
//        return permissionRepository.findAllById(permissions.stream().map(p -> p.getId()).collect(Collectors.toList()));
        return new ArrayList<>();
    }



//    public RoleVo getRole(Integer roleId) {
//        Role role = getRoleById(roleId);
//        RoleVo vo = new RoleVo();
//        BeanUtils.copyProperties(role, vo);
//        return vo;
//    }

    public Role getRoleById(Integer roleId) {
        Optional<Role> roleOptional = null ;
//        roleOptional = roleRepository.findById(roleId);
//        Validate.isTrue(roleOptional.isPresent(), ExceptionConstant.RoleNotExist);
        return roleOptional.get();
    }

    /*public void editRole(CreateRoleVo vo) {
//        Role role = getRoleById(vo.getId());
//        role.setRoleName(vo.getRoleName());
//        role.setDescription(vo.getDescription());
//        roleRepository.save(role);
    }*/

    public Integer addRole() {
        Role role = new Role();
//        role.setRoleName(vo.getRoleName());
//        role.setDescription(vo.getDescription());
//        role.setCreateTime(LocalDateTime.now());
//        roleRepository.save(role);
//        return role.getId();
        return 1;
    }
}
