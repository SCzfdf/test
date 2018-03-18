package com.wfly.dao;

import org.apache.ibatis.annotations.Param;

import com.wfly.model.User;

public interface UserDao {

	
	int insertUserByUser(@Param("user") User user);
	
	User selectUserByPhone(String username);
	
	User selectUserByUser(@Param("user") User user);

	User selectUserByUserName(String login_name);

}