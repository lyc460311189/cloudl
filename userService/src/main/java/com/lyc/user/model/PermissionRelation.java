package com.lyc.user.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(  "sys_permission_relation")
public class PermissionRelation {
    private Integer id;
    private Integer relId;
    private Integer relType;
    private Integer permissionId;
    private Integer creatorId;
    private LocalDateTime createTime;
}
