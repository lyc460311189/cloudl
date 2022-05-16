package com.lyc.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_permission")
public class Permission {
    private Integer id;
    private String permissionName;
    private Integer permissionType;
    private Integer relationId;
    private Integer groupId;
    private String description;
    private Integer viewId;
}
