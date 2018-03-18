package com.wfly.dao;

import org.apache.ibatis.annotations.Param;

public interface RoleDao {
	//建立user和role的关联
	int insertUseRoleOnId(@Param("userId") String userId , @Param("roleId") Integer roleId);

	int selectRoleByUserId(@Param("userId") String userId);
	
}
