package com.lyc.test.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyuchun
 * @since 2022-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录用户名
     */
    @TableField("user_name")
    private String user_name;

    /**
     * 登录账号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * md5加密密码
     */
    @TableField("password")
    private String password;

    /**
     * 盐值
     */
    @TableField("salt")
    private String salt;

    /**
     * 真实姓名
     */
    @TableField("nick_name")
    private String nick_name;

    /**
     * 当前状态
     */
    @TableField("user_state")
    private Integer user_state;

    /**
     * 是否被锁定
     */
    @TableField("is_lock")
    private Integer is_lock;

    /**
     * 部门id
     */
    @TableField("department_id")
    private Integer department_id;

    /**
     * 是否删除.0:正常1.已删除
     */
    @TableField("is_delete")
    private Integer is_delete;

    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;

    /**
     * 2表示女1表示男
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 职务
     */
    @TableField("position")
    private String position;

    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date create_time;

    /**
     * 更新日期
     */
    @TableField("update_time")
    private Date update_time;

    /**
     * 创建人
     */
    @TableField("creator_id")
    private Integer creator_id;

    /**
     * 1在职2离职
     */
    @TableField("job_state")
    private Integer job_state;

    /**
     * 用户安全级别
     */
    @TableField("safe_level")
    private Integer safe_level;

    /**
     * 是否是领导
     */
    @TableField("is_leader")
    private Boolean is_leader;

    /**
     * 最后一次尝试登录日期
     */
    @TableField("last_login_time")
    private Date last_login_time;

    /**
     * 一段时间内的尝试次数
     */
    @TableField("login_count")
    private Integer login_count;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    @TableField("user_type")
    private Integer user_type;

    @TableField("unit_id")
    private Long unit_id;

    @TableField("major_ids")
    private String major_ids;

    @TableField("birthday")
    private Date birthday;


}
