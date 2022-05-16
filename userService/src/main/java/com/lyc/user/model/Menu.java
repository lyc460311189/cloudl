package com.lyc.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_menu")
public class Menu {
    private Integer id;
    private String menuName;
    private String href;
    private Integer parentId;
    private Integer viewId;
    private Integer menuState;
    private Integer orderNum;
    private LocalDateTime createTime;
    private Integer creatorId;
    private Integer relationId;
    private String routeName;
    private String routePath;
    private String routeIcon;
    private String routeComponent;
    private Boolean routeHideInMenu;

}
