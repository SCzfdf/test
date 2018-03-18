package com.wfly.service;


import com.wfly.model.User;

public interface LoginService {
	//	public String login(Subject subject, UsernamePasswordToken token) throws AuthenticationException;
//
//	boolean isRegister(User user);
//
//	int addUseRoleOnId(String userId, Integer roleId);
//
//	int selectRoleByUserId(String userId);

	
//	ArrayList<String> selectRoleByUsername(String userName);

	boolean isRegister(User user);
	
	int insertUser(User user);
	
	User selectUserByUser(User user);

	User selectUserByUserName(String login_name);
}
