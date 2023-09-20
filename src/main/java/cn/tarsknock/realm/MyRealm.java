package cn.tarsknock.realm;

import cn.tarsknock.entity.User;
import cn.tarsknock.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     *
     * @param principalCollection
     * @return 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        Integer  id = (Integer) principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        if(username.equals("Tars-knock")){
            roles.add("admin");
            permissions.add("admin:change");
        }
        else if(username.equals("visitor")){
            roles.add("admin");
        }
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录验证
     * @param authenticationToken:基于用户名密码的令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        //让shiro去验证帐号密码
        User user = userService.getUserByUsername(username);
        if(user!=null){
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);      //将当前用户信息存入session
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            return authenticationInfo;
        }
        return null;
    }
}
