package com.lyc.user.shiro;

import com.lyc.user.model.Role;
import com.lyc.user.model.User;
import com.lyc.user.model.UserRole;
import com.lyc.user.service.RoleService;
import com.lyc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    final private static Logger logger = LoggerFactory.getLogger(JwtRealm.class);


    @Override
    public boolean supports(AuthenticationToken token) {
        if(token instanceof JwtToken){
            return false;}
        return true;
    }
    /**
     * 提供用户信息，返回权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String) principals.getPrimaryPrincipal();
        /*String userId=userService.findUserIdByName(userName);
        Set<UserRole> roleIdSet=userService.findRoleIdByUid( Integer.parseInt(userId) );
        Set<String> roleSet=new HashSet<>();
        Set<Integer>  pemissionIdSet=new HashSet<>();
        Set<String>  pemissionSet=new HashSet<>();
        for(UserRole roleInfo : roleIdSet) {
            int roleId=roleInfo.getRoleId();
            Optional<Role> role=roleRepository.findById(roleId);
            role.ifPresent(r->{
                roleSet.add(r.getRoleName());

            });
            //将拥有角色的所有权限放进Set里面，也就是求Set集合的并集
            pemissionIdSet.addAll( roleService.getPermissionByRoleId(  roleId ).stream().map(p->p.getId()).collect(Collectors.toList()));
        }
        for(int permissionId : pemissionIdSet) {
            String permission= roleService.findPermissionById( permissionId ).getPermissionName() ;
            pemissionSet.add(  permission );
        }*/
        // 将角色名称组成的Set提供给授权info
//        authorizationInfo.setRoles( roleSet );
//        // 将权限名称组成的Set提供给info
//        authorizationInfo.setStringPermissions(pemissionSet);

        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName=(String)authenticationToken.getPrincipal();
        Optional<User> userOpt=userService.findUserByName(userName);

        if(userOpt==null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
        User user=userOpt.get();
//        if (user.getJobState().equals(JobStateEnum.Leave.getIndex())||user.getIsDelete().equals(TrueFalseEnum.True.getIndex())) {
//            //用户被锁定就抛异常
//            throw new UnknownAccountException();
//        }
//        if (user.getJobState().equals(JobStateEnum.Leave.getIndex())) {
//            //用户被锁定就抛异常
//            throw new LockedAccountException();
//        }
//        //密码可以通过SimpleHash加密，然后保存进数据库。
//        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
//        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,
//                user.getPassword(),
//                getName());                   //realm name
        return new SimpleAuthenticationInfo();
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        //存储散列后的密码是否为16进制
        //hashedCredentialsMatcher.isStoredCredentialsHexEncoded();
        return hashedCredentialsMatcher;
       // return new PasswordMatcher();
    }
}
