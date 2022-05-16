package com.lyc.user.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * ShiroUtils class
 *
 * @author william
 * @date 2020/3/6
 */
public class ShiroUtils {
    public static String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new RuntimeException("没有权限");
        }
        return JwtUtils.getUsername(subject.getPrincipal().toString());
    }

    public static Integer getUserId() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new RuntimeException("没有权限");
        }
        return JwtUtils.getUserId(subject.getPrincipal().toString());
    }

    /**
     * 实现自动登录，方便测试时使用
     */
    public void autoLogin() {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "000000");
        currentUser.login(usernamePasswordToken);
    }
}
