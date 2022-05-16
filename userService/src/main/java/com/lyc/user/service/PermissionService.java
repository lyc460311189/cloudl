package com.lyc.user.service;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PermissionService class
 *
 * @author william
 * @date 2020/3/4
 */
@Service
public class PermissionService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    /**
     * 为指定的对象集合添加权限，reltype设定是用户，部门还是角色
     *
     * @param permId
     * @param ids     对象集合
     * @param relType 权限类型:部门，用户，角色
     */
    public void addPermissionRel(Integer permId, List<Integer> ids, Integer relType) {
//        ids.forEach(id -> {
//            PermissionRelation relation = new PermissionRelation();
//            relation.setCreateTime(LocalDateTime.now());
//            relation.setPermissionId(permId);
//            relation.setRelId(id);
//            relation.setRelType(relType);
//            permissionRelationRepository.save(relation);
//        });
    }

    /**
     * 编辑权限集合不存在的权限添加，已经存在，未传入的删除
     *
     * @param permId
     * @param ids
     * @param relType
     */
    public void editPermissionRel(Integer permId, List<Integer> ids, Integer relType) {
//        List<Integer> existIds = permissionRelationRepository.findByPermissionIdAndRelType(permId, relType).stream().map(p -> p.getRelId()).collect(Collectors.toList());
//        ids.forEach(id -> {
//            if (!existIds.contains(id)) {
//                PermissionRelation relation = new PermissionRelation();
//                relation.setCreateTime(LocalDateTime.now());
//                relation.setPermissionId(permId);
//                relation.setRelId(id);
//                relation.setRelType(relType);
//                permissionRelationRepository.save(relation);
//            } else {
//                existIds.remove(id);
//            }
//        });
//        deletePermissionRel(permId, existIds, relType);
    }

    public void deletePermissionRel(Integer permId, List<Integer> ids, Integer relType) {
//        ids.forEach(id -> {
//            permissionRelationRepository.deleteByPermissionIdAndRelIdAndRelType(permId, id, relType);
//        });
    }

    public void deletePermissionRel(Integer permId, Integer id, Integer relType) {
//        permissionRelationRepository.deleteByPermissionIdAndRelIdAndRelType(permId, id, relType);
    }

    public void deletePermissionRelByPermId(Integer permId) {
//        permissionRelationRepository.deleteByPermissionId(permId);
    }

    /**
     * 删除部门角色用户的所有权限关系
     *
     * @param id
     * @param relType
     */
    public void deletePermissionRelByRelationId(Integer id, Integer relType) {
//        permissionRelationRepository.deleteByRelTypeAndRelId(relType, id);
    }

    /**
     * 添加权限类型
     *
     * @param permName
     * @param description
     * @param relationId
     * @param permType    1.菜单权限2操作权限3数据权限
     * @return
     */
    public Integer addPermission(String permName, String description, Integer relationId, Integer permType) {
//        Permission permission = new Permission();
//        permission.setPermissionName(permName);
//        permission.setDescription(description);
//        permission.setRelationId(relationId);
//        permission.setPermissionType(permType);
//        permissionRepository.save(permission);
//        return permission.getId();
        return 1;
    }

    /**
     * 添加权限，传入实体名称，自动生成权限名称和描述
     *
     * @param entiyName
     * @param relationId
     * @param permType
     * @return
     */
    public Integer addPermission(String entiyName, Integer relationId, Integer permType) {
//        PermissionTypeEnum permissionTypeEnum = PermissionTypeEnum.getById(permType);
//        String permName = String.format("%s:%s", permissionTypeEnum.getName(), PinyinUtils.chineseToPinYinF(entiyName));
//        String description = String.format("%s:%s", permissionTypeEnum.getDescription(), entiyName);
//        Permission permission = new Permission();
//        permission.setPermissionName(permName);
//        permission.setDescription(description);
//        permission.setRelationId(relationId);
//        permission.setPermissionType(permType);
//        permissionRepository.save(permission);
//        return permission.getId();
        return 2;
    }

    /**
     * 添加视图操作权限，按钮权限
     *
     * @param opertionName
     * @param relationId
     * @param viewId
     * @return
     */
    public Integer addPermissionForOperation(String opertionName, Integer relationId, Integer viewId) {
        /*PermissionTypeEnum permissionTypeEnum = PermissionTypeEnum.OPERATOR;
        String permName = String.format("%s:%s", permissionTypeEnum.getName(), PinyinUtils.chineseToPinYinF(opertionName));
        String description = String.format("%s:%s", permissionTypeEnum.getDescription(), opertionName);
        Permission permission = new Permission();
        permission.setPermissionName(permName);
        permission.setDescription(description);
        permission.setRelationId(relationId);
        permission.setPermissionType(permissionTypeEnum.getIndex());
        permission.setGroupId(viewId);
        permissionRepository.save(permission);
        return permission.getId();*/
        return 3;
    }


}
