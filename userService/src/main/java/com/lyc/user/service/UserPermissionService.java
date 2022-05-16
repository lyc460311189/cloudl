package com.lyc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserPermissionService class
 *对用户的权限进行管理
 * @author william
 * @date 2020/3/25
 */
@Service
@Transactional
public class UserPermissionService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /**
     * 获取用户指定类型的所有权限id去重结构
     *
     * @param userId
     * @param permType
     * @return
     */
    public List<Integer> getPermissionIdsByUser(Integer userId, Integer permType) {
        //获取拥有的角色权限，用户权限，部门权限，组织权限
        //获取部门权限
        /*List<Integer> deparmtmentIds=departmentService.getAllDepartmentByUserId(userId);
        List<Integer> departPermIds = permissionRelationRepository.findByRelIdInAndRelType(deparmtmentIds, PermissionRelTypeEnum.Department.getIndex()).stream().map(pr -> pr.getPermissionId()).collect(Collectors.toList());
        //获取角色权限
        List<Integer> roleIds = roleService.getRoleIdByUserId(userId);
        List<Integer> rolePermIds = permissionRelationRepository.findByRelIdInAndRelType(roleIds, PermissionRelTypeEnum.Role.getIndex()).stream().map(pr -> pr.getPermissionId()).collect(Collectors.toList());
        //获取用户权限
        List<Integer> userPermIds = permissionRelationRepository.findByRelIdAndRelType(userId, PermissionRelTypeEnum.User.getIndex()).stream().map(pr -> pr.getPermissionId()).collect(Collectors.toList());
        result.addAll(departPermIds);
        result.addAll(rolePermIds);
        result.addAll(userPermIds);*/
        Set<Integer> result = new HashSet<>();
        return new ArrayList<>(result);
    }

    /**
     * 用户是否具有指定的权限
     * @param userId
     * @param permissionId
     * @return
     */
    public Boolean havePermissionWithUser(Integer userId,Integer permissionId){
        /*List<PermissionRelation> permissionRelations=permissionRelationRepository.findByPermissionId(permissionId);
        Set<Integer> departMents=new HashSet<>(departmentService.getAllDepartmentByUserId(userId));
        Set<Integer> roles=new HashSet<>(roleService.getRoleIdByUserId(userId));
        for(PermissionRelation rel:permissionRelations){
            if(rel.getRelType().equals(PermissionRelTypeEnum.Role.getIndex())){
                if(roles.contains(rel.getRelId())){
                    return true;
                }
            }
            if(rel.getRelType().equals(PermissionRelTypeEnum.Department.getIndex())){
                if(departMents.contains(rel.getRelId())){
                    return true;
                }
            }
            if(rel.getRelType().equals(PermissionRelTypeEnum.User.getIndex())){
                if(userId.equals(rel.getRelId())){
                    return true;
                }
            }
        }*/
        if(true){
            return true;
        }
        return false;
    }
}
