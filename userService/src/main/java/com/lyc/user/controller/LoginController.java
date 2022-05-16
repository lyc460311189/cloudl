package com.lyc.user.controller;

import com.lyc.test.model.ResponseResult;
import com.lyc.user.model.User;
import com.lyc.user.model.UserInfo;
import com.lyc.user.service.UserService;
import com.lyc.user.shiro.JwtUtils;
import com.lyc.user.util.ValidUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author liyuchun
 * @version 1.0
 * @description: 用户登录模块
 * @date 2022/5/16 14:35
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    final private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@Validated @RequestBody UserInfo user, BindingResult result) {
        logger.info("航协  访问登录接口 开始 参数 user : {} " , user);
        if (result.hasErrors()) {
            return ResponseResult.failResult(ValidUtils.getErrorMessage(result));
        }
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            try {
                SecurityUtils.getSubject().logout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!currentUser.isAuthenticated()) {
            try {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPwd());
                currentUser.login(usernamePasswordToken);
                User u = userService.getUserByUserName(user.getUserName());
                return ResponseResult.successResult(JwtUtils.sign(u.getId().intValue(), user.getUserName()));
            } catch (UnknownAccountException uae) {
                return ResponseResult.failResult("未知账户");
            } catch (IncorrectCredentialsException ice) {
                return ResponseResult.failResult("密码不正确");
            } catch (LockedAccountException lae) {
                return ResponseResult.failResult("账户已锁定");
            } catch (ExcessiveAttemptsException eae) {
                return ResponseResult.failResult("用户名或密码错误次数过多");
            } catch (AuthenticationException ae) {
                return ResponseResult.failResult("用户名或密码不正确!");
            } catch (Exception e) {
                return ResponseResult.failResult("服务器内部错误");
            }
        }
        return ResponseResult.successResult(null);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseResult logout(HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            SecurityUtils.getSubject().logout();
            return ResponseResult.successResult();
        } else {
            return ResponseResult.failResult("用户尚未登录");
        }
    }

    @RequestMapping(value = "resetpassword")
    public ResponseResult repassword(@RequestParam String userName) {
        Optional<User> userOptional = userService.findUserByName(userName);
        if (!userOptional.isPresent()) {
            return ResponseResult.failResult("用户不存在");
        }
        userService.resetPassword(userName);
        return ResponseResult.successResult();
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult unauthorized() {
        return ResponseResult.failResult("没有权限");
    }
}
