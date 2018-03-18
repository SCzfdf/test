package com.wfly.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wfly.model.Permission;

public interface PermissionDao {
	int selectPermisionsByUserId(@Param("userId") String userId);
	
	ArrayList<String>  selePermissionsByUsername(@Param("userName") String userName);
	
}
