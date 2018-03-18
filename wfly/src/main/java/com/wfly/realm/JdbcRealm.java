package com.wfly.realm;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.wfly.model.SimpleUser;
import com.wfly.model.User;
import com.wfly.service.LoginService;
import com.wfly.util.ResultUtil;

public class JdbcRealm extends AuthorizingRealm {

	@Resource //自动注入  
	private LoginService loginService;
	@Resource
	private ResultUtil result;
	

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.err.println("jdbcRealm:"+"AuthenticationInfo");
		//用户输入的凭证
		User userLogin = tokenToUser((UsernamePasswordToken)token);

		//数据库中的凭证
		User user = null;
		try {
			user = loginService.selectUserByUserName(userLogin.getLogin_name());
		} catch (Exception e) {

		}
		//若用户不存在, 则可以抛出 UnknownAccountException 异常
		if(user == null){
			return null;
		}

		//根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常. 
		//		if("monster".equals(username)){
		//			throw new LockedAccountException("用户被锁定");
		//		}

		//根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
		Object principal = user.getLogin_name();
		//2). credentials: 密码. 
		Object credentials = user.getUser_pwd(); //"fc1709d0a95a6be30bc5926fdb7f22f4";
		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值. 
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

		SimpleAuthenticationInfo info = null; 
		//new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);



		Session session = SecurityUtils.getSubject().getSession();
		user.setLast_login_time(new Timestamp(System.currentTimeMillis()));
		SimpleUser reUser = new SimpleUser(user);
		session.setAttribute("simpleUser", reUser);

		return info;
	}  

//	public static void main(String[] args) {
//		String hashAlgorithmName = "MD5";
//		Object credentials = "bf756d99f7dd7726";
//		ByteSource salt = ByteSource.Util.bytes("13111919211");
//		//		Object salt = null;
//		int hashIterations = 2;
//		System.out.println("info_credentials : "+credentials+"    info_credentialsSalt : "+salt);
////		String hashAlgorithmName = "MD5";
////		Object credentials = "888";
////		ByteSource salt = ByteSource.Util.bytes("134fb0bf3bdd54ee9098f4cbc4351b9a");
////		//		Object salt = null;
////		int hashIterations = 2;
//		
//		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//		System.out.println(result);
//	}

	//授权
	//暂时不授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		//1. 从 PrincipalCollection 中来获取登录用户的信息\
//		Object principal = principals.getPrimaryPrincipal();
//
//		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
//		ArrayList<String> selectRoleByUsername = loginService.selectRoleByUsername(principal.toString());
//		
//		Set<String> roles = new HashSet<>();
//		roles.add("user");
//		if("admin".equals(principal)){
//			roles.add("admin");
//		}
//
//		//3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
//		for (String string : selectRoleByUsername) {
//			info.addStringPermission(string);
//		}
//
//		//4. 返回 SimpleAuthorizationInfo 对象. 
//		return info;
		return null;
	}

	public static User tokenToUser(UsernamePasswordToken token) {
		User user = new User();
		user.setLogin_name(token.getUsername());
		user.setUser_pwd(String.valueOf(token.getPassword()) );
		return user;
	}
}
