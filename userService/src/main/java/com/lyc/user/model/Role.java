package com.lyc.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName( "sys_role")
public class Role {
    private Integer id;
    private String roleName;
    private String description;
    private Integer creatorId;
    private LocalDateTime createTime;
}
