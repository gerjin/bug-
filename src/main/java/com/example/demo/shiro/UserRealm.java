package com.example.demo.shiro;

import com.example.demo.dao.userDao;
import com.example.demo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Resource
    public userDao userDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 角色
        Set<String> roles = new HashSet<>();
        // 权限
        Set<String> permissions = new HashSet<>();
        // 测试用权限
        if ("admin".equals(user.getUsername())) {
            roles.add("admin");
            permissions.add("op:write");
        } else {
            roles.add("user");
            permissions.add("op:read");
        }
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userDao.getByUsername(username);

        if (user == null) {
            throw new UnknownAccountException(); // 账号不存在
        }



        if (user.getStatus() != 0) {
            throw new LockedAccountException();  // 账号被锁定
        }
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
                user.getPassword(),
                ByteSource.Util.bytes(salt),
                getName());

        return authenticationInfo;
    }
}
