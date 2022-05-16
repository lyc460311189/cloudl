package com.lyc.user.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/16 14:42
 */
@Data
public class UserInfo {
    @NotEmpty(message="用户名不能为空")
    private String userName;
    @Size(min = 0)
    private String pwd;
}

