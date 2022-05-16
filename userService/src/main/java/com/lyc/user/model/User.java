package com.lyc.user.model;

import lombok.Data;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/16 14:39
 */

@Data
public class User {

    private Long id;
    private String userName;
    private String pwd;
}
