package com.wfly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wfly.dao.UserDao;
import com.wfly.model.User;
import com.wfly.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Resource //自动注入  
    private UserDao userDao;
//	@Resource
//	private RoleDao roleDao;
//	@Resource
//	private PermissionDao permissionDao;
	
	@Override
	public boolean isRegister(User user) {
		User selectUserByPhone = userDao.selectUserByPhone(user.getPhone());
		if(selectUserByPhone != null ) {
			return false;
		}
		return true;
	}
	
	@Override
	public int insertUser(User user) {
		return userDao.insertUserByUser(user);
	}
	
	@Override
	public User selectUserByUser(User user) {
		return userDao.selectUserByUser(user);
	}

	@Override
	public User selectUserByUserName(String login_name) {
		return userDao.selectUserByUserName(login_name);
	}

//	@Override
//	public User doUserLogin(User userLogin) {
//		return userDao.selectUserByUser(userLogin);
//	}
//
	
//	
//	@Override
//	public int addUseRoleOnId(String userId, Integer roleId) {
//		return roleDao.insertUseRoleOnId(userId, roleId);
//	}
//
//	@Override
//	public int selectRoleByUserId(String userId) {
//		return roleDao.selectRoleByUserId(userId);
//	}
//	
//	@Override
//	public ArrayList<String> selectRoleByUsername(String userName) {
//		ArrayList<String> selePermissionsByUsername = permissionDao.selePermissionsByUsername(userName);
//		return selePermissionsByUsername;
//	}
	
//	@Override
//	public String login(Subject subject, UsernamePasswordToken token) throws AuthenticationException {
//		
////		User user = userDao.selectUserByUser(token.getUsername());
//		
//		subject.login(token);  
//		if (subject.isAuthenticated()) {  
//			
//			subject.getSession().setAttribute("token", token);
//			return "/wfly/index.html";  
//		} else {  
//			return "login/html";  
//		}
//	}
	
}
